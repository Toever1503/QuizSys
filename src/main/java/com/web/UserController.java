package com.web;

import com.jwt.JwtTokenProvider;
import com.repository.IRoleRepository;
import com.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    IUserRepository iUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    IRoleRepository iRoleRepository;
}
