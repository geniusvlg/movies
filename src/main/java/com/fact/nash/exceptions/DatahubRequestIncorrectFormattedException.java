package com.fact.nash.exceptions;

import com.fact.nash.constants.ErrorCode;
import org.springframework.http.HttpStatus;

public class DatahubRequestIncorrectFormattedException extends BaseException{

    public DatahubRequestIncorrectFormattedException(String message) {
        super(ErrorCode.MISSING_PARAM, message, HttpStatus.UNAUTHORIZED.value());
    }

    public DatahubRequestIncorrectFormattedException() {
        super(ErrorCode.MISSING_PARAM, "Invalid Request Format Exception", HttpStatus.UNAUTHORIZED.value());
    }

}
