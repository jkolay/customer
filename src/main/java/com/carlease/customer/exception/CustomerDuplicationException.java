package com.carlease.customer.exception;

/**
 * This is the custom exception class if customer is entering duplicate details
 */
public class CustomerDuplicationException extends Exception{
    public CustomerDuplicationException() {
        super();
    }

    public CustomerDuplicationException(String message) {
        super(message);
    }

    public CustomerDuplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerDuplicationException(Throwable cause) {
        super(cause);
    }

    protected CustomerDuplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
