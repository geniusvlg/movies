package com.fact.nash.persistance.entity.DistributionCompanyDistributedBy;

import com.google.type.DateTime;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "distribution_company_distributed_by")
@Getter
@Setter
public class DistributionCompanyDistributedBy implements Serializable {

    @Id
    public int movieOdid;

    @Id
    public int distributorId;

    @Id
    public int regionId;

    @Id
    public DateTime recordTime;

    public String dummyAttrib;
}
