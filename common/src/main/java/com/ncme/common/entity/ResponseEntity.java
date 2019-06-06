package com.ncme.common.entity;

public class ResponseEntity<T> {

    private T data;

    private String code;

    private String message;

    public ResponseEntity() {
    }

    public ResponseEntity(String code, String message) {
        this(null, code, message);
    }

    public ResponseEntity(T data, String code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
