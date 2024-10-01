package com.codewithblaise.movieflix.auth.repositories;

import com.codewithblaise.movieflix.auth.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Integer> {
}
