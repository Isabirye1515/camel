package com.camel.config;


import java.util.Properties;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfig {
@Autowired
private CommonConfig commonConfig;
@Bean
public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}

@Bean
public MessageConverter jacksonMessageConverter(){
    return new Jackson2JsonMessageConverter();
}

@Bean
public ConnectionFactory connectionFactory(){
    CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
    cachingConnectionFactory.setUsername("admin");
    cachingConnectionFactory.setPassword("Admin123");
    return cachingConnectionFactory;
}

@Bean
public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
    
    RabbitTemplate template = new RabbitTemplate(connectionFactory);
    template.setMessageConverter(jacksonMessageConverter());
    return template;
}

@Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){ 
        
        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("Admin123")) 
                .roles("administrator")
                .build();
                
        UserDetails user = User.withUsername("elai")
                .password(encoder.encode("Eli123"))  
                .roles("user")
                .build();
                
        return new InMemoryUserDetailsManager(admin, user);
    }
    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(commonConfig.getEmailHost());
        mailSender.setPassword(commonConfig.getEmailPassword());
        mailSender.setUsername(commonConfig.getEmailUser());
        mailSender.setPort(commonConfig.getEmailPort());
        Properties props = mailSender.getJavaMailProperties();

props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable", "true");
props.put("mail.transport.protocol", "smtp");
props.put("mail.debug", "true");
        return mailSender;
    }





    
}
