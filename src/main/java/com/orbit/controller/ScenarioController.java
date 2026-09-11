package com.orbit.controller;

import com.orbit.model.Scenario;
import com.orbit.service.ScenarioService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/scenarios")
public class ScenarioController {

    private final ScenarioService scenarioService;

    public ScenarioController(ScenarioService scenarioService) {
        this.scenarioService = scenarioService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("scenarios", scenarioService.listar());
        return "scenarios";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("scenario", new Scenario());
        return "scenario-form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Scenario scenario) {
        scenarioService.salvar(scenario);
        return "redirect:/scenarios";
    }

    @GetMapping("/{id}")
    public String detalhes(@PathVariable Long id, Model model) {
        Scenario scenario = scenarioService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cenário não encontrado"));
        model.addAttribute("scenario", scenario);
        return "scenario-details";
    }
}
