package com.orbit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Planet {

    private String name;
    private String color;
    private int size;
    private boolean hasRings;
    private String description;
    private String mass;
    private String distance;
    private String temperature;
    private String dayLength;
}
