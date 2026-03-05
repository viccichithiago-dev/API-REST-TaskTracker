package com.thiago.tasktracker.service;

import com.thiago.tasktracker.model.User;
import com.thiago.tasktracker.model.Role;
import com.thiago.tasktracker.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.thiago.tasktracker.dto.RegisterRequest;
import com.thiago.tasktracker.dto.AuthResponse;
import com.thiago.tasktracker.security.JwtService;
import com.thiago.tasktracker.dto.LoginRequest;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       JwtService jwtService,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());

        // Siempre USER
        user.setRole(Role.USER);

        // Hashear contraseña
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token);
    }
}