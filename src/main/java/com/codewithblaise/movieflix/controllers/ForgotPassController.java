package com.codewithblaise.movieflix.controllers;

import com.codewithblaise.movieflix.auth.entities.Email;
import com.codewithblaise.movieflix.auth.entities.ForgotPassword;
import com.codewithblaise.movieflix.auth.entities.MailBody;
import com.codewithblaise.movieflix.auth.entities.User;
import com.codewithblaise.movieflix.auth.repositories.ForgotPassRepo;
import com.codewithblaise.movieflix.auth.repositories.UserRepository;
import com.codewithblaise.movieflix.auth.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forgot")
public class ForgotPassController {
    private final UserRepository userRepository;
    private final MailService  mailService;
    private final ForgotPassRepo forgotPassRepo;

    @PostMapping("/verify")
    public ResponseEntity<String> sendVerifyEmail(@RequestBody Email email) {
        User user = userRepository.findByEmail(email.getEmail()).orElseThrow(() -> new UsernameNotFoundException("user with that email does not exist"));
        int otp=otpGenerator();
        MailBody mailBody = MailBody.builder()
                .to(email.getEmail())
                .subject("Otp for forgot password request")
                .text("verify the otp to change the password"+otp)
                .build();
        ForgotPassword forgotPassword= ForgotPassword.builder().user(user).expirationTime(new Date(System.currentTimeMillis()+2*60*1000)).otp(otp).build();
        mailService.sendMailMessage(mailBody);
        forgotPassRepo.save(forgotPassword);
        return new ResponseEntity<>("email sent for verification", HttpStatus.OK);


    }

    public Integer otpGenerator() {
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }


}
