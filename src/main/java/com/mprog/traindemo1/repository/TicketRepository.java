package com.mprog.traindemo1.repository;

import com.mprog.traindemo1.model.Ticket;
import com.mprog.traindemo1.service.exception.AppException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TicketRepository implements RepositoryDao<Ticket> {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    @SneakyThrows
    public Collection<Ticket> findAll() {
        String sql = """
                SELECT t.id t_id,
                        t.passenger_no t_passenger_no,
                        t.passenger_name t_passenger_name,
                        t.passenger_last_name t_passenger_last_name,
                        t.railway_car_no t_railway_car_no,
                        t.seat_no t_seat_no,
                        t.cost t_cost,
                        r.id r_id,
                        r.arrival_railway_station r_arrival_railway_station,
                        rs.city ar_railway_station,
                        r.departure_railway_station r_departure_railway_station,
                        drs.city dep_railway_station
                FROM ticket t
                LEFT JOIN route r on r.id = t.route_id
                LEFT JOIN railway_station rs ON r.arrival_railway_station = rs.railway_station_name
                LEFT JOIN railway_station drs ON drs.railway_station_name = r.departure_railway_station
                """;
        return jdbc.query(sql, new TicketResultSetExtractor());
    }


    @Override
    @SneakyThrows
    public Optional<Ticket> findById(int id) {

        String sql = """
                SELECT t.id t_id,
                        t.passenger_no t_passenger_no,
                        t.passenger_name t_passenger_name,
                        t.passenger_last_name t_passenger_last_name,
                        t.railway_car_no t_railway_car_no,
                        t.seat_no t_seat_no,
                        t.cost t_cost,
                        r.id r_id,
                        r.arrival_railway_station r_arrival_railway_station,
                        rs.city ar_railway_station,
                        r.departure_railway_station r_departure_railway_station,
                        drs.city dep_railway_station
                FROM ticket t
                LEFT JOIN route r on r.id = t.route_id
                LEFT JOIN railway_station rs ON r.arrival_railway_station = rs.railway_station_name
                LEFT JOIN railway_station drs ON drs.railway_station_name = r.departure_railway_station
                WHERE t.id = :id
                """;

        var list = jdbc.query(sql, Map.of("id", id), new TicketResultSetExtractor());
        if (list == null || list.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(list.get(0));
        }
    }

    private static final String SAVE_SQL = """
            INSERT INTO ticket
            (passenger_no, passenger_name, passenger_last_name, route_id, railway_car_no, seat_no, cost)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

    @Override
    @SneakyThrows
    public void save(Ticket object) {
        String sql = """
                INSERT INTO ticket (passenger_no, passenger_name, passenger_last_name, route_id, railway_car_no, seat_no, cost)
                VALUES (:passenger_no, :passenger_name, :passenger_last_name, :route_id, :railway_car_no, :seat_no, :cost)
                """;
        jdbc.update(sql, getParams(object));
    }

    private static final String UPDATE_SQL = """
            UPDATE ticket
            SET passenger_no = ?, passenger_name = ?, passenger_last_name = ?,
                route_id = ?, railway_car_no = ?, seat_no = ?, cost = ?
            WHERE id = ?
            """;

    @Override
    @SneakyThrows
    public void update(Ticket object) {
        String sql = """
                UPDATE ticket
                SET passenger_no = :passenger_no, passenger_name = :passenger_name,
                passenger_last_name = :passenger_last_name, route_id = :route_id,
                railway_car_no = :railway_car_no, seat_no = :seat_no, cost = :cost
                WHERE id = :id
                """;
        jdbc.update(sql, getParams(object));
    }

    private MapSqlParameterSource getParams(Ticket object) {
        var params = new MapSqlParameterSource();
        params.addValue("passenger_no", object.getPassengerNo());
        params.addValue("passenger_name", object.getPassengerName());
        params.addValue("passenger_last_name", object.getPassengerLastName());
        params.addValue("route_id", object.getRouteId());
        params.addValue("railway_car_no", object.getRailwayCarNo());
        params.addValue("seat_no", object.getSeatNo());
        params.addValue("cost", object.getCost());
        params.addValue("id", object.getId());
        return params;
    }

    private static final String FIND_BY_NAME_SQL = """
            SELECT id, passenger_no, passenger_name, passenger_last_name, route_id, railway_car_no, seat_no, cost
            FROM ticket
            WHERE passenger_name = ?
            """;

    @Override
    @SneakyThrows
    public Collection<Ticket> findByName(String name) {
        var params = new MapSqlParameterSource();
        params.addValue("passenger_name", name);

        String sql = """
                SELECT id, passenger_no, passenger_name, passenger_last_name, route_id, railway_car_no, seat_no, cost
                FROM ticket
                WHERE passenger_name = :passenger_name
                """;
        return jdbc.query(sql, params, new TicketRowMapper());
    }

    @Override
    public void deleteById(int id) {
        String sql = """
                DELETE FROM ticket
                WHERE id = :id
                """;
        var params = new MapSqlParameterSource();
        params.addValue("id", id);
        var deleted = jdbc.update(sql, params);
        if (deleted == 0){
            throw new AppException("No such ticket");
        }
    }

    @SneakyThrows
    private Ticket buildTicket(ResultSet resultSet) {
        return Ticket.builder()
                .id(resultSet.getObject("id", Integer.class))
                .passengerNo(resultSet.getObject("passenger_no", String.class))
                .passengerName(resultSet.getObject("passenger_name", String.class))
                .passengerLastName(resultSet.getObject("passenger_last_name", String.class))
                .routeId(resultSet.getObject("route_id", Integer.class))
                .railwayCarNo(resultSet.getObject("railway_car_no", Integer.class))
                .seatNo(resultSet.getObject("seat_no", String.class))
                .cost(resultSet.getObject("cost", BigDecimal.class))
                .build();
    }

    private void setParameters(Ticket object, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, object.getPassengerNo());
        preparedStatement.setString(2, object.getPassengerName());
        preparedStatement.setString(3, object.getPassengerLastName());
        preparedStatement.setInt(4, object.getRouteId());
        preparedStatement.setInt(5, object.getRailwayCarNo());
        preparedStatement.setString(6, object.getSeatNo());
        preparedStatement.setBigDecimal(7, object.getCost());
    }

}
