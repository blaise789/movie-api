package com.codewithblaise.movieflix.auth.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;

@Entity
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;
    @Column(nullable = false,length = 500)
    @NotBlank(message = "refresh token can't be blank")
    private String refreshToken;
    @Column(nullable = false)
    private Instant expirationTime;

    @OneToOne
    private User user;
}
