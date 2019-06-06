package com.ncme.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix // Feign默认是开启，这个注解可以不加的
public class consumerApp {
    public static void main(String[] args) {
        SpringApplication.run(consumerApp.class, args);
    }


}
