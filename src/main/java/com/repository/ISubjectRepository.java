package com.repository;

import com.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.security.auth.Subject;

public interface ISubjectRepository extends JpaRepository<SubjectEntity, Long> {
}
