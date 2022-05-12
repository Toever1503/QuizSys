package com.repository;

import com.entity.SubjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.security.auth.Subject;

@Repository
public interface ISubjectRepository extends JpaRepository<SubjectEntity, Long> {
    Page<SubjectEntity> findAll(Pageable pageable);
}
