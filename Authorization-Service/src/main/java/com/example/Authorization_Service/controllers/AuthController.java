package com.example.authorization.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${security.productcomposite.admin.username:admin}")
    private String adminUsername;

    @Value("${security.productcomposite.admin.password:admin123}")
    private String adminPassword;

    @Value("${security.productcomposite.user.username:user}")
    private String userUsername;

    @Value("${security.productcomposite.user.password:user123}")
    private String userPassword;

    // Version 1 : Avec @RequestHeader (doit fonctionner avec curl)
    @PostMapping("/validate")
    public boolean validateCredentials(@RequestHeader("username") String username,
                                       @RequestHeader("password") String password,
                                       @RequestHeader("role") String role) {

        System.out.println("🔐 Validation attempt - Username: " + username + ", Role: " + role);

        // Vérifier les credentials ADMIN
        if (adminUsername.equals(username) && adminPassword.equals(password) && "ADMIN".equals(role)) {
            System.out.println("✅ ADMIN authentication successful");
            return true;
        }

        // Vérifier les credentials USER
        if (userUsername.equals(username) && userPassword.equals(password) && "USER".equals(role)) {
            System.out.println("✅ USER authentication successful");
            return true;
        }

        System.out.println("❌ Authentication failed for user: " + username);
        return false;
    }

    // Version 2 : Alternative avec @RequestBody (pour SoapUI)
    @PostMapping("/validate-body")
    public boolean validateCredentialsBody(@RequestBody AuthRequest request) {
        return validateCredentials(request.getUsername(), request.getPassword(), request.getRole());
    }

    // Classe pour la version Body
    public static class AuthRequest {
        private String username;
        private String password;
        private String role;

        // Getters et Setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }

    // Endpoint de test
    @GetMapping("/health")
    public String healthCheck() {
        return "Authorization Service is UP - Users configured: " + userUsername + ", " + adminUsername;
    }

    // Endpoint pour vérifier la configuration
    @GetMapping("/config")
    public String showConfig() {
        return String.format("Config - Admin: %s, User: %s", adminUsername, userUsername);
    }
}