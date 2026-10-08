package com.orbit.controller;

import com.orbit.service.PlanetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CatalogController {

    private final PlanetService planetService;

    @GetMapping("/catalog")
    public String catalog(Model model) {
        model.addAttribute("planets", planetService.listar());
        return "catalog";
    }
}
