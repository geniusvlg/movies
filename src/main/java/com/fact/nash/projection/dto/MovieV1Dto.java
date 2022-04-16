package com.fact.nash.projection.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
public class MovieV1Dto {

    long rank;

    long movieVendorId;

    String title;

    Set<String> productionCompany;

    Set<String> domesticDistributor;

    Double domesticLifetimeGross;

    Double productionBudget;

    Date domesticReleaseDate;

    Date internetReleaseDate;

    String videoReleaseType;

    Long theaterCount;

    Double boxOffice;

}
