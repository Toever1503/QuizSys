package com.repository;

import com.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<ImageEntity, Long> {
}
