package com.mprog.traindemo1.repository;

import com.mprog.traindemo1.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface TicketDao extends JpaRepository<Ticket, Integer> {

    Collection<Ticket> findByPassengerName(String name);

//    Collection<T> findAll();
//    Optional<T> findById(int id);
//    void save(T object);
//    Collection<T> findByName(String name);
//    void deleteById(int id);
}
