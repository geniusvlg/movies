package com.fact.nash.V1.processor;

import com.fact.nash.projection.dto.MovieV1Dto;
import com.fact.nash.proto.core.DhDataColumnV1;
import com.fact.nash.proto.core.DhDataVectorVariantV1;
import com.fact.nash.proto.core.DhResponseHeaderV1;
import com.fact.nash.proto.general.DhDataResponseV1;
import com.fact.nash.utils.ClassProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class MovieProcessor {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

    public DhDataResponseV1 parseToDatahubResponse(List<Integer> dates, List<MovieV1Dto> movieV1Dtos) throws IllegalAccessException {

        var responseBuilder = DhDataResponseV1.newBuilder();
        var header = DhResponseHeaderV1.newBuilder();
        movieV1Dtos.forEach(movieV1Dto -> header.addIds(String.valueOf(movieV1Dto.getMovieVendorId())));

        Class<?> cls = MovieV1Dto.class;
        Field fieldlist[] = cls.getDeclaredFields();
        for (Field propertyName : fieldlist) {
            // build toString output with StringBuilder()
            var type = propertyName.getAnnotatedType().getType().getTypeName();
            var vector = DhDataVectorVariantV1.newBuilder();

            for (Integer date: dates) {
                var column = DhDataColumnV1.newBuilder();
                column.setField(this.convertToFullName(propertyName.getName()));
                column.setDate(date);

                for (MovieV1Dto movieV1Dto: movieV1Dtos) {

                    if (type.contains("Double")){
                        var data = ClassProperty.getProperty(movieV1Dto, propertyName.getName(), Double.MIN_VALUE);
                        vector.setType(DhDataVectorVariantV1.Type.DOUBLE);
                        vector.addDoubleValue(data);

                    } else if (type.equals("java.lang.Long")){
                        var data = ClassProperty.getProperty(movieV1Dto, propertyName.getName(), Long.MIN_VALUE);
                        vector.setType(DhDataVectorVariantV1.Type.INT64);
                        vector.addInt64Value(data);

                    } else if (type.equals("java.lang.String")) {
                        var data = ClassProperty.getProperty(movieV1Dto, propertyName.getName(), "");
                        vector.setType(DhDataVectorVariantV1.Type.STRING);
                        vector.addStringValue(data);

                    }  else if (type.equals("java.util.Date")) {
                        var data = ClassProperty.getProperty(movieV1Dto, propertyName.getName(), new Date (Integer.MIN_VALUE));
                        Long format = Long.parseLong(formatter.format(data));

                        vector.setType(DhDataVectorVariantV1.Type.INT64);
                        vector.addInt64Value(format);

                    } else if(type.contains("Set")) {
                        var data = ClassProperty.getProperty(movieV1Dto, propertyName.getName(),
                                Collections.emptySet());

                        var vectorValue = DhDataVectorVariantV1.newBuilder();
                        vectorValue.setType(DhDataVectorVariantV1.Type.STRING);
                        vector.setType(DhDataVectorVariantV1.Type.VECTOR);

                        if (data.size() == 0) {
                            vector.setType(DhDataVectorVariantV1.Type.STRING);

                        } else {
                            for (Object item: data) {
                                vectorValue.addStringValue(item.toString());
                            }
                        }
                        vector.addVectorValue(vectorValue);
                    }
                    column.setData(vector);
                }

                responseBuilder.addColumns(column.build());
            }
        }


        responseBuilder.setHeader(header);

        return responseBuilder.build();
    }


    String convertToFullName(String propertyName) {
        Map<String, String> map = new HashMap<String, String>(){{
            put("rank", "rank");
            put("movieVendorId", "movie_vendor_id");
            put("title", "title");
            put("productionCompany", "production_company");
            put("domesticDistributor", "domestic_distributor");
            put("domesticLifetimeGross", "domestic_lifetime_gross");
            put("productionBudget", "production_budget");
            put("domesticReleaseDate", "domestic_release_date");
            put("internetReleaseDate", "internet_release_date");
            put("videoReleaseType", "video_release_type");
            put("theaterCount", "theater_count");
            put("boxOffice", "box_office");
        }};

        return map.get(propertyName);
    }

}
