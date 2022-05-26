package com.fact.nash.mapper;

import com.fact.nash.projection.dto.MovieV1Dto;
import com.fact.nash.projection.view.MovieV1View;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;

import java.util.*;

public class MovieV1Mapper {

    public List<MovieV1Dto> convertViewsToDtos(List<MovieV1View> movieViews) {
        Map<Long, MovieV1Dto> map = new LinkedHashMap<>();
        ListIterator<MovieV1View> iter = movieViews.listIterator();

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
        return res;
    }

}
