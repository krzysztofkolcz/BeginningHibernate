package com.kkolcz;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;

public class MyMail{

    JavaMailSenderImpl mailSender;

    public MyMail(){ }

    public MyMail(JavaMailSenderImpl mailSender){
        this.mailSender = mailSender;
    }

    public void setMailSender(JavaMailSenderImpl mailSender){
        this.mailSender = mailSender;
    }

    public void sendSimpleEmail(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("hosting@power.com.pl");
        message.setTo(to);
        message.setSubject("testowy Mail");
        message.setText("testowy tekst w mailu");
        mailSender.send(message);
    }

}
