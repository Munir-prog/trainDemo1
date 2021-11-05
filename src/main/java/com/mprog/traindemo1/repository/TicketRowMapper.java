package com.mprog.traindemo1.repository;

import com.mprog.traindemo1.model.Ticket;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketRowMapper implements RowMapper<Ticket> {
    @Override
    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Ticket.builder()
                .id(rs.getInt("id"))
                .passengerNo(rs.getString("passenger_no"))
                .passengerName(rs.getString("passenger_name"))
                .passengerLastName(rs.getString("passenger_last_name"))
                .routeId(rs.getInt("route_id"))
                .railwayCarNo(rs.getInt("railway_car_no"))
                .seatNo(rs.getString("seat_no"))
                .cost(rs.getBigDecimal("cost"))
                .build();
    }
}
