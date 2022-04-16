package com.fact.nash.validator;

import com.fact.nash.exceptions.DatahubRequestMissingRequiredParameterException;
import com.fact.nash.proto.general.DhDataRequestV1;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RequestValidator {

    public DatahubRequestMissingRequiredParameterException validateMovieRequest(DhDataRequestV1 request) throws IOException, ParseException {

        if (request == null) {
            throw new NullPointerException("Request is null");
        }

        int date = request.getDatesList().get(0);
        if (date == 0) {
            return new DatahubRequestMissingRequiredParameterException("Invalid dates");
        }

        return null;
    }

    public void validateIsOnRequest(String ids) throws DatahubRequestMissingRequiredParameterException {
        if (ids.isEmpty() || ids.equals("")) {
            throw new DatahubRequestMissingRequiredParameterException("Empty Id");
        }
    }
}
