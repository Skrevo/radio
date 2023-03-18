package com.example.radio.repositories;

import com.example.radio.entities.Narrator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NarratorRepository extends JpaRepository<Narrator, Integer> {
}