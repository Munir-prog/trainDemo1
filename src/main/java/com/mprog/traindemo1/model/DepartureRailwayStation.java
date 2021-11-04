package com.mprog.traindemo1.model;

import lombok.Data;
import lombok.Value;

@Value
public class DepartureRailwayStation {
    String city;
    String railwayStationName;

    @Override
    public String toString() {
        return city + " - " + railwayStationName;
    }
}
