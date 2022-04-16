package com.fact.nash.V2.service;

import com.fact.nash.projection.view.MovieAvailableDatesView;
import com.fact.nash.projection.view.MovieV2View;
import com.fact.nash.projection.view.WeekendDatesView;
import com.fact.nash.projection.view.WeekendMovieView;
import com.fact.nash.proto.general.DhDataRequestV1;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface MovieServiceV2 {

    List<MovieV2View> fetchAnnualMovies(List<String> fields, List<Long> date, Map<String, String> options);

    List<MovieV2View> fetchQuarterlyMovies(List<String> fields, List<Long> dates, Map<String, String> options);

    List<WeekendDatesView> fetchOnlyWeekendDates(DhDataRequestV1 request, int totalWeekendDatesToFetch);

    List<WeekendMovieView> fetchWeekendMovie(List<Long> WDates);

    List<MovieAvailableDatesView> fetchOnlyMovieAvailableDates(int startYearsToFetch);
}
