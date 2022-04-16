package com.fact.nash.V1.service.Impl;

import com.fact.nash.V1.service.MovieServiceV1;
import com.fact.nash.persistance.repository.MovieRepository;
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
    MovieRepository movieRepository;

    @Override
    public List<MovieV1Dto> fetchMovies(DhDataRequestV1 request, int limit) {

        List<MovieV1View> movies = movieRepository.fetchMoviesByYear(NORTH_AMERICA_ID, 2017, 100);

        Map<Long, MovieV1Dto> map = new LinkedHashMap<>();
        ListIterator<MovieV1View> iter = movies.listIterator();

        int index = 1;
        while (iter.hasNext()) {
            MovieV1View movie = iter.next();
            long movieOdid = movie.getMovieOdid();

            if (!map.containsKey(movieOdid)) {
                MovieV1Dto movieDto = new MovieV1Dto();

                movieDto.setRank(index);
                movieDto.setMovieVendorId(movieOdid);
                movieDto.setTitle(movie.getMovieName());
                movieDto.setTheaterCount(movie.getTheaterCount());
                movieDto.setProductionBudget(movie.getProdBudget());
                movieDto.setDomesticLifetimeGross(movie.getGrossIncomeLocal());
                movieDto.setDomesticReleaseDate(movie.getReleaseDate());
                movieDto.setInternetReleaseDate(movie.getInternetReleaseDate());
                movieDto.setVideoReleaseType(movie.getVideoReleaseType());
                movieDto.setBoxOffice(movie.getBoxOffice());
                movieDto.setProductionCompany(new HashSet<String>(){{
                    add(movie.getCompanyName());
                }});
                movieDto.setDomesticDistributor(Sets.newHashSet(movie.getDistributorName()));
                map.put(movieOdid, movieDto);
                index++;

            } else {
                MovieV1Dto currMovieDto = map.get(movieOdid);
                currMovieDto.getProductionCompany().add(movie.getCompanyName());
                map.put(movieOdid, currMovieDto);
            }
        }

        List<MovieV1Dto> res = new ArrayList<>(map.values());

        return res.subList(0 , limit);
    }

}
