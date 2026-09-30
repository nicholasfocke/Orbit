package com.orbit.service;

import com.orbit.model.Scenario;
import com.orbit.repository.ScenarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScenarioService {

    private final ScenarioRepository scenarioRepository;

    public ScenarioService(ScenarioRepository scenarioRepository) {
        this.scenarioRepository = scenarioRepository;
    }

    public List<Scenario> listar() {
        return scenarioRepository.findAll();
    }

    public Scenario salvar(Scenario scenario) {
        return scenarioRepository.save(scenario);
    }

    public Optional<Scenario> buscarPorId(Long id) {
        return scenarioRepository.findById(id);
    }
}
