package com.orbit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scenario {

    private Long id;
    private String nome;
    private String corpoCentral;
    private Integer quantidadeCorpos;
    private String notas;
}
