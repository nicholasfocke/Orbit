package com.orbit.repository;

import com.orbit.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanetRepository extends JpaRepository<Planet, Long> {
    List<Planet> findAllByOrderByIdAsc();
}
