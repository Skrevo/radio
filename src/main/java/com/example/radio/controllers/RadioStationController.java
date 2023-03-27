package com.example.radio.controllers;

import com.example.radio.entities.Broadcast;
import com.example.radio.entities.Narrator;
import com.example.radio.repositories.BroadcastRepository;
import com.example.radio.repositories.NarratorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class RadioStationController {

    BroadcastRepository broadcastRepository;

    NarratorRepository narratorRepository;
    @GetMapping("/broadcasts")
    public String broadcasts(Model model) {
        List<Broadcast> broadcasts = broadcastRepository.findAll();
        broadcasts.forEach(Broadcast::calcCost);
        broadcasts.forEach(Broadcast::calcDuration);
        List<Narrator> narrators = narratorRepository.findAll();
        model.addAttribute("broadcasts", broadcasts);
        model.addAttribute("narrators", narrators);
        broadcastRepository.saveAll(broadcasts);
        return "broadcasts";
    }

    @GetMapping("/narrators")
    public String narrators(Model model) {
        List<Narrator> narrators = narratorRepository.findAll();
        model.addAttribute("narrators", narrators);
        return "narrators";
    }
}
