package com.servicesImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.service.EmailService;

@Service
public class EmailServiceImp implements EmailService {

    @Value("${spring.mail.username}") 
    private String sender;

    @Autowired 
    private JavaMailSender javaMailSender;

    /* public EmailService(JavaMailSender javaMailSender)
    {
        this.javaMailSender = javaMailSender;
    } */

    @Override
    public String sendMail(String recepient, String subject, String text)
    {
        try
        {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom(sender);
            mail.setTo(recepient);
            mail.setSubject(subject);
            mail.setText(text);

            javaMailSender.send(mail);
            
            return "success";
        }
        catch(Exception e)
        {return "email failed: " + e.getClass().toString() + ":\n" + e.getMessage();}
    }

}
