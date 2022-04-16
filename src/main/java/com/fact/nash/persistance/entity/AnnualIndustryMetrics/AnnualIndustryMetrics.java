package com.fact.nash.persistance.entity.AnnualIndustryMetrics;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "annual_industry_metrics")
@Getter
@Setter
public class AnnualIndustryMetrics {

    @Id
    public long regionId;

    public long year;

    public Date recordDate;
}
