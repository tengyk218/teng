package com.ncme.auth.config;

import com.ncme.auth.oauth2.MyJwtTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @author YooLin1c
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("client")
//                .secret("{noop}client-secret")
//                .authorizedGrantTypes("refresh_token", "password","client_credentials")
//                .scopes("read")
//                .accessTokenValiditySeconds(1200)
//                .refreshTokenValiditySeconds(50000);

        clients.inMemory()
                .withClient("client1")
                .secret("{noop}password")
                .authorizedGrantTypes("refresh_token", "password")
                .scopes("select")
                .and().withClient("client2")
                .secret("{noop}credentials")
                .authorizedGrantTypes("client_credentials")
                .scopes("select");
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("demo");
        return jwtAccessTokenConverter;
    }

    @Bean
    public TokenStore jwtTokenStore() {
        return new MyJwtTokenStore(jwtAccessTokenConverter(),redisTemplate);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(jwtTokenStore())
                .accessTokenConverter(jwtAccessTokenConverter())
                .reuseRefreshTokens(true)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }
}
