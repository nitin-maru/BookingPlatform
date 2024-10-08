package org.example.bookingplatform.config;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;  // Utility class to handle JWT creation/validation

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        // Extract the JWT token from the Authorization header
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);  // Remove "Bearer " from the token
            try {
                username = jwtUtil.extractUsername(jwt);
            } catch (ExpiredJwtException e) {
                // Handle expired token here (if needed)
            }
        }

        // If a valid JWT is present and no user is authenticated, authenticate the user
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Validate the JWT and extract user details
            if (jwtUtil.validateToken(jwt, username)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        username, null, new ArrayList<>());  // Optionally, provide roles here
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);  // Set user as authenticated
            }
        }
        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}