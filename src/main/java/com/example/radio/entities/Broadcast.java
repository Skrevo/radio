package com.example.radio.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "broadcast")
public class Broadcast {

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
    private Set<PartOfBroadcast> part = new LinkedHashSet<>();
}

//todo calc all parts of broadcast
//todo calc all time of broadcast
//todo limit