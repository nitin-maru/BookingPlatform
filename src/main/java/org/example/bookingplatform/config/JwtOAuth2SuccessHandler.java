package org.example.bookingplatform.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtUtil jwtUtil;  // Utility class for creating and validating JWTs


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // Get the authenticated user details from OAuth2
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String username = oAuth2User.getAttribute("email");  // Or use another unique identifier like `sub`

        // Generate JWT for the authenticated user
        String jwtToken = jwtUtil.generateToken(username);

        // Set the JWT in the response header or body
        response.setHeader("Authorization", "Bearer " + jwtToken);
        response.getWriter().write("JWT Token: " + jwtToken);  // Optionally return it in the body
        response.sendRedirect("/user");  // Redirect to the /user endpoint
//        response.getWriter().flush();
    }

}
