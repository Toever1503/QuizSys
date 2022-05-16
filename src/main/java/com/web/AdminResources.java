package com.web;

import com.entity.dto.ResponseDto;
import com.entity.dto.UserDTO;
import com.entity.model.UserModel;
import com.repository.IUserRepository;
import com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RolesAllowed(value = {"ROLE_ADMIN","ROLE_ADMINISTRATOR"})
public class AdminResources {
    @Autowired
    IUserService iUserService;
    @Autowired
    IUserRepository iUserRepository;

    @Transactional
    @PostMapping("/user/save")
    public Object saveUser(@RequestBody UserDTO userDTO) throws Exception {
        if (userDTO.getId() == null){
            if (iUserRepository.findByUsernameOrEmail(userDTO.getUsername()) != null
                    || iUserRepository.findByUsernameOrEmail(userDTO.getEmail()) != null)
                throw new Exception("User already exists");

        }
        UserModel userModel = UserModel.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .fullname(userDTO.getFullname())
                .avatar(userDTO.getAvatar())
                .roleIdList(userDTO.getRoleIdList())
                .build();
        return ResponseDto.of(iUserService.add(userModel),"Save User");
    }
    @PostMapping("/user/update")
    public Object updateUser(@RequestBody UserDTO userDTO) throws Exception {
        if (iUserRepository.findById(userDTO.getId()) == null) throw  new Exception("User not found !");
        UserModel userModel = UserModel.builder()
                .id(userDTO.getId())
                .fullname(userDTO.getFullname())
                .avatar(userDTO.getAvatar())
                .roleIdList(userDTO.getRoleIdList())
                .build();
        return ResponseDto.of(iUserService.update(userModel),"Update User");
    }

    @GetMapping("/user/find-all")
    public Object getAll(Pageable pageable){
        return ResponseDto.of(iUserService.findAll(pageable),"Find all User");
    }
}
