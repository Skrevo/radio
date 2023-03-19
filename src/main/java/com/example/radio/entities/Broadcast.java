package com.example.radio.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "broadcast")
public class Broadcast {

    private final double MAX_DURATION = 480;

    private String message;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "duration", nullable = false)
    private Double duration;

    @Column(name = "cost")
    private Double cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "narrator_id")
    private Narrator narrator;

    @OneToMany
    @JoinColumn(name = "part_id")
    private Set<PartOfBroadcast> parts = new LinkedHashSet<>();


    public Double calcCost() {
      return cost = parts.stream().mapToDouble(PartOfBroadcast::getCost).sum();
    }

    public Double calcDuration() {
        duration = parts.stream().mapToDouble(PartOfBroadcast::getDuration).sum();
        if (duration > MAX_DURATION) {
            message = "duration of all parts too long, please delete some parts or part from broadcast";
        }
        return duration;
    }
}

//todo limit