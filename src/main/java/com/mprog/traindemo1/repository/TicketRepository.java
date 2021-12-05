package com.mprog.traindemo1.repository;

import com.mprog.traindemo1.model.Ticket;
import com.mprog.traindemo1.service.exception.AppException;
import com.mprog.traindemo1.service.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class TicketRepository implements RepositoryDao<Ticket> {

    @PersistenceContext
    private final EntityManager entityManager;
//    private static Session session;

    @Override
    @SneakyThrows
    @Transactional
    public Collection<Ticket> findAll() {
        var session = getSession();
        try (session) {
            var query = session.getCriteriaBuilder().createQuery(Ticket.class);
            query.from(Ticket.class);
            return session.createQuery(query).getResultList();
        }
    }


    @Override
    @SneakyThrows
    @Transactional
    public Optional<Ticket> findById(int id) {

        var session = getSession();
        try (session) {
            var ticket = session.get(Ticket.class, id);
            return Optional.ofNullable(ticket);
        }
    }

    @Override
    @SneakyThrows
    @Transactional
    public void save(Ticket ticket) {
        var session = getSession();
        session.saveOrUpdate(ticket);
    }

    @Override
    @SneakyThrows
    @Transactional
    public Collection<Ticket> findByName(String name) {
        var session = getSession();
        try (session) {
            var criteriaBuilder = session.getCriteriaBuilder();
            var query = criteriaBuilder.createQuery(Ticket.class);
            var root = query.from(Ticket.class);
            query.select(root).where(criteriaBuilder.like(root.get("passengerName"), name));
            return session.createQuery(query).getResultList();
        }
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        var session = getSession();
        try (session) {
            var ticket = session.get(Ticket.class, id);
            session.delete(ticket);
        }
    }


    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

}
