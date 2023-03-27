package com.example.radio.controllers;

import com.example.radio.entities.Broadcast;
import com.example.radio.entities.Narrator;
import com.example.radio.entities.PartOfBroadcast;
import com.example.radio.repositories.BroadcastRepository;
import com.example.radio.repositories.NarratorRepository;
import com.example.radio.repositories.PartOfBroadcastRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class BroadcastController {

    BroadcastRepository broadcastRepository;

    PartOfBroadcastRepository partOfBroadcastRepository;

    NarratorRepository narratorRepository;

    @PostMapping("/add_broadcast")
    public String add_broadcast(@RequestParam String name, @RequestParam Integer narrator_id) {
        Broadcast broadcast = new Broadcast();
        broadcast.setName(name);
        broadcast.setDuration(0.0);
        broadcast.setCost(0.0);
        Optional<Narrator> narrator = narratorRepository.findById(narrator_id);
        broadcast.setNarrator(narrator.get());
        broadcastRepository.save(broadcast);
        return "redirect:broadcasts";
    }

    @GetMapping("/part_broadcast")
    public String editBroadcast(@RequestParam("id") int id, Model model) {
        Optional<Broadcast> broadcast = broadcastRepository.findById(id);
        if (broadcast.isEmpty()) return "redirect:broadcasts";
        model.addAttribute("broadcast", broadcast.get());
        model.addAttribute("parts", broadcast.get().getParts().stream().sorted(Comparator.comparingInt(PartOfBroadcast::getId)).collect(Collectors.toList()));
        model.addAttribute("types", PartOfBroadcast.TypeOfBroadcast.values());
        return "part_broadcast";
    }

    @PostMapping("update_broadcast")
    public String update_broadcast(@RequestParam String name,
                                   @RequestParam(value = "name_of_song", required = false) String nameOfSong,
                                   @RequestParam Double duration,
                                   @RequestParam PartOfBroadcast.TypeOfBroadcast type,
                                   @RequestParam Integer broadcast_id)
    {
        Optional<Broadcast> broadcast = broadcastRepository.findById(broadcast_id);
        if (broadcast.isEmpty()) return "redirect:broadcasts";
        PartOfBroadcast part = new PartOfBroadcast();
        part.setName(name);
        part.setNameOfSong(nameOfSong);
        part.setDuration(duration);
        part.setType(type);
        part.setCost(part.calc());
        part.setBroadcast(broadcast.get());
        partOfBroadcastRepository.save(part);
        if (broadcast.get().getParts().stream().mapToDouble(PartOfBroadcast::getDuration).sum() > broadcast.get().getMAX_DURATION()){
            partOfBroadcastRepository.delete(part);
        }
        if (broadcast.get().getParts().stream().filter(part1 -> part1.getCost()!=0).mapToDouble(PartOfBroadcast::getDuration).sum() > broadcast.get().getMAX_DURATION()/2){
            partOfBroadcastRepository.delete(part);
        }
        return "redirect:broadcasts";
    }

    @PostMapping("/deletePart")
    public String deletePart(@RequestParam Integer id) {
        partOfBroadcastRepository.deleteById(id);
        return "redirect:broadcasts";
    }

    @PostMapping("/deleteBroadcast")
    public String setNarrator(@RequestParam Integer id) {
        broadcastRepository.deleteById(id);
        return "redirect:broadcasts";
    }
}