package com.mprog.traindemo1.converter;

import com.mprog.traindemo1.model.RailwayStation;

import javax.persistence.AttributeConverter;
import java.util.Optional;

public class RailwayStationConverter implements AttributeConverter<RailwayStation, String> {

    @Override
    public String convertToDatabaseColumn(RailwayStation attribute) {
        return Optional.ofNullable(attribute)
                .map(it -> it.city() + " " + it.railwayStationName())
                .orElse("");
    }

    @Override
    public RailwayStation convertToEntityAttribute(String dbData) {
        return Optional.ofNullable(dbData)
                .map(RailwayStationConverter::convertToRailwayStation)
                .orElse(new RailwayStation("unknown", "unknown"));
    }

    private static RailwayStation convertToRailwayStation(String str) {
        var split = str.split(" ");
        if (split.length == 2)
            return new RailwayStation(split[0], split[1]);
        else
            return new RailwayStation("unknown", split[0]);
    }
}
