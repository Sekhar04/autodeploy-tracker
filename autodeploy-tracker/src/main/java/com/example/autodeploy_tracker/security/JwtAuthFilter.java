package com.example.autodeploy_tracker.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.
        UsernamePasswordAuthenticationToken;

import org.springframework.security.core.authority.
        SimpleGrantedAuthority;

import org.springframework.security.core.context.
        SecurityContextHolder;

import org.springframework.stereotype.Component;

import org.springframework.web.filter.
        OncePerRequestFilter;

import java.io.IOException;

import java.util.Collections;

@Component
public class JwtAuthFilter
        extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthFilter(
            JwtService jwtService) {

        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(

            HttpServletRequest request,

            HttpServletResponse response,

            FilterChain filterChain)

            throws ServletException, IOException {

        final String authHeader =
                request.getHeader(
                        "Authorization"
                );

        if(authHeader == null ||
                !authHeader.startsWith(
                        "Bearer "
                )) {

            filterChain.doFilter(
                    request,
                    response
            );

            return;
        }

        String jwt =
                authHeader.substring(7);

        String username =
                jwtService.extractUsername(jwt);

        String role =
                jwtService.extractRole(jwt);

        UsernamePasswordAuthenticationToken
                authenticationToken =

                new UsernamePasswordAuthenticationToken(

                        username,

                        null,

                        Collections.singletonList(

                                new SimpleGrantedAuthority(
                                        "ROLE_" + role
                                )
                        )
                );

        SecurityContextHolder
                .getContext()
                .setAuthentication(
                        authenticationToken
                );

        filterChain.doFilter(
                request,
                response
        );
    }
}