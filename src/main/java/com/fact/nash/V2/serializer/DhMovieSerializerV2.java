package com.fact.nash.V2.serializer;

import com.fact.nash.projection.dto.MovieAvailableDatesDto;
import com.fact.nash.projection.dto.MovieV2Dto;
import com.fact.nash.proto.core.DhDataColumnV1;
import com.fact.nash.proto.core.DhDataVectorVariantV1;
import com.fact.nash.proto.core.DhResponseHeaderV1;
import com.fact.nash.proto.general.DhDataResponseV1;
import com.fact.nash.utils.ClassProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DhMovieSerializerV2 {

    public DhDataResponseV1 parseToDatahubResponseMovieAvailableYears (
            List<MovieAvailableDatesDto> availableYearsInMoviesTable, List<String> fields) {

        var responseBuilder = DhDataResponseV1.newBuilder();
        var header = DhResponseHeaderV1.newBuilder();
        availableYearsInMoviesTable.forEach(movieDto -> header.addIds(String.valueOf(movieDto.getOrderNumber())));

        for (String field: fields) {
            var column = DhDataColumnV1.newBuilder();
            var vec = DhDataVectorVariantV1.newBuilder();
            column.setField(field);

            for (MovieAvailableDatesDto movieDto: availableYearsInMoviesTable) {
                var data = ClassProperty.getProperty(movieDto, field, Long.MIN_VALUE);
                vec.setType(DhDataVectorVariantV1.Type.INT64);
                vec.addInt64Value(data);
            }
            column.setData(vec);
            responseBuilder.addColumns(column.build());
        }

        responseBuilder.setHeader(header);
        return responseBuilder.build();
    }

    public DhDataResponseV1 parseToDatahubResonseMovieV2 (List<MovieV2Dto> movieV2Dtos, List<Long> dates,
                                                          List<String> fields) {
        return null;
    }
}
