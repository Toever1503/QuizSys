package com.web;

import com.entity.UserEntity;
import com.repository.IUserRepository;
import com.service.IMailService;
import com.service.impl.MailServiceImpl;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api")
public class ForgotPasswordResources {
    @Autowired
    IUserRepository iUserRepository;
    @Autowired
    IMailService iMailService;
    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam(name = "email") String email){
        UserEntity userEntity = iUserRepository.findByEmail(email);
        if (userEntity != null){
            String tokenString = RandomString.make(45);
            try {
                iMailService.sendEmail(email,tokenString);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }else {
        }
        return "forgot_password_form";
    }
}
