package com.camel.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service(value = "messagingService")
public class MessagingService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMessage(String reciever,String subject,String message) throws Exception{
        try{
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(reciever);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        javaMailSender.send(mailMessage);
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("Error occured while sending the messsage");
            
        }
        
        
    }
    
}
