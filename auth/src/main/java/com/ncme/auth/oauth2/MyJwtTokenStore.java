package com.ncme.auth.oauth2;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

public class MyJwtTokenStore extends JwtTokenStore {

    private RedisTemplate redisTemplate;

    public MyJwtTokenStore(JwtAccessTokenConverter jwtTokenEnhancer,RedisTemplate template){
        super(jwtTokenEnhancer);
        this.redisTemplate = template;
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        Object principal = authentication.getPrincipal();
        //如果是用户密码登录，需要存储token作为验证处理
        if(principal instanceof  User){
            User user = (User)principal;
            String username = user.getUsername();
            redisTemplate.opsForSet().add(username, token.getValue());
        }else{
            String key = principal.toString();
            redisTemplate.opsForSet().add(key, token.getValue());
        }
    }

    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
        Object principal = authentication.getPrincipal();
        //如果是用户密码登录，需要存储token作为验证处理
        if(principal instanceof  User){
            User user = (User)principal;
            String username = user.getUsername();
            redisTemplate.opsForSet().add(username, refreshToken.getValue());
        }
    }

}
