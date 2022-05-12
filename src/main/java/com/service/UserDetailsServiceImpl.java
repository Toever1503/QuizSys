package com.service;

import com.entity.UserEntity;
import com.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    IUserRepository iUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = iUserRepository.findByUsername(username);
        if (username == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new UserDetailsImpl(userEntity);
    }

    @Transactional
    public UserDetails loadUserByID(Long id){
        UserEntity userEntity = iUserRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );
        return new UserDetailsImpl(userEntity);
    }
}