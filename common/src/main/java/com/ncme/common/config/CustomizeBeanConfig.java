package com.ncme.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@PropertySource(
        value = {
                "classpath:application-dao.properties"
                ,"classpath:application-mybatis.properties"
                ,"classpath:application-springmvc.properties"
        },
        ignoreResourceNotFound = true, encoding = "utf-8")
public class CustomizeBeanConfig {

    @ConfigurationProperties("spring.datasource")
    @Bean(initMethod = "init",destroyMethod = "close")
    public DruidDataSource getDruidDataSource(){
        return new DruidDataSource();
    }


    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @ConfigurationProperties(prefix = "springmvc.jacksonconverter")
    public MappingJackson2HttpMessageConverter getMappingJacksonHttpMessageConverter(){
        return new MappingJackson2HttpMessageConverter();
    }

    @Bean
    @ConfigurationProperties(prefix = "springmvc.stringconverter")
    public StringHttpMessageConverter getStringHttpMessageConverter(){
        return new StringHttpMessageConverter();
    }

    @Bean
    public RequestMappingHandlerAdapter getMethodHandlerAdapter(StringHttpMessageConverter stringHttpMessageConverter, MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter){
        RequestMappingHandlerAdapter mappingHandlerAdapter = new RequestMappingHandlerAdapter();
        List list = new ArrayList();
        list.add(stringHttpMessageConverter);
        list.add(mappingJackson2HttpMessageConverter);
        mappingHandlerAdapter.setMessageConverters(list);
        return mappingHandlerAdapter;
    }

}