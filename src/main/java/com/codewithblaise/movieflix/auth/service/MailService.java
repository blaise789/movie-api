package com.codewithblaise.movieflix.auth.service;

import com.codewithblaise.movieflix.auth.entities.MailBody;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    public void sendMailMessage(MailBody mailBody){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(mailBody.to());
        message.setSubject(mailBody.subject());
        message.setText(mailBody.text());
        mailSender.send(message);

    }
}
