package com.fact.nash.projection.view;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MovieV1View {

    String getMovieName();

    Double getProdBudget();

    long getMovieOdid();

    String getVideoReleaseType();

    Long getTheaterCount();

    String getDistributorName();

    String getCompanyName();

    Double getBoxOffice();

    Double getGrossIncomeLocal();

    Date getReleaseDate();

    Date getInternetReleaseDate();

}
