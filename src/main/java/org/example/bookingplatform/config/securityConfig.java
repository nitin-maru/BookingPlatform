package org.example.bookingplatform.config;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.Key;

@Configuration
@EnableWebSecurity
public class securityConfig {

    private final String SECRET_KEY = "YourJWTSecretKey";  // Use the same secret key

    @Autowired
    private JWTRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers("/login", "/register", "/api/public/**", "/error", "/api/theaters/**", "/api/bookings/**","/api-docs").permitAll()  // Permit public access
                                //.requestMatchers("/api/**").authenticated()  // Secure API endpoints
                                .anyRequest().authenticated()  // Secure everything else
                ).oauth2Login(oauth2 -> oauth2
                        //.loginPage("/login")
                        .defaultSuccessUrl("/user/", true)  // Redirect after OAuth2 login
                        .successHandler(authenticationSuccessHandler())  // Force redirect using success handler
                ).formLogin(form -> form
                        // form.loginPage("/login")  // Custom login page
                        .defaultSuccessUrl("/user/", true)  // Redirect after form login
                        .permitAll()).oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))  // JWT for API security
                ).sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // No sessions, use stateless JWT auth
                );

        // Add JWT filter before the UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        // Add any role mapping or custom converters for JWT claims here if needed
        return jwtAuthenticationConverter;
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler("/user") { // Redirect to /user after successful login
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                System.out.println("OAuth2 login successful: Redirecting to /user");
                super.onAuthenticationSuccess(request, response, authentication);
            }
        };
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new BearerTokenAuthenticationEntryPoint();
    }

    // Add the JwtDecoder bean
    @Bean
    public JwtDecoder jwtDecoder() {
        // Use the same secret key for decoding JWT as you used for signing
        byte[] keyBytes = SECRET_KEY.getBytes();
        Key key = new SecretKeySpec(keyBytes, "HmacSHA256");
        return NimbusJwtDecoder.withSecretKey((SecretKey) key).build();
    }
}
