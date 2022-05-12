package com.repository;

import com.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
@Repository
public interface IAnswerRepository extends JpaRepository<AnswerEntity, Long> {

    @Query("select p from AnswerEntity p where p.id in :ids")
    List<AnswerEntity> getAllByQuestionId(@Param("ids") List<Long> ids);
}
