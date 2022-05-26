package com.fact.nash.V1.service.Impl;

import com.fact.nash.V1.service.MovieServiceV1;
import com.fact.nash.V1.repository.MovieRepositoryV1;
import com.fact.nash.mapper.MovieV1Mapper;
import com.fact.nash.projection.dto.MovieV1Dto;
import com.fact.nash.projection.view.MovieV1View;
import com.fact.nash.proto.general.DhDataRequestV1;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieServiceV1Impl implements MovieServiceV1 {

    private final String KEY_GEOGRAPHY = "Geography";

    private final String KEY_PERIOD = "Period";

    private final String KEY_DISTRBUTOR = "Distributor";

    private final int NORTH_AMERICA_ID = 20308;

    @Autowired
    MovieRepositoryV1 movieRepositoryV1;

    @Override
    public List<MovieV1Dto> fetchMovies(DhDataRequestV1 request, int limit) {
        List<MovieV1View> movies = movieRepositoryV1.fetchMoviesByYear(NORTH_AMERICA_ID, 2017, 100);
        MovieV1Mapper movieV1Mapper = new MovieV1Mapper();

        List<MovieV1Dto> res = movieV1Mapper.convertViewsToDtos(movies);

        return res.subList(0 , limit);
    }

}
