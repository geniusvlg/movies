package com.fact.nash.exceptions;

import com.fact.nash.constants.ErrorCode;
import org.springframework.http.HttpStatus;

public class DatahubRequestUnprocessableException extends BaseException{

    public DatahubRequestUnprocessableException(String message) {
        super(ErrorCode.MISSING_PARAM, message, HttpStatus.UNAUTHORIZED.value());
    }

    public DatahubRequestUnprocessableException() {
        super(ErrorCode.MISSING_PARAM, "Unprocessable Exception", HttpStatus.UNAUTHORIZED.value());
    }
}
