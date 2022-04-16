package com.fact.nash.persistance.repository;

import com.fact.nash.persistance.entity.WhitelistedCompanies.WhitelistedCompanies;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WhitelistedCompaniesRepository extends CrudRepository<WhitelistedCompanies, Integer> {

    @Modifying
    @Query(
            "SELECT com FROM WhitelistedCompanies com WHERE com.rawTicker IN (:tickers)"
    )
    List<WhitelistedCompanies> findByRawTickers(List<String> tickers);
}
