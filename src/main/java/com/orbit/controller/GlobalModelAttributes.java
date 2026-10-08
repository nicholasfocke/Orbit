package com.orbit.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

// Adiciona "loggedIn" em todas as páginas, para o header mostrar
// "Entrar / Cadastrar" ou "Sair".
@ControllerAdvice
public class GlobalModelAttributes {

    @ModelAttribute("loggedIn")
    public boolean loggedIn() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
    }
}
