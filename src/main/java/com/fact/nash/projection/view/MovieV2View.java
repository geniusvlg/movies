package com.fact.nash.projection.view;

import javax.xml.crypto.Data;
import java.util.Date;

public interface MovieV2View {

    Long getMovieVendorId();

    String getTitle();

    String getDomesticDistributor();

    Data getDomesticReleaseDate();

    String getVideoReleaseType();

    Long getTotalBoxOffice();

    Long getTicketSold();

    Long getYearEndTotal();

    Date getYear();

    Double getTheaterCount();

    String getCompanyName();

    String getDistributorName();

    String getInternetPlatformName();

    String getInternetReleaseDate();

    String getInternetPlatformsInternetReleasedBy();

}
