package com.utils;

import com.entity.RoleEntity;
import com.entity.UserEntity;
import com.repository.IRoleRepository;
import com.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityUtil implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    IRoleRepository iRoleRepository;
    @Autowired
    IUserRepository iUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (iRoleRepository.findByRole("ROLE_ADMINISTRATOR") == null) {
            iRoleRepository.save(new RoleEntity(1L, "ROLE_ADMINISTRATOR",null));
        }
        if (iRoleRepository.findByRole("ROLE_ADMIN") == null) {
            iRoleRepository.save(new RoleEntity(2L, "ROLE_ADMIN",null));
        }
        if (iRoleRepository.findByRole("ROLE_USER") == null) {
            iRoleRepository.save(new RoleEntity(3L, "ROLE_USER",null));
        }
        RoleEntity roleAdministrator = iRoleRepository.findByRole("ROLE_ADMINISTRATOR");
        RoleEntity roleAdmin = iRoleRepository.findByRole("ROLE_ADMIN");
        RoleEntity roleUser = iRoleRepository.findByRole("ROLE_USER");

        List<RoleEntity> listRoleAdministrator = new ArrayList<>();
        listRoleAdministrator.add(roleAdministrator);
        listRoleAdministrator.add(roleAdmin);
        listRoleAdministrator.add(roleUser);

        List<RoleEntity> listRoleAdmin = new ArrayList<>();
        listRoleAdmin.add(roleAdmin);
        listRoleAdmin.add(roleUser);

        List<RoleEntity> listRoleUser = new ArrayList<>();
        listRoleUser.add(roleUser);

        if (iUserRepository.findByUsername("ADMINISTRATOR") == null){
            iUserRepository.save(new UserEntity(
                    1L,
                    "ADMINISTRATOR",
                    passwordEncoder.encode("123456"),
                    "ADMINISTRATOR",
                    "administrator@gmail.com",
                    null,
                    null,
                    listRoleAdministrator ));
        }
        if (iUserRepository.findByUsername("ADMIN") == null){
            iUserRepository.save(new UserEntity(
                    2L,
                    "ADMIN",
                    passwordEncoder.encode("123456"),
                    "ADMIN",
                    "admin@gmail.com",
                    null,
                    null,
                    listRoleAdmin ));
        }
        if (iUserRepository.findByUsername("USER") == null){
            iUserRepository.save(new UserEntity(
                    3L,
                    "USER",
                    passwordEncoder.encode("123456"),
                    "USER",
                    "user@gmail.com",
                    null,
                    null,
                    listRoleUser ));
        }
    }
}
