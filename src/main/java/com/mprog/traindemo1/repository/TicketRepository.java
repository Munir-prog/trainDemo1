package com.mprog.traindemo1.repository;

import com.mprog.traindemo1.model.Ticket;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TicketRepository implements RepositoryDao<Ticket> {

    private final DataSource dataSource;

    private static final String FIND_ALL_SQL = """
            SELECT id, passenger_no, passenger_name, passenger_last_name, route_id, railway_car_no, seat_no, cost
            FROM ticket
            """;

    @Override
    @SneakyThrows
    public Collection<Ticket> findAll() {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {

            Collection<Ticket> ticketCollection = new ArrayList<>();
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ticketCollection.add(buildTicket(resultSet));
            }

            return ticketCollection;
        }
    }

    private static final String FIND_BY_ID_SQL = """
            SELECT id, passenger_no, passenger_name, passenger_last_name, route_id, railway_car_no, seat_no, cost
            FROM ticket
            WHERE id = ?
            """;

    @Override
    @SneakyThrows
    public Optional<Ticket> findById(int id) {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {

            preparedStatement.setInt(1, id);

            Ticket ticket = null;

            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ticket = (buildTicket(resultSet));
            }


            return Optional.ofNullable(ticket);
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
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(SAVE_SQL)) {

            setParameters(object, preparedStatement);

            preparedStatement.executeUpdate();
        }
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
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {

            setParameters(object, preparedStatement);
            preparedStatement.setInt(8, object.getId());

            preparedStatement.executeUpdate();
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
