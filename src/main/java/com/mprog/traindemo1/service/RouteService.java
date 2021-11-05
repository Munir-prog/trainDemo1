package com.mprog.traindemo1.service;

import com.mprog.traindemo1.model.Route;
import com.mprog.traindemo1.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class RouteService implements DaoService<Route> {

    private final RouteRepository routeRepository;

    @Override
    public Collection<Route> findAll() {
        return routeRepository.findAll();
    }

    @Override
    public Route findById(int id) {
        return null;
    }

    @Override
    public void save(Route object) {

    }

    @Override
    public Collection<Route> findByName(String name) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
