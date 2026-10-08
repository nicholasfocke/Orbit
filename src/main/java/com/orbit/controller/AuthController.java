package com.orbit.controller;

import com.orbit.dto.AuthRequest;
import com.orbit.dto.AuthResponse;
import com.orbit.security.JwtAuthenticationFilter;
import com.orbit.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        return comCookie(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return comCookie(authService.login(request));
    }

    // Guarda o JWT num cookie: assim o navegador o envia sozinho em toda
    // navegação entre páginas, e o JwtAuthenticationFilter consegue lê-lo.
    // HttpOnly = o JavaScript da página não consegue ler o token.
    private ResponseEntity<AuthResponse> comCookie(AuthResponse response) {
        ResponseCookie cookie = ResponseCookie.from(JwtAuthenticationFilter.TOKEN_COOKIE, response.getToken())
                .httpOnly(true)
                .path("/")
                .sameSite("Lax")
                .maxAge(Duration.ofDays(1))
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(response);
    }
}
