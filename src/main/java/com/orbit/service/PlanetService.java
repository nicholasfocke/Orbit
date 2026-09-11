package com.orbit.service;

import com.orbit.model.Planet;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Dados simulados do catálogo (mesmas informações que antes viviam em
 * planets-data.js), agora servidas pelo backend em vez de hardcoded no
 * JavaScript do navegador.
 */
@Service
public class PlanetService {

    private final List<Planet> planets = List.of(
            new Planet("Mercúrio", "#9c9c94", 4, false,
                    "O menor planeta e o mais rápido, torrado pela proximidade com o Sol.",
                    "3,30 × 10^23 kg", "57,9 milhões de km", "167 °C (média)", "58,6 dias terrestres"),
            new Planet("Vênus", "#e0c16c", 7, false,
                    "Efeito estufa descontrolado: é mais quente que Mercúrio mesmo estando mais longe.",
                    "4,87 × 10^24 kg", "108,2 milhões de km", "464 °C (média)", "243 dias terrestres"),
            new Planet("Terra", "#3a86ff", 7, false,
                    "O único mundo conhecido com oceanos líquidos e vida na superfície.",
                    "5,97 × 10^24 kg", "149,6 milhões de km", "15 °C (média)", "24 horas"),
            new Planet("Marte", "#c1440e", 5, false,
                    "O Planeta Vermelho, com o maior vulcão e o maior cânion do Sistema Solar.",
                    "6,42 × 10^23 kg", "227,9 milhões de km", "-65 °C (média)", "24,6 horas"),
            new Planet("Júpiter", "#d8ca9d", 16, false,
                    "Um gigante gasoso tão grande que caberiam todos os outros planetas dentro dele.",
                    "1,90 × 10^27 kg", "778,5 milhões de km", "-110 °C (média)", "9,9 horas"),
            new Planet("Saturno", "#ead6b8", 14, true,
                    "Famoso pelos anéis brilhantes feitos de gelo e rocha.",
                    "5,68 × 10^26 kg", "1,43 bilhão de km", "-140 °C (média)", "10,7 horas"),
            new Planet("Urano", "#9fe3f0", 10, false,
                    "Um gigante de gelo que gira quase deitado de lado.",
                    "8,68 × 10^25 kg", "2,87 bilhões de km", "-195 °C (média)", "17,2 horas"),
            new Planet("Netuno", "#3b5bdb", 10, false,
                    "O mundo mais ventoso, com tempestades acima de 2.000 km/h.",
                    "1,02 × 10^26 kg", "4,50 bilhões de km", "-200 °C (média)", "16,1 horas")
    );

    public List<Planet> listar() {
        return planets;
    }
}
