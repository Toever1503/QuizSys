package com.repository;

import com.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IQuestionRepository extends JpaRepository<QuestionEntity, Long> {

    List<QuestionEntity> findAllBySubjectEntityId(Long id);
}
