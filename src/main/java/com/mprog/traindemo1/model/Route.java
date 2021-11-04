package com.mprog.traindemo1.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
public class Route {
    int id;
    String routeNo;
    LocalDate departureDate;
    DepartureRailwayStation departureRailwayStation;
    LocalDate arrivalDate;
    ArrivalRailwayStation arrivalRailwayStation;
    int trainId;
    String status;
    Collection<Ticket> tickets;


    public String routeToString() {
        return departureRailwayStation + " -> " + arrivalRailwayStation;
    }
}
