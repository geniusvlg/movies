package com.fact.nash.persistance.entity.MovieInternetPlatformsInternetReleasedBy;

import com.google.type.DateTime;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "movie_internet_platforms_internet_released_by")
@Getter
@Setter
public class MovieInternetPlatformsInternetReleasedBy implements Serializable {

    @Id
    public int MovieOdid;

    @Id
    public int InternetPlatformId;

    @Id
    public int RegionId;

    @Id
    public DateTime RecordTime;

    public String DummyAttrib;

    public DateTime InternetReleaseDate;
}
