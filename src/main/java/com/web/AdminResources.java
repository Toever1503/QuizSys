package com.web;

import com.entity.dto.UserDTO;
import com.entity.model.UserModel;
import com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Transactional
    @PostMapping("/user/save")
    public ResponseEntity<?> saveOrUpdateUser(@RequestBody UserDTO userDTO){
        UserModel userModel = UserModel.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .fullname(userDTO.getFullname())
                .avatar(userDTO.getAvatar())
                .roleIdList(userDTO.getRoleIdList())
                .build();
        iUserService.add(userModel);
        return ResponseEntity.ok().body(userDTO);
    }

    @GetMapping("/user/get-all")
    public ResponseEntity<?> getAll(){
        List<UserModel> userModels = iUserService.findAll();
        List<UserDTO> dtoList = iUserService.findAll();

        return ResponseEntity.ok().body(null);
    }
}
