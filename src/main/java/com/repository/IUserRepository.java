package com.repository;

import com.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);
    UserEntity findByResetPassWordToken(String token);
    @Query("SELECT u FROM UserEntity u WHERE u.username = :usename or u.email = :usename ")
    UserEntity findByUsernameOrEmail(@Param(value = "usename") String username);

}
