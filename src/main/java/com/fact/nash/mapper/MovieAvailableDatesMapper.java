package com.fact.nash.mapper;

import com.fact.nash.projection.dto.MovieAvailableDatesDto;
import com.fact.nash.projection.dto.MovieV1Dto;
import com.fact.nash.projection.view.MovieAvailableDatesView;
import com.fact.nash.projection.view.MovieV1View;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Component
public class MovieAvailableDatesMapper {

    public List<MovieAvailableDatesDto> convertViewToDto(List<MovieAvailableDatesView> movieAvailableDatesViews) {
        ListIterator<MovieAvailableDatesView> iter = movieAvailableDatesViews.listIterator();
        List<MovieAvailableDatesDto>movieAvailableDatesDtos = new ArrayList<>();

        int index = 1;
        while (iter.hasNext()) {
            MovieAvailableDatesView movie = iter.next();
            MovieAvailableDatesDto movieAvailableDatesDto = new MovieAvailableDatesDto(
                    index, movie.getYear());

            movieAvailableDatesDtos.add(movieAvailableDatesDto);
            index++;
        }

        return movieAvailableDatesDtos;
    }
}
