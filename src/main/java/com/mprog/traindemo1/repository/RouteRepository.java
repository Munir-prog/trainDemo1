package com.mprog.traindemo1.repository;

import com.mprog.traindemo1.model.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RouteRepository {

    private final RouteDao routeDao;

    public Collection<Route> findAll() {
        return routeDao.findAll();
    }

    public Optional<Route> findById(int id) {
        return routeDao.findById(id);
    }

    public void save(Route object) {
        routeDao.save(object);
    }


    public void deleteById(int id) {
        routeDao.deleteById(id);
    }

}
