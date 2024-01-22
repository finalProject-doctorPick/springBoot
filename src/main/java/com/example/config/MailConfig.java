package com.example.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
	
	@Value("${spring.mail.username}")
	String id;
	
	@Value("${spring.mail.password}")
	String password;
	
	@Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        // smtp 서버 주소
        javaMailSender.setHost("smtp.naver.com");
        javaMailSender.setUsername(id);
        javaMailSender.setPassword(password);
        
        // 메일 인증서버 포트
        javaMailSender.setPort(465); 

     // 메일 인증서버 정보 가져오기
        javaMailSender.setJavaMailProperties(getMailProperties()); 

        return javaMailSender;
    }
    
    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.debug", "true");
        properties.setProperty("mail.smtp.ssl.trust","smtp.naver.com");
        properties.setProperty("mail.smtp.ssl.enable","true");
        return properties;
    }
	
}
