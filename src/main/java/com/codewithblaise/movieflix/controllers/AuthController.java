package com.codewithblaise.movieflix.controllers;

import com.codewithblaise.movieflix.auth.utils.AuthResponse;
import com.codewithblaise.movieflix.auth.utils.LoginRequest;
import com.codewithblaise.movieflix.auth.utils.RegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest){
        return new ResponseEntity<>(null,HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginRequest> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(null);
    }

}
