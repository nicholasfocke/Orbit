package com.orbit.dto;

import lombok.Data;

// Dados do formulário de novo corpo, dentro de um cenário.
@Data
public class BodyRequest {
    private String name;
    // Valores iniciais que aparecem no formulário.
    private String color = "#ff4fd8";
    private double mass = 10;
    private double posX;
    private double posY;
    private double velX;
    private double velY;
}
