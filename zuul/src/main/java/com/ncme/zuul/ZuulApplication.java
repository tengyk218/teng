package com.ncme.zuul;

import com.ncme.zuul.filter.TokenAuthenticationFilter;
import com.ncme.zuul.filter.TokenRefreshFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
@EnableZuulProxy
//@EnableOAuth2Sso
public class ZuulApplication {

    @Autowired
    private RedisTemplate redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

//    @Bean
//    public RequestContextListener requestContextListener() {
//        return new RequestContextListener();
//    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(redisTemplate);
    }

    @Bean
    public TokenRefreshFilter tokenRefushFilter() {
        return new TokenRefreshFilter(redisTemplate);
    }

}
