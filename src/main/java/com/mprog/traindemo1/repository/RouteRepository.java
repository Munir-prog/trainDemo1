package com.mprog.traindemo1.repository;

import com.mprog.traindemo1.model.Route;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RouteRepository {

    @PersistenceContext
    private final EntityManager entityManager;


    @Transactional
    public Collection<Route> findAll() {
        try (var session = getSession();) {
            var query = session.getCriteriaBuilder().createQuery(Route.class);
            query.from(Route.class);
            return session.createQuery(query).getResultList();
        }
    }

    public Optional<Route> findById(int id) {
        try (var session = getSession()) {
            return Optional.ofNullable(session.get(Route.class, id));
        }

    }

    public void save(Route object) {
        try (var session = getSession()) {
            session.saveOrUpdate(object);
        }
    }


    public void deleteById(int id) {
        try (var session = getSession()) {
            var ticket = session.get(Route.class, id);
            session.delete(ticket);
        }
    }

    public Collection<Route> findByName(String name) {
        return null;
    }

    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }
}
