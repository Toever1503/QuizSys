package com.repository;

import com.entity.ExamResultEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamResultRepository extends JpaRepository<ExamResultEntity, Long> {
    Page<ExamResultEntity> findAllByExamedUserId(Long userId, Pageable pageable);

    Page<ExamResultEntity> findAllByExamedSubjectId(Long subjectId, Pageable pageable);
}
