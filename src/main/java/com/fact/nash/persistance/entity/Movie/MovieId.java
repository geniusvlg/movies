package com.fact.nash.persistance.entity.Movie;

import java.io.Serializable;
import java.util.Objects;

public class MovieId implements Serializable {

    private long movieOdid;
    public int factsetContentId;
    public int regionId;

    private MovieId() {}

    public MovieId(long movieOdid, int factsetContentId, int regionId) {
        this.movieOdid = movieOdid;
        this.factsetContentId = factsetContentId;
        this.regionId = regionId;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        MovieId movieId = (MovieId) o;
        return movieOdid == movieId.movieOdid && factsetContentId == movieId.factsetContentId
                && regionId == movieId.regionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash( movieOdid, factsetContentId, regionId);
    }
}
