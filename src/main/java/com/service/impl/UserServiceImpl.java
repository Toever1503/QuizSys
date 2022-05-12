package com.service.impl;

import com.entity.RoleEntity;
import com.entity.UserEntity;
import com.entity.model.UserModel;
import com.repository.IRoleRepository;
import com.repository.IUserRepository;
import com.service.IBaseService;
import com.service.IMailService;
import com.service.IUserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService<UserEntity, UserModel, Long>, UserDetailsService {
    @Autowired
    IUserRepository iUserRepository;
    @Autowired
    IMailService iMailService;
    @Autowired
    IRoleRepository iRoleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

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
        UserEntity userEntity = UserEntity.builder()
                .id(model.getId())
                .username(model.getUsername())
                .password(passwordEncoder.encode(model.getPassword()))
                .email(model.getEmail())
                .fullname(model.getFullname())
                .avatar(model.getAvatar())
                .enabled(true)
                .build();
        List<RoleEntity> listRole = new ArrayList<>();
        model.getRoleIdList().stream().forEach(role ->{
            listRole.add(iRoleRepository.findById(role).get());
        });
        userEntity.setRoleEntityList(listRole);
        iUserRepository.save(userEntity);
        return userEntity;
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
    public boolean deleteByIds(List<Long> id) {
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
    public void sendMailResetPassword(String from, String toAddress, String subject, String content, String tokenString) {
        iMailService.sendTextMail(from, toAddress, subject, content, tokenString);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = iUserRepository.findByUsernameOrEmail(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new UserDetailsImpl(userEntity);
    }
}
