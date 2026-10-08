package com.orbit.config;

import com.orbit.model.Planet;
import com.orbit.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

// Roda ao subir a aplicação: se o catálogo estiver vazio,
// cadastra o Sol, os oito planetas e Plutão.
// A ordem importa: o BodyService monta as órbitas nessa ordem.
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final PlanetRepository planetRepository;

    @Override
    public void run(String... args) {
        if (planetRepository.count() > 0) {
            return;
        }

        planetRepository.saveAll(List.of(
                new Planet(null, "Sol", "#ffc93c", 22, false,
                        "A estrela no centro do sistema: concentra 99,8% de toda a massa do Sistema Solar.",
                        "1,99 × 10^30 kg", "—", "5.500 °C (superfície)", "25 dias terrestres (no equador)"),
                new Planet(null, "Mercúrio", "#9c9c94", 4, false,
                        "O menor planeta e o mais rápido, torrado pela proximidade com o Sol.",
                        "3,30 × 10^23 kg", "57,9 milhões de km", "167 °C (média)", "58,6 dias terrestres"),
                new Planet(null, "Vênus", "#e0c16c", 7, false,
                        "Efeito estufa descontrolado: é mais quente que Mercúrio mesmo estando mais longe.",
                        "4,87 × 10^24 kg", "108,2 milhões de km", "464 °C (média)", "243 dias terrestres"),
                new Planet(null, "Terra", "#3a86ff", 7, false,
                        "O único mundo conhecido com oceanos líquidos e vida na superfície.",
                        "5,97 × 10^24 kg", "149,6 milhões de km", "15 °C (média)", "24 horas"),
                new Planet(null, "Marte", "#c1440e", 5, false,
                        "O Planeta Vermelho, com o maior vulcão e o maior cânion do Sistema Solar.",
                        "6,42 × 10^23 kg", "227,9 milhões de km", "-65 °C (média)", "24,6 horas"),
                new Planet(null, "Júpiter", "#d8ca9d", 16, false,
                        "Um gigante gasoso tão grande que caberiam todos os outros planetas dentro dele.",
                        "1,90 × 10^27 kg", "778,5 milhões de km", "-110 °C (média)", "9,9 horas"),
                new Planet(null, "Saturno", "#ead6b8", 14, true,
                        "Famoso pelos anéis brilhantes feitos de gelo e rocha.",
                        "5,68 × 10^26 kg", "1,43 bilhão de km", "-140 °C (média)", "10,7 horas"),
                new Planet(null, "Urano", "#9fe3f0", 10, false,
                        "Um gigante de gelo que gira quase deitado de lado.",
                        "8,68 × 10^25 kg", "2,87 bilhões de km", "-195 °C (média)", "17,2 horas"),
                new Planet(null, "Netuno", "#3b5bdb", 10, false,
                        "O mundo mais ventoso, com tempestades acima de 2.000 km/h.",
                        "1,02 × 10^26 kg", "4,50 bilhões de km", "-200 °C (média)", "16,1 horas"),
                new Planet(null, "Plutão", "#c9a98a", 3, false,
                        "Planeta-anão no Cinturão de Kuiper, com uma geleira em forma de coração.",
                        "1,30 × 10^22 kg", "5,91 bilhões de km", "-229 °C (média)", "6,4 dias terrestres")
        ));
    }
}
