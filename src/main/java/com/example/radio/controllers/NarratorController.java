package com.example.radio.controllers;

import com.example.radio.entities.Narrator;
import com.example.radio.repositories.NarratorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class NarratorController {

    NarratorRepository narratorRepository;

    @PostMapping("/add_narrator")
    public String add_narrator(@RequestParam String name, @RequestParam(required = false) Integer experience, @RequestParam String resume) {
        Narrator narrator = new Narrator();
        narrator.setName(name);
        narrator.setExperience(experience);
        narrator.setResume(resume);
        narratorRepository.save(narrator);
        return "redirect:narrators";
    }

    @GetMapping("/narrator_broadcasts/{id}")
    public String broadcastsByNarrator(@PathVariable("id") int id, Model model) {
        Optional<Narrator> narrator = narratorRepository.findById(id);
        if (narrator.isEmpty())
            return "redirect:narrators";
        model.addAttribute("narrator", narrator.get());
        model.addAttribute("broadcasts", narrator.get().getBroadcasts());
        return "broadcasts_by_narrator";
    }

    @PostMapping("/deleteNarrator")
    public String deleteNarrator(@RequestParam Integer id) {
        narratorRepository.deleteById(id);
        return "redirect:narrators";
    }

    @GetMapping("/narrator_edit/{id}")
    public String editNarrator(@PathVariable("id") int id, Model model) {
        Optional<Narrator> narrator = narratorRepository.findById(id);
        model.addAttribute("narrator", narrator.get());
        return "narrator_edit";
    }

    @PostMapping("update_narrator")
    public String updateNarrator(@ModelAttribute Narrator narrator) {
        narratorRepository.save(narrator);
        return "redirect:narrators";
    }
}
