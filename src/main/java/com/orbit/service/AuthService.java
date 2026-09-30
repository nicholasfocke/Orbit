package com.orbit.service;

import com.orbit.dto.AuthRequest;
import com.orbit.dto.AuthResponse;
import com.orbit.model.User;
import com.orbit.repository.UserRepository;
import com.orbit.security.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public AuthResponse register(AuthRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getFullName()
        );
        User saved = userRepository.save(user);

        String token = jwtProvider.generateToken(saved.getEmail());
        AuthResponse response = new AuthResponse(saved.getId(), saved.getEmail(), saved.getFullName());
        response.setToken(token);
        return response;
    }

    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtProvider.generateToken(user.getEmail());
        AuthResponse response = new AuthResponse(user.getId(), user.getEmail(), user.getFullName());
        response.setToken(token);
        return response;
    }
}
