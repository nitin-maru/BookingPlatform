package org.example.bookingplatform.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static io.jsonwebtoken.Jwts.parser;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "YourJWTSecretKey"; // This should be securely stored or rotated
    private final Key key;

    public JwtUtil() {
        byte[] keyBytes = SECRET_KEY.getBytes();  // Consider using a Base64 encoded secret
        key = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    // Extract the username (subject) from the token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract the expiration date from the token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extract any claim from the token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract all claims from the token
    private Claims extractAllClaims(String token) {
        return parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

    // Check if the token has expired
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Generate a token for the given username
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    // Create a JWT with claims and a subject (username)
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours expiration
                .signWith(SignatureAlgorithm.HS256, key)
//                .signWith(key, SignatureAlgorithm.HS256)  // Use the new method with Key
                .compact();
    }

    // Validate the token and ensure it's not expired
    public Boolean validateToken(String token, String username) {
        final String tokenUsername = extractUsername(token);
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }
}