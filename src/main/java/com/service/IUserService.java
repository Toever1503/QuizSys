package com.service;

import com.entity.UserEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService<T,M,K> extends IBaseService<T,M,K>{
    UserEntity findByToken(String token);
    void updateUserToken(String email, String token);
    void sendMailResetPassword(String from,String toAddress,String subject,String content,String tokenString);
}
