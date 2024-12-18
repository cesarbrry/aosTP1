package com.aos.aosTP1.userService;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public String generateToken(String username) {
        // Implémentation simple pour générer un token fictif
        return "token-for-" + username;
    }
}
