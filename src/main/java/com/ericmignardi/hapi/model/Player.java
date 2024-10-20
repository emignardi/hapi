package com.ericmignardi.hapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "players")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private String team;
    @Column(nullable = false)
    private String position;
    @Column(nullable = false)
    private int games;
    @Column(nullable = false)
    private int goals;
    @Column(nullable = false)
    private int assists;
    @Column(nullable = false)
    private int points;
    @Column(name = "plus_minus", nullable = false)
    private int plusMinus;
    @Column(name = "penalty_minutes", nullable = false)
    private int penaltyMinutes;
}
