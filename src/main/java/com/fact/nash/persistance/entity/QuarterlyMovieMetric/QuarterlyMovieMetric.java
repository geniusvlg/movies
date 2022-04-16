package com.fact.nash.persistance.entity.QuarterlyMovieMetric;

import com.google.type.DateTime;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "quarterly_movie_metric")
@Getter
@Setter
public class QuarterlyMovieMetric implements Serializable {

    @Id
    public int movieOdid;

    @Id
    public int regionId;

    @Id
    public int year;

    @Id
    public int quarter;

    @Id
    public DateTime recordTime;

    public Double boxOffice;

    public Double quarterEndTotal;

    public Integer ticketsSold;
}
