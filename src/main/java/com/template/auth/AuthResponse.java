package com.template.auth;

import lombok.Data;

@Data
public class AuthResponse {
    private String authId;
    private String token;
}
