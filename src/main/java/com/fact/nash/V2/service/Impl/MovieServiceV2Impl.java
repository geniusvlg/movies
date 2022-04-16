package com.fact.nash.V2.service.Impl;

import com.fact.nash.V2.service.MovieServiceV2;
import com.fact.nash.projection.view.MovieAvailableDatesView;
import com.fact.nash.projection.view.MovieV2View;
import com.fact.nash.projection.view.WeekendDatesView;
import com.fact.nash.projection.view.WeekendMovieView;
import com.fact.nash.proto.general.DhDataRequestV1;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MovieServiceV2Impl implements MovieServiceV2 {

    /**
     * [ MVP2 ]
     * Fields BoxOffice, TicketsSold, and YearEndTotal will be dependent on the geography from the request.
     * Other fields will always show data for North America/Domestic only
     * @param fields
     * @param date
     * @param options
     * @return
     */
    @Override
    public List<MovieV2View> fetchAnnualMovies(List<String> fields, List<Long> date, Map<String, String> options) {


        return null;
    }

    @Override
    public List<MovieV2View> fetchQuarterlyMovies(List<String> fields, List<Long> dates, Map<String, String> options) {
        return null;
    }

    @Override
    public List<WeekendDatesView> fetchOnlyWeekendDates(DhDataRequestV1 request, int totalWeekendDatesToFetch) {
        return null;
    }

    @Override
    public List<WeekendMovieView> fetchWeekendMovie(List<Long> WDates) {
        return null;
    }

    @Override
    public List<MovieAvailableDatesView> fetchOnlyMovieAvailableDates(int startYearsToFetch) {
        return null;
    }
}
