package com.example.demo.security.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.security.JwtAuthorizationManager;
import com.example.demo.security.authentication.JwtAuthorization;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    JwtAuthorizationManager jwtAuthorizationManager;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String jwttokenString = request.getHeader("Authorization");
        if (jwttokenString != null && jwttokenString.startsWith("Bearer ")) {
            jwttokenString = jwttokenString.replace("Bearer ", "");
            JwtAuthorization jwtAuthorization = new JwtAuthorization(jwttokenString);
            jwtAuthorizationManager.verify(() -> jwtAuthorization, null);
        }
        filterChain.doFilter(request, response);
    }
}
