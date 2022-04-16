package com.fact.nash.exceptions;

import com.fact.nash.constants.ErrorCode;
import org.springframework.http.HttpStatus;

public class DatahubRequestMissingRequiredParameterException extends BaseException{

    public DatahubRequestMissingRequiredParameterException(Throwable err) {
        super(ErrorCode.MISSING_PARAM, "Missing Required Parameter Exception", HttpStatus.UNAUTHORIZED.value(), err);
    }

    public DatahubRequestMissingRequiredParameterException(String str) {
        super(ErrorCode.MISSING_PARAM, str, HttpStatus.UNAUTHORIZED.value());
    }
}
