package com.fact.nash.V1.controller;

import com.fact.nash.V1.processor.MovieProcessor;
import com.fact.nash.V1.service.MovieServiceV1;
import com.fact.nash.projection.dto.MovieV1Dto;
import com.fact.nash.projection.view.MovieV1View;
import com.fact.nash.exceptions.DatahubRequestIncorrectFormattedException;
import com.fact.nash.proto.general.DhDataRequestV1;
import com.fact.nash.proto.general.DhDataResponseV1;
import com.fact.nash.validator.RequestValidator;
import com.googlecode.protobuf.format.JsonFormat;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movie/fetch")
public class MovieControllerV1 {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MovieServiceV1 movieServiceV1;

    @Autowired
    RequestValidator requestValidator;

    @Autowired
    MovieProcessor movieProcessor;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> fetch(@RequestBody DhDataRequestV1 request) throws IOException, ParseException,
            NoSuchFieldException, IllegalAccessException {

        requestValidator.validateMovieRequest(request);

        List<MovieV1Dto> movies = movieServiceV1.fetchMovies(request, 100);

        DhDataResponseV1 responseV1 = movieProcessor.parseToDatahubResponse(request.getDatesList(), movies);
        JsonFormat jsonFormat = new JsonFormat();
        String jsonString = "";
        jsonString = jsonFormat.printToString(responseV1);

        return ResponseEntity.ok(jsonString);
    }

    @RequestMapping(value = "/{year}" ,method = RequestMethod.GET)
     ResponseEntity<String> get(@PathVariable String year) throws DatahubRequestIncorrectFormattedException {
        try {
            int num = Integer.parseInt(year);
        } catch (NumberFormatException e) {
            throw new DatahubRequestIncorrectFormattedException("Year should be number");
        }

        return null;
    }
}
