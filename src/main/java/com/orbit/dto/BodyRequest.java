package com.orbit.dto;

import lombok.Data;

@Data
public class BodyRequest {
    private String name;
    private String color = "#ff4fd8";
    private double mass = 10;
    private double posX;
    private double posY;
    private double velX;
    private double velY;
}
