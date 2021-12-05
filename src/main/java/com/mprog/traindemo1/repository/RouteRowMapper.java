package com.mprog.traindemo1.repository;

import com.mprog.traindemo1.model.Route;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteRowMapper implements RowMapper<Route> {

    @Override
    public Route mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Route.builder()
                .id(rs.getInt("id"))
                .routeNo(rs.getString("route_no"))
                .departureDate(rs.getDate("departure_date").toLocalDate())
//                .departureRailwayStation(new DepartureRailwayStation(null, rs.getString("departure_railway_station")))
                .arrivalDate(rs.getDate("arrival_date").toLocalDate())
//                .arrivalRailwayStation(new ArrivalRailwayStation(null, rs.getString("arrival_railway_station")))
                .trainId(rs.getInt("train_id"))
                .status(rs.getString("status"))
                .build();
    }
}
