package com.fact.nash.V1.service;

import com.fact.nash.projection.dto.MovieV1Dto;
import com.fact.nash.projection.view.MovieV1View;
import com.fact.nash.proto.general.DhDataRequestV1;

import java.util.List;

public interface MovieServiceV1 {

    List<MovieV1Dto> fetchMovies(DhDataRequestV1 request, int limit);

}
