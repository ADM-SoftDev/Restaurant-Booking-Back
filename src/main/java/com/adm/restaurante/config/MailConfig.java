package com.adm.restaurante.config;

import java.util.Properties;

import com.adm.restaurante.dto.GmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailConfig {

    @Autowired
    private GmailSender mailProperty;

    @Bean
    public JavaMailSender javaMailSender() {

        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.connectiontimeout", 10000);

        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
        javaMailSenderImpl.setJavaMailProperties(properties);
        javaMailSenderImpl.setHost(mailProperty.getHost());
        javaMailSenderImpl.setPort(mailProperty.getPort());
        javaMailSenderImpl.setUsername(mailProperty.getUsername());
        javaMailSenderImpl.setPassword(mailProperty.getPassword());

        return javaMailSenderImpl;

    }

}
