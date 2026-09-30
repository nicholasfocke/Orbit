package com.orbit.service;

import com.orbit.model.Planet;
import com.orbit.repository.PlanetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanetService {

    private final PlanetRepository planetRepository;

    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public List<Planet> listar() {
        return planetRepository.findAll();
    }

    public Planet salvar(Planet planet) {
        return planetRepository.save(planet);
    }
}
