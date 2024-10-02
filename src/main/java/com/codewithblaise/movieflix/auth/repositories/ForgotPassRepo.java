package com.codewithblaise.movieflix.auth.repositories;

import com.codewithblaise.movieflix.auth.entities.ForgotPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForgotPassRepo extends JpaRepository<ForgotPassword,Integer> {

}
