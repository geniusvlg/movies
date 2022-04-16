package com.fact.nash.persistance.entity.QuarterlyDistributorMetric;

import com.google.type.DateTime;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "quarterly_distributor_metric")
@Getter
@Setter
public class QuarterlyDistributorMetric implements Serializable {

    @Id
    public int distributorId;

    @Id
    public int regionId;

    @Id
    public int year;

    @Id
    public int quarter;

    @Id
    public DateTime recordTime;

    public Double averageTicketPrice;

    public Double percentChange;

    public Double totalBoxOffice;

    public Integer totalMovieReleased;

    public Integer totalTicketsSold;

}