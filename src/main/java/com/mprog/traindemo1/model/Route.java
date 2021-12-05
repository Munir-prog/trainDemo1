package com.mprog.traindemo1.model;

import com.mprog.traindemo1.converter.RailwayStationConverter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String routeNo;

    LocalDate departureDate;

    @Convert(converter = RailwayStationConverter.class)
    RailwayStation departureRailwayStation;

    LocalDate arrivalDate;

    @Convert(converter = RailwayStationConverter.class)
    RailwayStation arrivalRailwayStation;

    int trainId;

    String status;

//    Collection<Ticket> tickets;


    public String routeToString() {
        return departureRailwayStation + " -> " + arrivalRailwayStation;
    }
}
