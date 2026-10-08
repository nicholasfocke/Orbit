package com.orbit.controller;

import com.orbit.dto.BodyRequest;
import com.orbit.dto.BodyResponse;
import com.orbit.dto.ScenarioRequest;
import com.orbit.model.Scenario;
import com.orbit.service.BodyService;
import com.orbit.service.ScenarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/scenarios")
@RequiredArgsConstructor
public class ScenarioController {

    private final ScenarioService scenarioService;
    private final BodyService bodyService;

    @GetMapping
    public String listar(Model model, Principal principal) {
        model.addAttribute("scenarios", scenarioService.listar(principal.getName()));
        return "scenarios";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("scenario", new ScenarioRequest());
        return "scenario-form";
    }

    @PostMapping
    public String criar(@ModelAttribute ScenarioRequest request, Principal principal) {
        Scenario scenario = scenarioService.criar(request, principal.getName());
        return "redirect:/scenarios/" + scenario.getId();
    }

    @GetMapping("/{id}")
    public String detalhes(@PathVariable Long id, Model model, Principal principal) {
        model.addAttribute("scenario", scenarioService.buscarDoUsuario(id, principal.getName()));
        model.addAttribute("body", new BodyRequest());
        return "scenario-details";
    }

    @GetMapping("/{id}/bodies")
    @ResponseBody
    public List<BodyResponse> bodies(@PathVariable Long id, Principal principal) {
        Scenario scenario = scenarioService.buscarDoUsuario(id, principal.getName());
        return bodyService.paraResposta(scenario.getBodies());
    }

    @PostMapping("/{id}/bodies")
    public String adicionarCorpo(@PathVariable Long id, @ModelAttribute BodyRequest request, Principal principal) {
        Scenario scenario = scenarioService.buscarDoUsuario(id, principal.getName());
        bodyService.adicionar(scenario, request);
        return "redirect:/scenarios/" + id;
    }

    @PostMapping("/{id}/bodies/{bodyId}/delete")
    public String removerCorpo(@PathVariable Long id, @PathVariable Long bodyId, Principal principal) {
        Scenario scenario = scenarioService.buscarDoUsuario(id, principal.getName());
        bodyService.remover(scenario, bodyId);
        return "redirect:/scenarios/" + id;
    }

    @PostMapping("/{id}/delete")
    public String excluir(@PathVariable Long id, Principal principal) {
        scenarioService.excluir(id, principal.getName());
        return "redirect:/scenarios";
    }
}
