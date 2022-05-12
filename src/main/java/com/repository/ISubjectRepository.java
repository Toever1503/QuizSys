package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.security.auth.Subject;

public interface ISubjectRepository extends JpaRepository<Subject, Long> {
}
