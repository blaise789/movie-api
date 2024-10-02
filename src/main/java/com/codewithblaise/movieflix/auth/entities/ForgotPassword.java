package com.codewithblaise.movieflix.auth.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ForgotPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer fgpId;
    @Column(nullable = false)
    private  Integer otp;
    @Column(nullable = false)
    private Date expirationTime;
    @OneToOne()
    private User user;

}
