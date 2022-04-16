package com.fact.nash.mapper;

import com.fact.nash.persistance.entity.WhitelistedCompanies.WhitelistedCompanies;
import com.fact.nash.projection.dto.CompanyDto;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {}
)
public abstract class CompanyMapper {

    public abstract WhitelistedCompanies convertToEntity(CompanyDto companyDto);

    public abstract CompanyDto convertToDto(WhitelistedCompanies whitelistedCompanies);

    public List<CompanyDto> convertToDtos(final List<WhitelistedCompanies> whitelistedCompaniesList) {
        if (whitelistedCompaniesList == null)
            return null;

        final List<CompanyDto> list = new ArrayList<CompanyDto>(whitelistedCompaniesList.size());
        for (final WhitelistedCompanies whitelistedCompany: whitelistedCompaniesList) {
            list.add(this.convertToDto(whitelistedCompany));
        }

        return list;
    }
}
