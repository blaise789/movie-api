package com.codewithblaise.movieflix.auth.service;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class JwtService {
    private final String SECRET_KEY="BLAISERCA2022";
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){

    }
}
