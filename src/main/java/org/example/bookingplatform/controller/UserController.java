package org.example.bookingplatform.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

    @GetMapping("user/")
    public String home(@AuthenticationPrincipal OAuth2User principal) {
        System.out.println("Authenticated User: " + principal.getAttributes());
        return "Welcome to the homepage!";
    }
}
