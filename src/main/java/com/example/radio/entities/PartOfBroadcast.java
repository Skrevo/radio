package com.example.radio.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "part_of_broadcast")
public class PartOfBroadcast {

    public enum TypeOfBroadcast {
        SONG, INTERVIEW, ADVERTISING
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type")
    private TypeOfBroadcast type;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "name_of_song", length = 50)
    private String nameOfSong;

    @Column(name = "duration", nullable = false)
    private Double duration;

    @Column(name = "cost")
    private Double cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_id")
    private Broadcast broadcast;

    public Double calc() {
        if (type.name().equals("ADVERTISING"))
            return cost = duration * 5 * 60;
        if (type.name().equals("INTERVIEW"))
            return cost = duration * 30;
        else return cost = duration * 0;
    }
}

//todo sort by id
