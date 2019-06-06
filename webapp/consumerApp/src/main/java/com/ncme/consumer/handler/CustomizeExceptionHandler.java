package com.ncme.consumer.handler;

import com.ncme.common.entity.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public Object exceptionResolve(Exception ex){
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setCode("500");
        responseEntity.setMessage(ex.getMessage());
        try {
            return responseEntity;
        }catch (Exception e){

        }
        return "";
    }
}
