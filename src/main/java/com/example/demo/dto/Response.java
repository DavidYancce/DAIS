package com.example.demo.dto;

public class Response<T> {
    public T data;
    public String errorMessage;

    public Response() {
        this.data = null;
        this.errorMessage = null;
    }

    public Response(T data) {
        this.data = data;
        this.errorMessage = null;
    }

    public Response(T data, String errorMessage) {
        this.data = data;
        this.errorMessage = errorMessage;
    }
}
