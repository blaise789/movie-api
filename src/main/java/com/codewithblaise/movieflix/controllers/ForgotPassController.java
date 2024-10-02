package com.codewithblaise.movieflix.controllers;

import com.codewithblaise.movieflix.auth.entities.*;
import com.codewithblaise.movieflix.auth.repositories.ForgotPassRepo;
import com.codewithblaise.movieflix.auth.repositories.UserRepository;
import com.codewithblaise.movieflix.auth.service.MailService;
import com.codewithblaise.movieflix.exceptions.InvalidOtp;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
   private  final PasswordEncoder passwordEncoder
    @PostMapping("/verify")
    public ResponseEntity<String> sendVerifyEmail(@RequestBody Email email) {
        User user = userRepository.findByEmail(email.getEmail()).orElseThrow(() -> new UsernameNotFoundException("user with that email does not exist"));
        int otp=otpGenerator();
        MailBody mailBody = MailBody.builder()
                .to(email.getEmail())
                .subject("Otp for forgot password request")
                .text("verify the otp to change the password "+otp)
                .build();
        ForgotPassword forgotPassword= ForgotPassword.builder().user(user).expirationTime(new Date(System.currentTimeMillis()+2*60*1000)).otp(otp).build();
        ForgotPassword fpExists=forgotPassRepo.findByUser(user);
        if(fpExists !=null){
            forgotPassRepo.delete(fpExists);
        }
        mailService.sendMailMessage(mailBody);
        forgotPassRepo.save(forgotPassword);
        return new ResponseEntity<>("email sent for verification", HttpStatus.OK);


    }
    @PostMapping("/verifyOtp")
    public ResponseEntity<String> verifyOtp(@RequestBody OptVerifier verifier){
        User user=userRepository.findByEmail(verifier.getEmail()).orElseThrow(()->new UsernameNotFoundException("user not found"));
        ForgotPassword fp=forgotPassRepo.findByOtpAndUser(user,verifier.getOtp()).orElseThrow(()->new InvalidOtp("no otp that matches"));
        if(fp.getExpirationTime().before(new Date(System.currentTimeMillis()))){

            forgotPassRepo.delete(fp);
            throw new InvalidOtp("invalid otp out of date");
        }

      return ResponseEntity.ok("otp verified successfully");


    }
   @PostMapping("/changePassword/{email}")
   public ResponseEntity<String>  resetPassword(@RequestBody ChangePassword changePassword,@PathVariable String email){
        if(!changePassword.newPassword().equals(changePassword.confirmPassword())){
            return new ResponseEntity<>("password don't match",HttpStatus.EXPECTATION_FAILED);
        }
        User user=userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("user does n't exist"));
        userRepository.updateUserPassword(email, changePassword.newPassword());
        return  new ResponseEntity<>("user password updated successfully",HttpStatus.ACCEPTED);




   }
    public Integer otpGenerator() {
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }


}
