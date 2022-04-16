package com.fact.nash.persistance.entity.AnnualDistributorMetrics;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "annual_distributor_metrics")
@Getter
@Setter
public class AnnualDistributorMetrics implements Serializable {
    @Id
    public int distributorId;

    @Id
    public int regionId;

    @Id
    public int year;

    @Id
    public Date recordTime;

    public Double averageTicketPrice;

    public Double percentChange;

    public Double totalBoxOffice;

    public Integer totalMovieReleased;

    public Integer totalTicketsSold;
}
