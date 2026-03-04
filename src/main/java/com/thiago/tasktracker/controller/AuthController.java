package com.thiago.tasktracker.controller;

import org.springframework.web.bind.annotation.*;
import com.thiago.tasktracker.model.User;
import com.thiago.tasktracker.service.AuthService;
import com.thiago.tasktracker.dto.RegisterRequest;
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
        authService.register(request);
    }
}
