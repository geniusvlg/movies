package com.fact.nash.persistance.entity.AnnualMovieMetric;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "annual_movie_metrics")
@Getter
@Setter
public class AnnualMovieMetric implements Serializable {

    @Id
    public long movieOdid;

    @Id
    public long regionId;

    @Id
    public Date recordTime;

    @Column(nullable = true)
    public double boxOffice;

    @Column(nullable = true)
    public long ticketSold;

    @Column(nullable = true)
    public double yearEndTotal;
}
