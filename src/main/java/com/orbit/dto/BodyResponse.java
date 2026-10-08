package com.orbit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BodyResponse {
    private String name;
    private String color;
    private double mass;
    private double posX;
    private double posY;
    private double velX;
    private double velY;
    private boolean central;
    private int size;
    private boolean rings;
}
