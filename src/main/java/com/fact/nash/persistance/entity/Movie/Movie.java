package com.fact.nash.persistance.entity.Movie;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "MOVIE")
@Getter
@Setter
@IdClass(MovieId.class)
public class Movie implements Serializable{
    @Id
    private long movieOdid;

    @Id
    public int factsetContentId;

    @Id
    public int regionId;

    public Date recordTime;

    public Double grossIncomeLocal;

    public Double grossIncomeUsd;

    public String movieName;

    public Double prodBudget;

    public Date releaseDate;

    public String videoReleaseType;
}
