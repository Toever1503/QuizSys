package com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.Properties;


@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    public static String HOST;
    public static String ROOT_CONTENT_SYS;

    WebConfiguration(@Value("${web-content.root-content}") String ROOT_CONTENT_SYS,
                     @Value("${web-content.host}") String HOST) {
        WebConfiguration.HOST = HOST;
        ROOT_CONTENT_SYS = new File(ROOT_CONTENT_SYS).getAbsolutePath();
        System.out.println(ROOT_CONTENT_SYS);
        WebConfiguration.ROOT_CONTENT_SYS = ROOT_CONTENT_SYS;
        System.out.println(ROOT_CONTENT_SYS);
    }

    @Bean
    public WebMvcConfigurer configurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://10.0.0.93:3000/", "http://10.0.0.93", "http://165.22.48.208")
                        .allowCredentials(true)
                        .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS");
            }
        };
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:".concat(ROOT_CONTENT_SYS + "/"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //    @Bean
//    public JavaMailSender javaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.mailtrap.io");
//        mailSender.setPort(465);
//
//        mailSender.setUsername("chiphongteo1123@gmail.com");
//        mailSender.setPassword("phongcao123");
//
//        Properties props = mailSender.getJavaMailProperties();
//        props.setProperty("mail.smtp.auth", "true");
//
//        return mailSender;
//    }
    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("email-smtp.us-west-1.amazonaws.com");
        mailSender.setPort(587);
        mailSender.setUsername("AKIA5DWEWGJIHJJZGCXN");
        mailSender.setPassword("BP+kKjV7GuP8WGRY3Congrx7PxS+CQ2r2vASBCXvx/Us");
//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(587);
//        mailSender.setUsername("chiphongteo1123@gmail.com");
//        mailSender.setPassword("phongcao123");

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.smtp.auth", true);
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.debug", "false");
        javaMailProperties.put("mail.smtp.ssl.trust", "*");

        mailSender.setJavaMailProperties(javaMailProperties);

        return mailSender;
    }


}
