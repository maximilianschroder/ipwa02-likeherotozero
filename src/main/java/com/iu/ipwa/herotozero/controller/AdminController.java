package com.iu.ipwa.herotozero.controller;

import com.iu.ipwa.herotozero.model.EmissionData;
import com.iu.ipwa.herotozero.service.EmissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin") // Alle URLs hier starten mit /admin
public class AdminController {

    private final EmissionService emissionService;

    public AdminController(EmissionService emissionService) {
        this.emissionService = emissionService;
    }

    // 1. Dashboard anzeigen (Liste mit Bearbeiten-Buttons)
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("emissions", emissionService.getAllEmissions());
        return "admin/dashboard"; // Wir legen das gleich in einen Unterordner
    }

    // 2. Formular zum "Neu anlegen" zeigen
    @GetMapping("/add")
    public String showAddForm(Model model) {
        // Wir übergeben ein leeres Objekt an das Formular
        model.addAttribute("emissionData", new EmissionData());
        return "admin/emission-form";
    }

    // 3. Formular zum "Bearbeiten" zeigen (lädt vorhandene Daten)
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        EmissionData data = emissionService.findById(id); // Diese Methode müssen wir im Service noch bauen!
        model.addAttribute("emissionData", data);
        return "admin/emission-form";
    }

    // 4. Speichern (wird vom Formular aufgerufen)
    @PostMapping("/save")
    public String saveEmission(@ModelAttribute("emissionData") EmissionData emissionData) {
        emissionService.save(emissionData); // Diese Methode müssen wir im Service noch bauen!
        return "redirect:/admin/dashboard"; // Nach dem Speichern zurück zur Übersicht
    }

    // 5. Löschen
    @GetMapping("/delete/{id}")
    public String deleteEmission(@PathVariable("id") long id) {
        emissionService.delete(id); // Diese Methode müssen wir im Service noch bauen!
        return "redirect:/admin/dashboard";
    }
}