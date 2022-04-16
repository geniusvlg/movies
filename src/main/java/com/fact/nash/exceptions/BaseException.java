package com.fact.nash.exceptions;

public abstract class BaseException extends Exception{

    int errorCode;
    String message;
    int httpStatusCode;

    public BaseException(int errorCode, String message, int httpStatusCode,  Throwable err) {
        super(message, err);
        this.errorCode = errorCode;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    public BaseException(int errorCode, String message, int httpStatusCode) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    public String toString() {
        return String.format("%d: %s", errorCode, message);
    }

}
