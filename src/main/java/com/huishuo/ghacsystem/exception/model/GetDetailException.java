package com.huishuo.ghacsystem.exception.model;

public class GetDetailException extends RuntimeException {
    public GetDetailException(String message) {
        super(message);
    }

    public GetDetailException(String message, Throwable cause) {
        super(message, cause);
    }
}
