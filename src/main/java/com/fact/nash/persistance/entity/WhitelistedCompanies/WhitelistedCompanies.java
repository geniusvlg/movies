package com.fact.nash.persistance.entity.WhitelistedCompanies;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "whitelisted_companies")
@Getter
@Setter
public class WhitelistedCompanies implements Serializable {

    @Id
    public int factsetContentId;

    public String companyName;

    public String entityId;

    public String industryName;

    public Integer industryNumber;

    public String rawTicker;

    public String tickerRegion;
}
