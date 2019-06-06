package com.ncme.consumer.handler;

import com.ncme.consumer.domain.User;
import com.ncme.consumer.service.TestService;
import feign.hystrix.FallbackFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HystrixClientFallbackFactory implements FallbackFactory {

    public Object create(Throwable throwable) {
        System.out.println("feignError：" + throwable.getMessage());
        return new TestService(){
            @Override
            public User queryUser(String id) {
                return new User();
            }

            @Override
            public void saveUser(User user) {

            }
        };
    }

}
