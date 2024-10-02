package com.codewithblaise.movieflix.auth.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptVerifier {
    private String email;
    private  Integer otp;
}
