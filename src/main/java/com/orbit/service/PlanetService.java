package com.orbit.service;

import com.orbit.model.Planet;
import com.orbit.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanetService {

    private final PlanetRepository planetRepository;

    public List<Planet> listar() {
        return planetRepository.findAllByOrderByIdAsc();
    }
}
