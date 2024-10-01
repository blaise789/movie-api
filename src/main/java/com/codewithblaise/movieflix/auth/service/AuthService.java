package com.codewithblaise.movieflix.auth.service;

import com.codewithblaise.movieflix.auth.entities.User;
import com.codewithblaise.movieflix.auth.entities.UserRole;
import com.codewithblaise.movieflix.auth.repositories.UserRepository;
import com.codewithblaise.movieflix.auth.utils.AuthResponse;
import com.codewithblaise.movieflix.auth.utils.LoginRequest;
import com.codewithblaise.movieflix.auth.utils.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final  JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

     public AuthResponse createUser(RegisterRequest  registerRequest ){
         User user=User.builder()
                 .email(registerRequest.getEmail())
                 .username(registerRequest.getUsername())
                 .name(registerRequest.getName())
                 .password(passwordEncoder.encode(registerRequest.getPassword()))
                 .role(UserRole.USER)
                 .build();

         User savedUser=userRepository.save(user);
           var accessToken=jwtService.generateToken(savedUser);
           var refreshToken=refreshTokenService.createRefreshToken(savedUser.getEmail());
           return new AuthResponse(accessToken,refreshToken.getRefreshToken());

     }
     public AuthResponse loginUser(LoginRequest request){
      Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                 request.getPassword()
         ));

         System.out.println("hello");
        User user=userRepository.findByEmail(request.getEmail()).orElseThrow(()->new UsernameNotFoundException("user not found with email"+request.getEmail()));
         System.out.println(user);
        var accessToken=jwtService.generateToken(user);
        var refreshToken=refreshTokenService.createRefreshToken(request.getEmail());
        return new AuthResponse(accessToken,refreshToken.getRefreshToken());
     }

}
