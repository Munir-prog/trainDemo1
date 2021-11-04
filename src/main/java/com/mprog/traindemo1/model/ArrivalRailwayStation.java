package com.mprog.traindemo1.model;

import lombok.Value;

@Value
public class ArrivalRailwayStation {
    String city;
    String railwayStationName;

    @Override
    public String toString() {
        return city + " - " + railwayStationName;
    }
}
