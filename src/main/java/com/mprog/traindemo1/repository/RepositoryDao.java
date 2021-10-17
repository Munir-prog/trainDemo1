package com.mprog.traindemo1.repository;

import com.mprog.traindemo1.model.Ticket;

import java.util.Collection;
import java.util.Optional;

public interface RepositoryDao<T> {
    Collection<T> findAll();
    Optional<T> findById(int id);
    void save(T object);
    void update(T object);
    Collection<T> findByName(String name);
}
