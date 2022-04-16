package com.fact.nash.persistance.entity.DistributionCompany;

import com.google.type.DateTime;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "distributor_company")
@Setter
@Getter
public class DistributionCompany implements Serializable {

    @Id
    public int distributorId;

    @Id
    public int factsetContentId;

    @Id
    public int regionId;

    @Id
    public DateTime recordTime;

    public String distributorName;
}
