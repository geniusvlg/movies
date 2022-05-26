package com.fact.nash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Constants {

    public static final String defaultGeography = "20308";    // 20308 id is for Domestic/North America
    public static final String defaultPeriod = "Annual";
    public static final String defaultDistributor = "@ALL";
    public static final String defaultDate = "Date";
    public static final List<String> validOptionKeys = Arrays.asList(
            "Geography",
            "Distributor",
            "Period"
    );
    public static  final List<String> validPeriodValues = Arrays.asList("Annual", "Quarterly");

    public static final List<String> supportedMovieDatesFields = Arrays.asList(
            "OrderNumber",
            "MovieYears"
    );

    public static final List<String> validFields = Arrays.asList(
            "MovieVendorId",
            "Title",
            "ProductionCompany",
            "DomesticDistributor",
            "DomesticReleaseDate",
            "InternetReleaseDate",
            "VideoReleaseType",
            "TotalBoxOffice",
            "TicketsSold",
            "YearEndTotal",
            "QuarterEndTotal",
            "InternetPlatform",
            "MaxTheaterCount"
    );

    public static final List<String> validReleaseScheduleDateFieldsv = Arrays.asList(
            "Date",
            "OrderNumber"
    );

    public static final List<String> validReleaseScheduleFields = Arrays.asList(
            "MovieVendorId",
            "Title",
            "MovieReleasePattern",
            "ProductionCompany",
            "DomesticDistributor",
            "DomesticReleaseDate",
            "InternetReleaseDate",
            "VideoReleaseType",
            "InternetPlatform",
            "ProductionBudget"
    );

    public static final List<String> supportedWeekend3DFields = Arrays.asList(
            "MovieVendorId",
            "Title",
            "ProductionCompany",
            "DomesticDistributor",
            "WeekendTicket",
            "WeekendGross",
            "TotalTicket",
            "TotalBoxOffice",
            "ProductionBudget",
            "WeekNumber",
            "ReleaseDate",
            "TheaterCount",
            "PerTheaterRevenue",
            "MaxTheaterCount"
    );




}
