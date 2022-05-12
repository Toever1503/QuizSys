package com.service;

import com.entity.ExamResultEntity;
import com.entity.model.ExamResultModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IExamResultService extends IBaseService<ExamResultEntity, ExamResultModel, Long> {
    Page<ExamResultEntity> findAllByUser(Long userId, Pageable page);
    Page<ExamResultEntity> findAllBySubject(Long subjectId, Pageable page);

}
