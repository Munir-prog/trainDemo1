package com.mprog.traindemo1.repository;


import com.mprog.traindemo1.model.Route;
import com.mprog.traindemo1.model.Ticket;
import lombok.SneakyThrows;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class TicketResultSetExtractor implements ResultSetExtractor<List<Ticket>> {

    @Override
    public List<Ticket> extractData(ResultSet rs) throws SQLException, DataAccessException {
        var tickets = new ArrayList<Ticket>();
        var routes = new HashMap<Integer, Route>();
        var currentTicket = Ticket.buildNullTicket();

        while (rs.next()) {
            var ticketId = rs.getInt("t_id");
            if (currentTicket.getId() != ticketId){
                currentTicket = buildTicket(rs, ticketId);
                tickets.add(currentTicket);
            }


            var routeId = rs.getInt("r_id");
            if (routeId == 0)
                throw new RuntimeException("no route for ticket " + currentTicket.getId());
            var route = routes.get(routeId);
            if (route == null) {
                route = buildRoute(rs, routeId);
                routes.put(routeId, route);
            }

//            route.getTickets().add(currentTicket);
//            currentTicket.setRoute(route);

        }

        return tickets;
    }

    @SneakyThrows
    private Route buildRoute(ResultSet rs, int routeId) {
        return Route.builder()
                .id(routeId)
//                .departureRailwayStation(new DepartureRailwayStation(rs.getString("dep_railway_station"), rs.getString("r_departure_railway_station")))
//                .arrivalRailwayStation(new ArrivalRailwayStation(rs.getString("ar_railway_station"), rs.getString("r_arrival_railway_station")))
//                .tickets(new HashSet<>())
                .build();
    }

    @SneakyThrows
    private Ticket buildTicket(ResultSet rs, int ticketId) {
        return Ticket.builder()
                .id(ticketId)
                .passengerNo(rs.getString("t_passenger_no"))
                .passengerName(rs.getString("t_passenger_name"))
                .passengerLastName(rs.getString("t_passenger_last_name"))
                .railwayCarNo(rs.getInt("t_railway_car_no"))
                .seatNo(rs.getString("t_seat_no"))
                .cost(rs.getBigDecimal("t_cost"))
//                .route(null)
                .build();
    }
}
