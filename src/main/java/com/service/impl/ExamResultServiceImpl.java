package com.service.impl;

import com.entity.ExamResultEntity;
import com.entity.model.ExamResultModel;
import com.repository.ExamResultRepository;
import com.service.IExamResultService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamResultServiceImpl implements IExamResultService {
    private final ExamResultRepository examResultRepository;

    public ExamResultServiceImpl(ExamResultRepository examResultRepository) {
        this.examResultRepository = examResultRepository;
    }

    ExamResultEntity toEntity(ExamResultModel model) {
        if (model == null) throw new RuntimeException("Exam result model is null");
        return ExamResultEntity.builder()
                .examRsId(model.getExamRsId())
                .totalScore(model.getTotalScore())
                .correctAns(model.getCorrectAns())
                .wrongAns(model.getWrongAns())
                .totalTime(model.getTotalTime())
                .build();
    }

    @Override
    public List<ExamResultEntity> findAll() {
        return this.examResultRepository.findAll();
    }

    @Override
    public Page<ExamResultEntity> findAll(Pageable page) {
        return this.examResultRepository.findAll(page);
    }

    @Override
    public ExamResultEntity findById(Long id) {
        return this.examResultRepository.findById(id).orElseThrow(() -> new RuntimeException("Exam result Not found"));
    }

    @Override
    public ExamResultEntity add(ExamResultModel model) {
        return save(model);
    }

    public ExamResultEntity save(ExamResultModel model) {
        ExamResultEntity examResultEntity = toEntity(model);
        //        examResultEntity.setExamed_user();
//        examResultEntity.setExamed_subject();
        return this.examResultRepository.save(examResultEntity);
    }

    @Override
    public List<ExamResultEntity> add(List<ExamResultModel> model) {
        return null;
    }

    @Override
    public ExamResultEntity update(ExamResultModel model) {
        return save(model);
    }

    @Override
    public boolean deleteById(Long id) {
        this.examResultRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteByIds(List<Long> ids) {
        this.examResultRepository.deleteAllById(ids);
        return true;
    }

    @Override
    public Page<ExamResultEntity> findAllByUser(Long examId, Pageable page) {
        return this.examResultRepository.findAllByExamedUserId(examId, page);
    }

    @Override
    public Page<ExamResultEntity> findAllBySubject(Long subjectId, Pageable page) {
        return this.examResultRepository.findAllByExamedSubjectId(subjectId, page);
    }
}
