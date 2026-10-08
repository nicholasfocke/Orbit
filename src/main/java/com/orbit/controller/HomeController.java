package com.orbit.controller;

import com.orbit.dto.BodyResponse;
import com.orbit.service.BodyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BodyService bodyService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot-password";
    }

    // Sandbox público: mostra só o Sistema Solar, sem salvar nada.
    @GetMapping("/sandbox")
    public String sandbox() {
        return "sandbox";
    }

    // JSON lido pelo sandbox.js na página pública.
    @GetMapping("/sandbox/bodies")
    @ResponseBody
    public List<BodyResponse> sandboxBodies() {
        return bodyService.paraResposta(bodyService.montarSistemaSolar());
    }
}
