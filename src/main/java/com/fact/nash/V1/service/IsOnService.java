package com.fact.nash.V1.service;

import com.fact.nash.projection.dto.CompanyDto;
import com.fact.nash.exceptions.DatahubRequestMissingRequiredParameterException;

import java.util.List;

public interface IsOnService {

    List<CompanyDto> findCompaniesByEntityId(String ids) throws DatahubRequestMissingRequiredParameterException;
}
