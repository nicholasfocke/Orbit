package com.orbit.dto;

import lombok.Data;

// Dados do formulário de novo cenário.
@Data
public class ScenarioRequest {
    private String name;
    private String notes;
}
