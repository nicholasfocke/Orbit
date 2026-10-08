package com.orbit.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bodies")
public class Body {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "scenario_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Scenario scenario;

    @ManyToOne
    @JoinColumn(name = "planet_id")
    private Planet planet;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 7)
    private String color;

    @Column(nullable = false)
    private double mass;

    @Column(nullable = false)
    private double posX;

    @Column(nullable = false)
    private double posY;

    @Column(nullable = false)
    private double velX;

    @Column(nullable = false)
    private double velY;

    @Column(name = "is_central", nullable = false)
    private boolean central;

    public boolean isCustom() {
        return planet == null;
    }
}
