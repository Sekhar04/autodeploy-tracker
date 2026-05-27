package com.example.autodeploy_tracker.controller;

import com.example.autodeploy_tracker.auth.LoginRequest;
import com.example.autodeploy_tracker.auth.SignupRequest;
import com.example.autodeploy_tracker.model.User;
import com.example.autodeploy_tracker.service.AuthService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(
            AuthService authService) {

        this.authService = authService;
    }

    @PostMapping("/signup")
    public User signup(
            @RequestBody SignupRequest request) {

        return authService.signup(request);
    }

    @PostMapping("/login")
    public String login(
            @RequestBody LoginRequest request) {

        return authService.login(request);
    }
}