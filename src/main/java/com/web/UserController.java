package com.web;

import com.entity.RoleEntity;
import com.entity.UserEntity;
import com.jwt.JwtTokenProvider;
import com.jwt.payload.request.LoginRequest;
import com.jwt.payload.request.RegisterRequest;
import com.jwt.payload.response.LoginResponse;
import com.repository.IRoleRepository;
import com.repository.IUserRepository;
import com.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
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


    @PostMapping("/login")
    public LoginResponse getResponseAfterLogin(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwtToken = jwtTokenProvider.generateTokenFormUsername(userDetails.getUsername());
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return LoginResponse.builder()
                .id(userDetails.getUserEntity().getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .accessToken(jwtToken)
                .tokenType(new LoginResponse().getTokenType())
                .role(roles)
                .build();
    }
    @PostMapping("/register")
    public RegisterRequest register(@RequestBody RegisterRequest request){
        RoleEntity roleUser = iRoleRepository.getById(3L);
        List<RoleEntity> listRoleUser = new ArrayList<>();
        listRoleUser.add(roleUser);
        iUserRepository.save(new UserEntity(null,request.getUsername(),passwordEncoder.encode(request.getPassword()),null,null,null,listRoleUser));
        return request;
    }
}
