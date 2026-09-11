package com.orbit.controller;

import com.orbit.service.PlanetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatalogController {

    private final PlanetService planetService;

    public CatalogController(PlanetService planetService) {
        this.planetService = planetService;
    }

    @GetMapping("/catalog")
    public String catalog(Model model) {
        model.addAttribute("planets", planetService.listar());
        return "catalog";
    }
}
