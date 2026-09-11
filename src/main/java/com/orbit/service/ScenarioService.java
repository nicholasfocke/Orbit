package com.orbit.service;

import com.orbit.model.Scenario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Armazena os cenários de simulação apenas em memória, como pedido no
 * trabalho: nenhum banco de dados, apenas uma lista mantida durante a
 * execução da aplicação.
 */
@Service
public class ScenarioService {

    private final List<Scenario> scenarios = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public ScenarioService() {
        salvar(new Scenario(null, "Sistema Solar clássico", "Sol", 8,
                "Cenário padrão com os oito planetas em órbitas estáveis ao redor do Sol."));
        salvar(new Scenario(null, "Binário caótico", "Duas estrelas de massa parecida", 2,
                "Duas estrelas orbitando o centro de massa comum, gerando trajetórias instáveis."));
    }

    public List<Scenario> listar() {
        return scenarios;
    }

    public Scenario salvar(Scenario scenario) {
        scenario.setId(nextId.getAndIncrement());
        scenarios.add(scenario);
        return scenario;
    }

    public Optional<Scenario> buscarPorId(Long id) {
        return scenarios.stream()
                .filter(scenario -> scenario.getId().equals(id))
                .findFirst();
    }
}
