package com.codewithblaise.movieflix.auth.repositories;

import com.codewithblaise.movieflix.auth.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
    @Transactional
    @Modifying
    @Query("update User user where user.email=?1 set user.password=?2")
    void updateUserPassword(String email,String password);
}
