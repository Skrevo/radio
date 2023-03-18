package com.example.radio.controllers;

import com.example.radio.entities.Broadcast;
import com.example.radio.entities.Narrator;
import com.example.radio.repositories.BroadcastRepository;
import com.example.radio.repositories.NarratorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class RadioStationController {

    BroadcastRepository broadcastRepository;

    NarratorRepository narratorRepository;
    @GetMapping("/broadcasts")
    public String broadcasts(Model model) {
        List<Broadcast> broadcasts = broadcastRepository.findAll();
        List<Narrator> narrators = narratorRepository.findAll();
        model.addAttribute("broadcasts", broadcasts);
        model.addAttribute("narrators", narrators);
        return "broadcasts";
    }

    @GetMapping("/narrators")
    public String narrators(Model model) {
        List<Narrator> narrators = narratorRepository.findAll();
        model.addAttribute("narrators", narrators);
        return "narrators";
    }
}
