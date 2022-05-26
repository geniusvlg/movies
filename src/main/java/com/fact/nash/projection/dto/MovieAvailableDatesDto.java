package com.fact.nash.projection.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieAvailableDatesDto {

    public MovieAvailableDatesDto(long orderNumber, long movieYears) {
        this.orderNumber = orderNumber;
        this.movieYears = movieYears;
    }

    long orderNumber;

    long movieYears;
}
