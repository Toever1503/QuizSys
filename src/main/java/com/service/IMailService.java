package com.service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface IMailService {
    void sendEmail(String emailString, String tokenString) throws UnsupportedEncodingException, MessagingException;
    void sendTextMail(String from,String toAddress,String subject,String content,String tokenString);
}
