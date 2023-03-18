package com.example.radio.controllers;

import com.example.radio.entities.Broadcast;
import com.example.radio.entities.Narrator;
import com.example.radio.repositories.BroadcastRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class BroadcastController {

    BroadcastRepository broadcastRepository;

    @PostMapping("/add_broadcast")
    public String add_broadcast(@RequestParam String name, @RequestParam Narrator narrator) {
        Broadcast broadcast = new Broadcast();
        broadcast.setName(name);
        broadcast.setNarrator(narrator);
        broadcastRepository.save(broadcast);
        return "redirect:broadcasts";
    }
}
