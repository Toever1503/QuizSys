package com.service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface IMailService {
    void sendEmail(String emailString, String tokenString) throws UnsupportedEncodingException, MessagingException;
}
