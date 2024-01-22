//package com.example.config;
//
//import java.util.Properties;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class MailConfig {
//	@Bean
//    public JavaMailSender javaMailService() {
//        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
//
//        // smtp 서버 주소
//        javaMailSender.setHost("smtp.naver.com");
//        javaMailSender.setUsername("doctorpick@naver.com");
//        javaMailSender.setPassword("a13579!@#");
//        
//        // 메일 인증서버 포트
//        javaMailSender.setPort(465); 
//
//     // 메일 인증서버 정보 가져오기
//        javaMailSender.setJavaMailProperties(getMailProperties()); 
//
//        return javaMailSender;
//    }
//    
//    private Properties getMailProperties() {
//        Properties properties = new Properties();
//        properties.setProperty("mail.transport.protocol", "smtp");
//        properties.setProperty("mail.smtp.auth", "true");
//        properties.setProperty("mail.smtp.starttls.enable", "true");
//        properties.setProperty("mail.debug", "true");
//        properties.setProperty("mail.smtp.ssl.trust","smtp.naver.com");
//        properties.setProperty("mail.smtp.ssl.enable","true");
//        return properties;
//    }
//	
//}
