package com.ncme;

import com.ncme.webapi.filter.MyFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ServletComponentScan(basePackageClasses = MyFilter.class)
@EnableTransactionManagement
@MapperScan("com.dao")
@EnableConfigurationProperties
@SpringBootApplication
@EnableEurekaClient
public class NcmeAPIApplication {

    private Logger logger = LoggerFactory.getLogger(NcmeAPIApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(NcmeAPIApplication.class, args);
    }

}
