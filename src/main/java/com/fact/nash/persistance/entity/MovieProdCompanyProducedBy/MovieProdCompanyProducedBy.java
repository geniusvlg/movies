package com.fact.nash.persistance.entity.MovieProdCompanyProducedBy;

import com.google.type.DateTime;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "movie_prod_company_produced_by")
@Getter
@Setter
public class MovieProdCompanyProducedBy implements Serializable {

    @Id
    public int MovieOdid;

    @Id
    public int CompanyId;

    @Id
    public DateTime RecordTime;

    public String DummyAttrib;
}
