package com.codewithblaise.movieflix.auth.repositories;

import com.codewithblaise.movieflix.auth.entities.ForgotPassword;
import com.codewithblaise.movieflix.auth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ForgotPassRepo extends JpaRepository<ForgotPassword,Integer> {

     @Query("select fp from ForgotPassword fp where  fp.user=?1 and fp.otp=?2")
     Optional<ForgotPassword> findByOtpAndUser(User user, Integer otp);
     @Query("select fp from ForgotPassword fp where fp.user=?1")
     ForgotPassword findByUser(User user);
}
