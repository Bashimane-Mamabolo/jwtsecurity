package org.bash.jwtsecurity.services.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    // We can intercept our request and response
    // Filter-chain design pattern. Is a chain of responsibilities
    // Calls the next filter within the chain
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;
        // Check jwt token in the header
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        // Extract token from the header
        jwtToken = authHeader.substring(7);
        // Extract userEmail from JWT token


    }


}
