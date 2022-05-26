package com.fact.nash.validator;

import com.fact.nash.Constants;
import com.fact.nash.exceptions.DatahubRequestIncorrectFormattedException;
import com.fact.nash.exceptions.DatahubRequestMissingRequiredParameterException;
import com.fact.nash.exceptions.DatahubRequestUnprocessableException;
import com.fact.nash.proto.general.DhDataRequestV1;
import com.fact.nash.proto.general.DhRequestV1;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;

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

    public void validateDateRequest(DhDataRequestV1 request) throws DatahubRequestMissingRequiredParameterException,
            DatahubRequestUnprocessableException, DatahubRequestIncorrectFormattedException {

        if (request.getIdsList().isEmpty()) {
            throw new DatahubRequestMissingRequiredParameterException("Ids");
        }

        if (request.getFieldsList().isEmpty()) {
            throw new DatahubRequestMissingRequiredParameterException("fields");
        }

        List<String> validFields = Constants.supportedMovieDatesFields;
        List<String> fields = request.getFieldsList();
        for (String field: fields) {
            if (!validFields.contains(field)) {
                throw new DatahubRequestUnprocessableException("Invalid Fields Name");
            }
        }

        var option = request.getOptions(0);
        if (option.getKey().isEmpty()) {
            throw new DatahubRequestMissingRequiredParameterException("Options Missing with DatesRequest");
        } else if (option.getValue().isEmpty()) {
            throw new DatahubRequestIncorrectFormattedException("Accepts only MovieAvailableYears");
        }

    }

    public void validateRequest(DhDataRequestV1 request) throws DatahubRequestMissingRequiredParameterException,
            DatahubRequestUnprocessableException {
        if (request == null) {
            throw new DatahubRequestMissingRequiredParameterException("request");
        }

        validateFields(request);
        validateOptionsFilter(request);
        validatePeriodFilter(request);
    }

    private void validateFields(DhDataRequestV1 request) throws DatahubRequestMissingRequiredParameterException,
            DatahubRequestUnprocessableException {
        var fields = request.getFieldsList();
        if (fields.isEmpty())
        {
            throw new DatahubRequestMissingRequiredParameterException("fields");
        }

        var validFields = Constants.validFields;
        for (String field: fields
             ) {
            if (!validFields.contains(field)) {
                throw new DatahubRequestUnprocessableException("Invalid field name: " + field);
            }
        }
    }

    private void validateOptionsFilter(DhDataRequestV1 request) throws DatahubRequestUnprocessableException {

        var options = request.getOptionsList();
        var validOptionKeys = Constants.validOptionKeys;
        var validPeriodValues = Constants.validPeriodValues;

        if (options != null)
        {
            for (DhDataRequestV1.Option option: options) {
                String key = option.getKey();
                String val = option.getValue();

                if (!validOptionKeys.contains(key)) {
                    throw new DatahubRequestUnprocessableException("Invalid option key name: " + key);
                }

                if (key == "Period" && !validPeriodValues.contains(val)) {
                    throw new DatahubRequestUnprocessableException("Invalid value for Period: " + val);
                }

            }
        }
    }

    private void validateYearFormat(int date) throws DatahubRequestIncorrectFormattedException {
        try {
            LocalDate.parse(String.valueOf(date),
                    DateTimeFormatter.ofPattern("uuuu")
                            .withResolverStyle(ResolverStyle.STRICT)
            );

        } catch (DateTimeParseException e) {
            throw new DatahubRequestIncorrectFormattedException("Invalid dates, year should follow yyyy format");
        }
    }

    private void validateQuarterFormat(int quarter) throws DatahubRequestIncorrectFormattedException {
        try {
            LocalDate.parse(String.valueOf(quarter),
                    DateTimeFormatter.ofPattern("uuuuMM")
                            .withResolverStyle(ResolverStyle.STRICT)
            );

        } catch (DateTimeParseException e) {
            throw new DatahubRequestIncorrectFormattedException("Invalid dates, year should follow yyyy format");
        }
    }

    private void validatePeriodFilter(DhDataRequestV1 request) {
        int sDate = request.getSdate();
        int eDate = request.getEdate();
        List<Integer> dates = request.getDatesList();

    }

}
