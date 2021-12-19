package com.mprog.traindemo1.repository;

import com.mprog.traindemo1.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteDao extends JpaRepository<Route, Integer> {

}
