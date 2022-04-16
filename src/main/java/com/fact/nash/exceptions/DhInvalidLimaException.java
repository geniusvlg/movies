package com.fact.nash.exceptions;

public class DhInvalidLimaException extends BaseException{
    public DhInvalidLimaException(int errorCode, String message, int httpStatusCode, Throwable err) {
        super(errorCode, message, httpStatusCode, err);
    }

    public DhInvalidLimaException(int errorCode, String message, int httpStatusCode) {
        super(errorCode, message, httpStatusCode);
    }
}
