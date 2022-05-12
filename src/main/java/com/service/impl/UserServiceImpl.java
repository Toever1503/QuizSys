package com.service.impl;

import com.entity.UserEntity;
import com.entity.model.UserModel;
import com.repository.IUserRepository;
import com.service.IBaseService;
import com.service.IMailService;
import com.service.IUserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService<UserEntity, UserModel,Long> {
    @Autowired
    IUserRepository iUserRepository;
    @Autowired
    IMailService iMailService;


    @Override
    public List<UserEntity> findAll() {
        return null;
    }

    @Override
    public Page<UserEntity> findAll(Pageable page) {
        return null;
    }

    @Override
    public UserEntity findById(Long id) {
        return null;
    }

    @Override
    public UserEntity add(UserModel model) {
        return null;
    }

    @Override
    public UserEntity update(UserModel model) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public UserEntity findByToken(String token) {
        return iUserRepository.findByResetPassWordToken(token);
    }

    @Override
    public void updateUserToken(String email, String token) {
        UserEntity userEntity = iUserRepository.findByEmail(email);
        userEntity.setResetPassWordToken(token);
        iUserRepository.save(userEntity);
    }
    @Override
    public void sendMailResetPassword(String from,String toAddress,String subject,String content,String tokenString) {
        iMailService.sendTextMail(from,toAddress,subject,content,tokenString);
    }
}
