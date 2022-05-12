package com.web;

import com.entity.RoleEntity;
import com.entity.UserEntity;
import com.entity.dto.ResponseDto;
import com.jwt.JwtTokenProvider;
import com.jwt.payload.request.LoginRequest;
import com.jwt.payload.request.RegisterRequest;
import com.jwt.payload.response.LoginResponse;
import com.repository.IRoleRepository;
import com.repository.IUserRepository;
import com.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserResources {
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
    public Object getResponseAfterLogin(@RequestBody LoginRequest loginRequest) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        if (userDetails == null) throw new Exception("User not found !");
        String jwtToken = jwtTokenProvider.generateTokenFormUsername(userDetails.getUsername());
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseDto.of(LoginResponse.builder()
                .id(userDetails.getUserEntity().getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .accessToken(jwtToken)
                .tokenType(new LoginResponse().getTokenType())
                .role(roles)
                .build(),"Login");

    }


    @PostMapping("/register")
    public Object register(@RequestBody RegisterRequest request) throws Exception {
        UserEntity userEntity = iUserRepository.findByUsernameOrEmail(request.getUsername());
        if (userEntity != null) {
            throw new Exception("User already exists");
        } else {
            RoleEntity roleUser = iRoleRepository.getById(3L);
            List<RoleEntity> listRoleUser = new ArrayList<>();
            listRoleUser.add(roleUser);
            iUserRepository.save(new UserEntity(
                    null,
                    request.getUsername(),
                    passwordEncoder.encode(request.getPassword()),
                    request.getFullname(),
                    request.getEmail(),
                    null,
                    true,
                    null,listRoleUser));
        }
        return ResponseDto.of(new RegisterRequest(request.getUsername(), null,request.getEmail(), request.getFullname()),"Register");
    }


}
