package com.github.ionething.sparrow.framework.exception;

public class BussinessException extends Exception {

    public BussinessException() {
    }

    public BussinessException(String message) {
        super(message);
    }

    public BussinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
