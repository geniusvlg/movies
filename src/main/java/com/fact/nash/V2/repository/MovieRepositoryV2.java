package com.fact.nash.V2.repository;

import com.fact.nash.persistance.entity.Movie.Movie;
import com.fact.nash.projection.view.MovieAvailableDatesView;
import com.fact.nash.projection.view.MovieV2View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MovieRepositoryV2 extends JpaRepository<Movie, Integer> {

    int northAmericaId = 20308;
    String allSymbols = "@ALL";
    String keyDistributor = "Distributor";
    String keyPeriod = "Period";
    String keyGeograpgy = "Geography";

    @Modifying
    @Query(
            value = "SELECT DISTINCT m.year FROM tmt_nash1_dev.annual_movie_metrics AS m " +
                    "WHERE m.region_id = :northAmericaId AND m.year > :startYearToFetch " +
                    "ORDER BY m.year DESC ",
            nativeQuery = true
    )
    List<MovieAvailableDatesView> fetchOnlyMovieAvailableDates(int startYearToFetch);

    @Modifying
    @Query(
            value = "SELECT m.movie_name AS Title, m.movie_odid AS MovieVendorId FROM tmt_nash1_dev.movie AS m " +
                    "WHERE m.movie_name = 'Titanic'",
            nativeQuery = true
    )
    List<MovieV2View> fetchAnnualDataMovies(List<String> fields, List<Long> dates, String geography,
                                            String distributor);

    @Modifying
    @Query(
            value = "SELECT m.movie_name AS Title, m.movie_odid AS MovieVendorId FROM tmt_nash1_dev.movie",
            nativeQuery = true
    )
    List<MovieV2View> fetchQuarterlyDataMovies(List<String> fields, List<Long> dates, String geography,
                                               String distributor);
}
