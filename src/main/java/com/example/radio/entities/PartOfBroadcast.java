package com.example.radio.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "part_of_broadcast")
public class PartOfBroadcast {

    public enum typeOfBroadcast {
        SONG, INTERVIEW, ADVERTISING
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "name_of_song", length = 50)
    private String nameOfSong;

    @Column(name = "duration", nullable = false)
    private Double duration;

    @Column(name = "cost")
    private Double cost;

    private Double calc() {
        return cost = duration * 5 * 60;
    }
}
