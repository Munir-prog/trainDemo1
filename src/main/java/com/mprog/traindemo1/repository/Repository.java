package com.mprog.traindemo1.repository;

import java.util.Collection;
import java.util.Optional;

public interface Repository<T> {
    Collection<T> findAll();
    Optional<T> findById(int id);
    void save(T object);
    void update(T object);
}
