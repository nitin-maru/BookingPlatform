package org.example.bookingplatform.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError() {
        // Handle the error gracefully
        return "error";  // Map this to an "error.html" or JSP page in your templates directory
    }

    @GetMapping("/")
    public String home(@AuthenticationPrincipal OAuth2User principal) {
        System.out.println("Authenticated User: " + principal.getAttributes());
        return "Welcome to the homepage! ";
    }

    @GetMapping("/login")
    public String login() {
        return "login";  // This should return the login.html view
    }
}
