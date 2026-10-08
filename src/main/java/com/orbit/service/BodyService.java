package com.orbit.service;

import com.orbit.dto.BodyRequest;
import com.orbit.dto.BodyResponse;
import com.orbit.model.Body;
import com.orbit.model.Planet;
import com.orbit.model.Scenario;
import com.orbit.repository.BodyRepository;
import com.orbit.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BodyService {

    // Unidades da simulação (não são as reais): distância em pixels, G = 1.
    // O sandbox.js usa o mesmo G.
    private static final double G = 1;
    private static final double SUN_MASS = 50000;
    private static final String SUN_NAME = "Sol";
    private static final double FIRST_ORBIT = 80;
    private static final double ORBIT_GAP = 45;

    private final BodyRepository bodyRepository;
    private final PlanetRepository planetRepository;

    // Monta o Sistema Solar a partir do catálogo: o Sol parado no centro e
    // cada planeta numa órbita circular, um pouco mais longe que o anterior.
    // Os corpos voltam sem cenário (ainda não foram salvos).
    public List<Body> montarSistemaSolar() {
        List<Body> bodies = new ArrayList<>();
        double orbita = FIRST_ORBIT;
        int indice = 0;

        for (Planet planet : planetRepository.findAllByOrderByIdAsc()) {
            Body body = new Body();
            body.setPlanet(planet);
            body.setName(planet.getName());
            body.setColor(planet.getColor());

            if (planet.getName().equals(SUN_NAME)) {
                body.setMass(SUN_MASS);
                body.setCentral(true);
            } else {
                // Espalha os planetas em ângulos diferentes para não nascerem enfileirados.
                double angulo = indice * 0.9;
                // Velocidade de órbita circular: v = raiz(G * M / r)
                double velocidade = Math.sqrt(G * SUN_MASS / orbita);

                body.setMass(planet.getSize());
                body.setPosX(Math.cos(angulo) * orbita);
                body.setPosY(Math.sin(angulo) * orbita);
                body.setVelX(-Math.sin(angulo) * velocidade);
                body.setVelY(Math.cos(angulo) * velocidade);

                orbita += ORBIT_GAP;
                indice++;
            }
            bodies.add(body);
        }
        return bodies;
    }

    public Body adicionar(Scenario scenario, BodyRequest request) {
        if (request.getMass() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A massa deve ser maior que zero");
        }

        Body body = new Body();
        body.setScenario(scenario);
        body.setName(request.getName());
        body.setColor(request.getColor());
        body.setMass(request.getMass());
        body.setPosX(request.getPosX());
        body.setPosY(request.getPosY());
        body.setVelX(request.getVelX());
        body.setVelY(request.getVelY());
        return bodyRepository.save(body);
    }

    // Só deixa remover corpos criados pelo usuário, nunca os planetas do catálogo.
    public void remover(Scenario scenario, Long bodyId) {
        Body body = bodyRepository.findById(bodyId)
                .filter(b -> b.getScenario().getId().equals(scenario.getId()))
                .filter(Body::isCustom)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Corpo não encontrado"));
        bodyRepository.delete(body);
    }

    public List<BodyResponse> paraResposta(List<Body> bodies) {
        List<BodyResponse> resposta = new ArrayList<>();
        for (Body body : bodies) {
            Planet planet = body.getPlanet();
            resposta.add(BodyResponse.builder()
                    .name(body.getName())
                    .color(body.getColor())
                    .mass(body.getMass())
                    .posX(body.getPosX())
                    .posY(body.getPosY())
                    .velX(body.getVelX())
                    .velY(body.getVelY())
                    .central(body.isCentral())
                    .size(planet != null ? planet.getSize() : tamanhoPelaMassa(body.getMass()))
                    .rings(planet != null && planet.isRings())
                    .build());
        }
        return resposta;
    }

    // Corpo do usuário: o tamanho na tela cresce com a massa (entre 3 e 20 px).
    private int tamanhoPelaMassa(double mass) {
        int tamanho = (int) Math.round(Math.cbrt(mass) * 2);
        return Math.max(3, Math.min(20, tamanho));
    }
}
