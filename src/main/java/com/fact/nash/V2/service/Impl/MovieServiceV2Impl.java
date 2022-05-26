package com.fact.nash.V2.service.Impl;

import com.fact.nash.Constants;
import com.fact.nash.V2.repository.MovieRepositoryV2;
import com.fact.nash.V2.serializer.DhMovieSerializerV2;
import com.fact.nash.V2.service.MovieServiceV2;
import com.fact.nash.exceptions.DatahubRequestIncorrectFormattedException;
import com.fact.nash.exceptions.DatahubRequestMissingRequiredParameterException;
import com.fact.nash.exceptions.DatahubRequestUnprocessableException;
import com.fact.nash.mapper.MovieAvailableDatesMapper;
import com.fact.nash.mapper.MovieV2Mapper;
import com.fact.nash.projection.dto.MovieAvailableDatesDto;
import com.fact.nash.projection.dto.MovieV2Dto;
import com.fact.nash.projection.view.MovieAvailableDatesView;
import com.fact.nash.projection.view.MovieV2View;
import com.fact.nash.projection.view.WeekendDatesView;
import com.fact.nash.projection.view.WeekendMovieView;
import com.fact.nash.proto.general.DhDataRequestV1;
import com.fact.nash.proto.general.DhDataResponseV1;
import com.fact.nash.utils.DateResolution;
import com.fact.nash.validator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieServiceV2Impl implements MovieServiceV2 {

    private final String  onlyAvailableMovieYears = "MovieAvailableYears";
    private int regionId = 20308;
    private HashMap<String, String> options;
    private final String keyGeography = "geography";
    private final String keyPeriod = "period";
    private final String keyDistributor = "distributor";
    private final String valueAnnual = "annual";
    private final String valueQuarterly = "quarterly";

    @Autowired
    RequestValidator requestValidator;

    @Autowired
    MovieRepositoryV2 movieRepositoryV2;

    @Autowired
    DhMovieSerializerV2 serializerV2;

    @Autowired
    MovieAvailableDatesMapper movieAvailableDatesMapper;

    @Autowired
    MovieV2Mapper movieV2Mapper;

    /**
     * [ MVP2 ]
     * Fields BoxOffice, TicketsSold, and YearEndTotal will be dependent on the geography from the request.
     * Other fields will always show data for North America/Domestic only
     * @param request
     * @return
     */
    @Override
    public DhDataResponseV1 fetchMovies(DhDataRequestV1 request) throws DatahubRequestMissingRequiredParameterException
            , DatahubRequestUnprocessableException, DatahubRequestIncorrectFormattedException {

        String distributor = "10101";
        Integer geography = Integer.parseInt("20308");

        boolean isRequestForAvailableMovieYear = checkIsRequestForAvailableMovieYear(request);

        if (isRequestForAvailableMovieYear) {
            requestValidator.validateDateRequest(request);
            int startYearToFetch = Integer.parseInt(System.getenv("YearsToFetchFrom"));
            List<MovieAvailableDatesView> availableYearsInMoviesTable = movieRepositoryV2.fetchOnlyMovieAvailableDates(
                    startYearToFetch);

            List<MovieAvailableDatesDto> availableYearsInMovies = movieAvailableDatesMapper.convertViewToDto(
                    availableYearsInMoviesTable);

            return serializerV2.parseToDatahubResponseMovieAvailableYears(availableYearsInMovies,
                    request.getFieldsList());

        } else {
            requestValidator.validateRequest(request);
            setDefaultOptionParameters();
            parseRequestOptions(request.getOptionsList());
            List<Integer> dateList = request.getDatesList();
            List<String> fields = request.getFieldsList();
            List<Long> annualDates;
            List<Long> quarterlyDates;
            List<Long> dates = null;
            var datesList = request.getDatesList();
            List<MovieV2Dto> movieV2Dtos;
            List<MovieV2View> movieV2Views = null;

            if (dateList.size() > 0) {
                annualDates = DateResolution.parsingDatesFromDhRequest(datesList);
                dates = annualDates;
            }
            if (this.options.get(keyPeriod).toLowerCase().equals(valueAnnual)) {
                if (dateList.size() > 0) {
                    annualDates = DateResolution.getResolvedAnnualDates(request.getSdate(), request.getEdate());
                    dates = annualDates;
                }

                movieV2Views = movieRepositoryV2.fetchAnnualDataMovies(fields, dates,
                        this.options.get("geography"), this.options.get("distributor"));
            } else if (this.options.get(keyPeriod).toLowerCase().equals(valueQuarterly)) {
                if (dateList.size() > 0) {
                    quarterlyDates = DateResolution.getResolvedQuarterlyDates(request.getSdate(), request.getEdate());
                    dates = quarterlyDates;
                }
                movieV2Views = movieRepositoryV2.fetchQuarterlyDataMovies(fields, dates,
                        this.options.get("geography"), this.options.get("distributor"));
            }

            movieV2Dtos = movieV2Mapper.convertViewsToDtos(movieV2Views);

            return serializerV2.parseToDatahubResonseMovieV2(movieV2Dtos, dates, fields);
        }

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

    private boolean checkIsRequestForAvailableMovieYear(DhDataRequestV1 request) {
        if (request.getOptions(0).getValue().equals(onlyAvailableMovieYears)) {
            return true;
        }
        return false;
    }

    private void setDefaultOptionParameters() {
        this.options = new HashMap<String, String>();
        this.options.put(keyGeography, Constants.defaultGeography);
        this.options.put(keyPeriod, Constants.defaultPeriod);
        this.options.put(keyDistributor, Constants.defaultDistributor);
    }

    private void parseRequestOptions(List<DhDataRequestV1.Option> options) {
        for (DhDataRequestV1.Option option: options) {
            this.options.put(option.getKey().toLowerCase(), option.getValue());
        }
    }


}
