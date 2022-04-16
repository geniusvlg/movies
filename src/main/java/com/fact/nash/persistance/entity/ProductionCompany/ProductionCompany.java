package com.fact.nash.persistance.entity.ProductionCompany;

import com.google.type.DateTime;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "production_company")
@Getter
@Setter
public class ProductionCompany implements Serializable {

    @Id
    public int CompanyId;

    @Id
    public int FactsetContentId;

    @Id
    public DateTime RecordTime;

    public String CompanyName;
}
