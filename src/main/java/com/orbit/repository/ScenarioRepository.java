package com.orbit.repository;

import com.orbit.model.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScenarioRepository extends JpaRepository<Scenario, Long> {
    List<Scenario> findByUserEmailOrderByCreatedAtDesc(String email);

    // Só encontra o cenário se ele for do usuário informado.
    Optional<Scenario> findByIdAndUserEmail(Long id, String email);
}
