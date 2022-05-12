package com.repository;

import com.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<UserEntity, Long> {
}
