package com.ncme.consumer.service;

import com.ncme.consumer.domain.User;
import com.ncme.consumer.handler.HystrixClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="provider-client", path = "/eureka/test", fallbackFactory = HystrixClientFallbackFactory.class)
public interface TestService {

    @RequestMapping(value = "/queryUser", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public User queryUser(@RequestParam("id") String id);


    @RequestMapping(value = "/saveUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveUser(@RequestBody User user);

}