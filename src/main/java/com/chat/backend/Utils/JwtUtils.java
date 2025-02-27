package com.chat.backend.Utils;



import com.chat.backend.Exceptions.JwtTokenExpirationException;
import com.chat.backend.Services.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtils {

    @Value("${spring.jwt.expiration}")
    private long jwtExpiration;

    @Value("${spring.jwt.secret}")
    private String jwtSecret;
    
    private final CustomUserDetailsService customUserDetailsService;

    public JwtUtils(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return
                Jwts.parser()
                        .setSigningKey(jwtSecret)
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();
    }

    public String GenerateToken(String email, String uuid, String name, String picture) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);
        return Jwts.builder()
                .setSubject(email)
                .claim("id_user", uuid)
                .claim("name", name)
                .claim("picture", picture)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Boolean validateToken(String token) {
        try{
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .build()
                    .parseSignedClaims(token);
            return isTokenExpired(token);
        } catch (Exception e){
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        String username = extractEmail(token);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null, null);
    }

    private Boolean isTokenExpired(String token) {
        Date expirationDate = extractClaim(token, Claims::getExpiration);
        if(expirationDate.before(new Date())){
            throw new JwtTokenExpirationException(

            );
        }
        return true;
    }

}
