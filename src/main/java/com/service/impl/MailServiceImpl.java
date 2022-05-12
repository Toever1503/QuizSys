package com.service.impl;

import com.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class MailServiceImpl implements IMailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String emailString, String tokenString)
            throws UnsupportedEncodingException, MessagingException {

        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mailHelper = new MimeMessageHelper(mailMessage);

        mailHelper.setFrom("chiphongteo1123@gmail.com", "Reset PassWord");
        mailHelper.setTo(emailString);
        String resetPasswordString = "http://localhost:8080/reset_password?token=" + tokenString;
        String subject = "Here's the link to reset your password";

        String content = "<p>Hello !!!,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + resetPasswordString + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        mailHelper.setSubject(subject);

        mailHelper.setText(content, true);

        javaMailSender.send(mailMessage);
    }
    @Override
    public void  sendTextMail(String from,
                              String toAddress,
                              String subject,
                              String content,
                              String tokenString){
        final MimeMessage mesasge = this.javaMailSender.createMimeMessage();
        final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mesasge,"UTF-8");
        try {
            mimeMessageHelper.setTo(toAddress);
            try {
                mimeMessageHelper.setFrom(from,"Reset Password");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            mimeMessageHelper.setSubject(subject);
            String resetPasswordString = "http://localhost:8088/reset_password?token=" + tokenString;
            String textContent = "<h3> " +content+ " </h3>"
                    + "<p>Click the link below to change your password:</p>"
                    + "<p><a href=\"" + resetPasswordString + "\">Change my password</a></p>";
            mimeMessageHelper.setText(textContent,true);

            this.javaMailSender.send(mesasge);


        } catch (MessagingException e) {
            e.printStackTrace();

        }
    }
}
