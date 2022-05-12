package com.web;

import com.entity.UserEntity;
import com.jwt.payload.request.ForgotPasswordRequest;
import com.jwt.payload.request.ResetPasswordRequest;
import com.repository.IUserRepository;
import com.service.IMailService;
import com.service.IUserService;
import com.service.impl.MailServiceImpl;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api")
public class ForgotPasswordResources {
    @Autowired
    IUserRepository iUserRepository;
    @Autowired
    IMailService iMailService;
    @Autowired
    IUserService iUserService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestBody ForgotPasswordRequest request) {
        UserEntity userEntity = iUserRepository.findByUsernameOrEmail(request.getUsername());
        if (userEntity != null) {
            String tokenString = RandomString.make(45);
//                iMailService.sendEmail(userEntity.getEmail(), tokenString);
            iMailService.sendTextMail("chiphongteo1123@gmail.com", userEntity.getEmail(), "Subject", "Content", tokenString);
            iUserService.updateUserToken(userEntity.getEmail(), tokenString);
        } else {
        }
        return null;
    }

    @PostMapping("/reset_password")
    public String resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        UserEntity userEntity = iUserService.findByToken(resetPasswordRequest.getToken());
        if (userEntity != null) {
            if (resetPasswordRequest.getPassword().equals(resetPasswordRequest.getConfirm_pass())) {
                userEntity.setResetPassWordToken(null);
                userEntity.setPassword(passwordEncoder.encode(resetPasswordRequest.getPassword()));
                iUserRepository.save(userEntity);
            }
        }
        return null;
    }
}
