package com.mprog.traindemo1.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class RailwayStation {
    private final String city;
    private final String railwayStationName;

    public RailwayStation(String city, String railwayStationName) {
        this.city = city;
        this.railwayStationName = railwayStationName;
    }

}
