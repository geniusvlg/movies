package com.fact.nash.persistance.entity.Region;

import com.google.type.DateTime;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "region")
@Getter
@Setter
public class Region implements Serializable {
    @Id
    public int regionId;

    @Id
    public DateTime recordTime;

    public String currency;

    public String iso4217CodeAlpha;

    public Integer iso4217CodeNumeric;

    public String regionName;
}
