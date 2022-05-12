package com.repository;

import com.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByUsername(String username);
    UserEntity findByUsernameOrEmail(String username);
}
