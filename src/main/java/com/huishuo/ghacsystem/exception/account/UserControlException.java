package com.huishuo.ghacsystem.exception.account;

public class UserControlException extends RuntimeException {
    public UserControlException(String message) {
        super(message);
    }

    public UserControlException(String message, Throwable cause) {
        super(message, cause);
    }
}
