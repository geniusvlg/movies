package com.fact.nash.projection.dto;

import com.google.type.DateTime;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieV2Dto {

    long movieVendorId;

    String title;

    List<String> productionCompany;

    List<String> domesticDistributor;

    DateTime domesticReleaseDate;

    DateTime internetReleaseDate;

    String videoReleaseType;

    String internetPlatform;

    Double totalBoxOffice;

    Long ticketsSold;

    long date;

    Double yearEndTotal;

    Double quarterEndTotal;

    Long maxTheaterCount;
}
