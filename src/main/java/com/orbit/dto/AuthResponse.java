package com.orbit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private Long id;
    private String email;
    private String fullName;
    private String token;

    public AuthResponse(Long id, String email, String fullName) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
    }
}
