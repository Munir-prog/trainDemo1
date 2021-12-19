package com.mprog.traindemo1.repository;

import com.mprog.traindemo1.model.Ticket;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class TicketRepository {

    private final TicketDao ticketDao;

    public Collection<Ticket> findAll() {
        return ticketDao.findAll();
    }

    public Optional<Ticket> findById(int id) {
        return ticketDao.findById(id);
    }

    public void save(Ticket ticket) {
        ticketDao.save(ticket);
    }

    public Collection<Ticket> findByName(String name) {
        return ticketDao.findByPassengerName(name);
    }
    public void deleteById(int id) {
        ticketDao.deleteById(id);
    }

}
