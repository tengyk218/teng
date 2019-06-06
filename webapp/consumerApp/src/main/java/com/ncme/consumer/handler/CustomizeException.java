package com.ncme.consumer.handler;

import com.netflix.hystrix.exception.HystrixBadRequestException;

/**
 * 自定义异常
 */
public class CustomizeException extends HystrixBadRequestException {

    public CustomizeException(String errorMsg){
        super(errorMsg);
    }

}
