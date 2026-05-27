package com.example.autodeploy_tracker.service;

import com.example.autodeploy_tracker.auth.LoginRequest;
import com.example.autodeploy_tracker.auth.SignupRequest;
import com.example.autodeploy_tracker.model.User;
import com.example.autodeploy_tracker.repository.UserRepository;
import com.example.autodeploy_tracker.security.JwtService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public User signup(SignupRequest request) {

        User user = new User();

        user.setUsername(
                request.getUsername());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()));

        user.setRole(
                request.getRole());

        return userRepository.save(user);
    }

    public String login(LoginRequest request) {

        User user = userRepository
                .findByUsername(
                        request.getUsername())
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"));

        boolean validPassword =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword());

        if (!validPassword) {

            throw new RuntimeException(
                    "Invalid password");
        }

        return jwtService.generateToken(
                user.getUsername(),
                user.getRole());
    }
}