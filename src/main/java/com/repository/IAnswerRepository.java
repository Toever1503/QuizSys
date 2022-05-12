package com.repository;

import com.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAnswerRepository extends JpaRepository<AnswerEntity, Long> {
}
