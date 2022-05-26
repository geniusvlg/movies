package com.fact.nash.V1.controller;

import com.fact.nash.V1.serializer.DhIsOnSerializer;
import com.fact.nash.V1.service.IsOnService;
import com.fact.nash.exceptions.BaseException;
import com.fact.nash.exceptions.DatahubRequestMissingRequiredParameterException;
import com.fact.nash.projection.dto.CompanyDto;
import com.fact.nash.proto.general.DhDataResponseV1;
import com.googlecode.protobuf.format.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/is-on/check")
@PreAuthorize("hasAuthority('SCOPE_nash-resource/read')")
public class IsOnController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private DhIsOnSerializer dhIsOnSerializer = new DhIsOnSerializer();

    @Autowired
    private IsOnService isOnService;

    @GetMapping("/{companyId}")
    ResponseEntity<String> getCompanyIsOn(@PathVariable String companyId) throws BaseException {
        if (companyId.equals("")) {
            throw new DatahubRequestMissingRequiredParameterException("Empty company id");
        }

        List<CompanyDto> companyDTOList = isOnService.findCompaniesByEntityId(companyId);
        DhDataResponseV1 responseV1 = dhIsOnSerializer.parseToDatahubResponse(companyId, companyDTOList);

        JsonFormat jsonFormat = new JsonFormat();
        String jsonString = "";
        jsonString = jsonFormat.printToString(responseV1);
        return ResponseEntity.ok(jsonString);
    }

}

