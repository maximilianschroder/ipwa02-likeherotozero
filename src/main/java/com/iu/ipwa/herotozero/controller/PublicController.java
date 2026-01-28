package com.iu.ipwa.herotozero.controller;

import com.iu.ipwa.herotozero.service.EmissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicController {

    private final EmissionService emissionService;

    public PublicController(EmissionService emissionService) {
        this.emissionService = emissionService;
    }

    @GetMapping("/") // Wenn jemand die Startseite aufruft
    public String showStartPage(Model model) {
        // holen wir die Daten
        model.addAttribute("emissions", emissionService.getAllEmissions());
        // und geben "index" zurück (Spring sucht dann nach index.html)
        return "index";
    }
}