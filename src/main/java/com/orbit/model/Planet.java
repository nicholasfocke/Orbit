package com.orbit.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Item do catálogo (Sol, planetas, Plutão). Dados fixos, criados pelo DataLoader.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "planets")
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 7)
    private String color;

    @Column(nullable = false)
    private int size;

    @Column(nullable = false)
    private boolean rings;

    @Column(nullable = false, length = 2000)
    private String description;

    @Column(nullable = false)
    private String mass;

    @Column(nullable = false)
    private String distance;

    @Column(nullable = false)
    private String temperature;

    @Column(nullable = false)
    private String dayLength;
}
