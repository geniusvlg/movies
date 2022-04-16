package com.fact.nash.persistance.entity.DailyMovieMetrics;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "daily_movie_metrics")
@Getter
@Setter
public class DailyMovieMetrics implements Serializable {

    @Id
    public int movieOdid;

    @Id
    public int regionId;

    @Id
    public Date chartDate;

    @Id
    public String chartType;

    @Id
    public Date recordTime;

    @Id
    public int daysInRelease;

    public Double revenue;

    public Integer ticketsSold;

    public Double totalRevenue;

    public Integer totalTicketsSold;
}
