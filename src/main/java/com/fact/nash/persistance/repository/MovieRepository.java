package com.fact.nash.persistance.repository;

import com.fact.nash.persistance.entity.Movie.Movie;
import com.fact.nash.projection.view.MovieV1View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Modifying
    @Query(
            value = "SELECT DISTINCT m.movie_name AS MovieName, m.prod_budget AS ProdBudget, m.movie_odid AS MovieOdid, " +
                    "m.video_release_type AS VideoReleaseType, m.release_date AS ReleaseDate, " +
                    "m.gross_income_local AS GrossIncomeLocal, m.theater_count AS TheaterCount, " +
                    "m.box_office As BoxOffice, dc.distributor_name AS DistributorName, " +
                    "pc.company_name AS CompanyName, mip.internet_release_date AS InternetReleaseDate " +
                    "FROM (" +
                    "   SELECT m.movie_name, m.movie_odid, m.prod_budget, m.video_release_type, m.release_date, " +
                    "   m.gross_income_local, m.theater_count, amm.box_office " +
                    "   FROM tmt_nash1_dev.movie AS m " +
                    "   JOIN tmt_nash1_dev.annual_movie_metrics AS amm " +
                    "   ON m.movie_odid = amm.movie_odid AND m.region_id = amm.region_id " +
                    "   WHERE amm.year = :year AND m.region_id = :regionId ORDER BY amm.box_office DESC " +
                    "   LIMIT :limit) AS m " +
                    "LEFT JOIN ( " +
                    "   SELECT pc.company_name, mpcpb.movie_odid " +
                    "   FROM tmt_nash1_dev.production_company AS pc " +
                    "   JOIN tmt_nash1_dev.movie_prod_company_produced_by as mpcpb " +
                    "   ON pc.company_id = mpcpb.company_id) AS pc " +
                    "ON m.movie_odid = pc.movie_odid " +
                    "LEFT JOIN ( " +
                    "   SELECT dcdb.movie_odid, dc.distributor_name " +
                    "   FROM tmt_nash1_dev.distribution_company_distributed_by As dcdb " +
                    "   JOIN tmt_nash1_dev.distribution_company AS dc " +
                    "   ON dcdb.distributor_id = dc.distributor_id AND dcdb.region_id = dc.region_id " +
                    "   WHERE dc.region_id = :regionId) AS dc " +
                    "ON m.movie_odid = dc.movie_odid " +
                    "LEFT JOIN tmt_nash1_dev.movie_internet_platforms_internet_released_by AS mip " +
                    "ON m.movie_odid = mip.movie_odid " +
                    "ORDER BY m.box_office DESC",
            nativeQuery = true
    )
    List<MovieV1View> fetchMoviesByYear(int regionId, int year, int limit);

    List<String> findByMovieName(String movieName);
}
