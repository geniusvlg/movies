package com.fact.nash.persistance.entity.IndustryIdentifier;

import com.google.type.DateTime;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "industry_identifier")
@Getter
@Setter
public class IndustryIdentifier implements Serializable {

    @Id
    public int factsetContentId;

    @Id
    public DateTime recordTime;

    public String comments;

    public String companyName;

    public String entityId;

    public String factsetInd;

    public Integer factsetIndNumber;

    public String factsetIndustryTicker;

    public String industryName;

    public String name;

    public String symbol;

    public String ticker;
}
