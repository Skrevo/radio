package com.example.radio.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "narrator")
public class Narrator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "resume", length = 500)
    private String resume;

    @OneToMany
    @JoinColumn(name = "narrator_id")
    private Set<Broadcast> broadcasts = new LinkedHashSet<>();
}
