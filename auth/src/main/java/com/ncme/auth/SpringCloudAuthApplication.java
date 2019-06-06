package com.ncme.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.neo.springcloudauth.mapper")
public class SpringCloudAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAuthApplication.class, args);
    }

}
