package com.orbit.service;

import com.orbit.dto.ScenarioRequest;
import com.orbit.model.Body;
import com.orbit.model.Scenario;
import com.orbit.model.User;
import com.orbit.repository.ScenarioRepository;
import com.orbit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScenarioService {

    private final ScenarioRepository scenarioRepository;
    private final UserRepository userRepository;
    private final BodyService bodyService;

    public List<Scenario> listar(String email) {
        return scenarioRepository.findByUserEmailOrderByCreatedAtDesc(email);
    }

    // Cria o cenário já com o Sistema Solar dentro dele.
    @Transactional
    public Scenario criar(ScenarioRequest request, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        Scenario scenario = new Scenario();
        scenario.setUser(user);
        scenario.setName(request.getName());
        scenario.setNotes(request.getNotes());

        for (Body body : bodyService.montarSistemaSolar()) {
            body.setScenario(scenario);
            scenario.getBodies().add(body);
        }
        // O cascade de "bodies" salva os corpos junto com o cenário.
        return scenarioRepository.save(scenario);
    }

    // Busca um cenário do usuário logado. Cenário de outra pessoa = 404.
    public Scenario buscarDoUsuario(Long id, String email) {
        return scenarioRepository.findByIdAndUserEmail(id, email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cenário não encontrado"));
    }

    @Transactional
    public void excluir(Long id, String email) {
        scenarioRepository.delete(buscarDoUsuario(id, email));
    }
}
