package com.fact.nash.V2.controller;

import com.fact.nash.V2.serializer.DhMovieSerializerV2;
import com.fact.nash.V2.service.MovieServiceV2;
import com.fact.nash.exceptions.DatahubRequestIncorrectFormattedException;
import com.fact.nash.exceptions.DatahubRequestMissingRequiredParameterException;
import com.fact.nash.exceptions.DatahubRequestUnprocessableException;
import com.fact.nash.proto.general.DhDataRequestV1;
import com.fact.nash.proto.general.DhDataResponseV1;
import com.fact.nash.validator.RequestValidator;
import com.googlecode.protobuf.format.JsonFormat;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v2/movie")
public class MovieControllerV2 {

    @Autowired
    MovieServiceV2 movieServiceV2;

    @Autowired
    DhMovieSerializerV2 dhMovieSerializerV2;


    @PostMapping
    ResponseEntity<?> fetchMovies(@RequestBody DhDataRequestV1 request) throws
            DatahubRequestMissingRequiredParameterException, DatahubRequestUnprocessableException,
            DatahubRequestIncorrectFormattedException {

        DhDataResponseV1 responseV1 = movieServiceV2.fetchMovies(request);
        JsonFormat jsonFormat = new JsonFormat();
        String jsonString = "";
        jsonString = jsonFormat.printToString(responseV1);

        return ResponseEntity.ok(jsonString);
    }

    @PostMapping("/weekend")
    ResponseEntity<?> fetchWeekendMovies(@RequestBody DhDataRequestV1 request) {

        return null;
    }

    @PostMapping("/release-schedule")
    ResponseEntity<?> fetchReleaseSchedule(@RequestBody DhDataRequestV1 request) {

        return null;
    }


}
