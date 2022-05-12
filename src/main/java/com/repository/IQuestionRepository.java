package com.repository;

import com.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQuestionRepository extends JpaRepository<QuestionEntity, Long> {
}
