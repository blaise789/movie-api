package com.codewithblaise.movieflix.auth.service;

import com.codewithblaise.movieflix.auth.entities.RefreshToken;
import com.codewithblaise.movieflix.auth.entities.User;
import com.codewithblaise.movieflix.auth.repositories.RefreshTokenRepository;
import com.codewithblaise.movieflix.auth.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final UserRepository userRepository;
    private  final RefreshTokenRepository tokenRepository;

    public RefreshToken createRefreshToken(String username){
        User user=userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("user not found with email"+username));
        RefreshToken refreshToken=user.getRefreshToken();
        if(refreshToken==null){
            long refreshTokenValidity=5*60*60*1000;
            refreshToken=RefreshToken.builder()
                    .refreshToken(UUID.randomUUID().toString())
                    .expirationTime(Instant.now().plusMillis(refreshTokenValidity))
                    .user(user)
                    .build();
            tokenRepository.save(refreshToken);
        }
        return refreshToken;

    }
    public RefreshToken verifyRefreshToken(String token){
        RefreshToken refreshToken=tokenRepository.findByRefreshToken(token).orElseThrow(()->new RuntimeException("refresh token not found"));
        if (refreshToken.getExpirationTime().compareTo(Instant.now())<0){
            tokenRepository.delete(refreshToken);
            throw  new RuntimeException("your refresh token is expired");
        }
        return refreshToken;

    }


}
