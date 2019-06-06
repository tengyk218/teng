package com.ncme.consumer.handler;

import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ExceptionErrorDecoder implements ErrorDecoder {

    public Exception decode(String s, Response response) {
        try {
            String errorMsg = Util.toString(response.body().asReader());
            /**
             * 业务异常抛出自定义异常
             * 其他进行熔断
             */
            return new Exception(errorMsg);
        }catch (Exception e){
        }
        return null;
    }
}
