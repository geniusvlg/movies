package com.fact.nash.mapper;

import com.fact.nash.projection.dto.MovieV1Dto;
import com.fact.nash.projection.dto.MovieV2Dto;
import com.fact.nash.projection.view.MovieV1View;
import com.fact.nash.projection.view.MovieV2View;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieV2Mapper {

    public List<MovieV2Dto> convertViewsToDtos(List<MovieV2View> movieViews) {

        return null;
    }
}
