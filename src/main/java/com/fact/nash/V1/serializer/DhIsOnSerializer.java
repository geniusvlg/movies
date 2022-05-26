package com.fact.nash.V1.serializer;

import com.fact.nash.projection.dto.CompanyDto;
import com.fact.nash.proto.core.DhDataColumnV1;
import com.fact.nash.proto.core.DhDataVectorVariantV1;
import com.fact.nash.proto.core.DhResponseHeaderV1;
import com.fact.nash.proto.general.DhDataResponseV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DhIsOnSerializer {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    public DhDataResponseV1 parseToDatahubResponse(String ids, List<CompanyDto> companyDTO1s) {

        List<String> idArrays = Arrays.asList(ids.split(","));
        var responseBuilder = DhDataResponseV1.newBuilder();
        var column = DhDataColumnV1.newBuilder();
        column.setField("isOn");
        var header = DhResponseHeaderV1.newBuilder().addAllIds(idArrays).build();
        var data = DhDataVectorVariantV1.newBuilder();
        data.setType(DhDataVectorVariantV1.Type.BOOL);

        for (String id: idArrays) {
            Optional<CompanyDto> company = companyDTO1s.stream().filter(p -> p.getRawTicker().equals(id)).findFirst();

            if (!company.isEmpty()) {
                data.addBoolValue(true);
            } else {
                data.addBoolValue(false);
            }
        }

        column.setData(data);
        responseBuilder.addColumns(column.build());
        responseBuilder.setHeader(header);

        return responseBuilder.build();
    }
}
