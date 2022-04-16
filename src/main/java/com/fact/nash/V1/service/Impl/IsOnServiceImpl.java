package com.fact.nash.V1.service.Impl;

import com.fact.nash.mapper.CompanyMapper;
import com.fact.nash.persistance.entity.WhitelistedCompanies.WhitelistedCompanies;
import com.fact.nash.projection.dto.CompanyDto;
import com.fact.nash.V1.service.IsOnService;
import com.fact.nash.exceptions.DatahubRequestMissingRequiredParameterException;
import com.fact.nash.persistance.repository.WhitelistedCompaniesRepository;
import com.fact.nash.validator.RequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class IsOnServiceImpl implements IsOnService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private WhitelistedCompaniesRepository whitelistedCompaniesRepository;

    @Autowired
    RequestValidator requestValidator;

    @Override
    public List<CompanyDto> findCompaniesByEntityId(String ids) throws DatahubRequestMissingRequiredParameterException {

        requestValidator.validateIsOnRequest(ids);
        List<String> idArrays = Arrays.asList(ids.split(","));
        List<WhitelistedCompanies> companies = whitelistedCompaniesRepository.findByRawTickers(idArrays);

        return this.companyMapper.convertToDtos(companies);
    }

}
