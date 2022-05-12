package com.service.impl;

import com.entity.ExamResultEntity;
import com.entity.model.ExamResultModel;
import com.service.IExamResultService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EXamResultServiceImpl implements IExamResultService {
    @Override
    public List<ExamResultEntity> findAll() {
        return null;
    }

    @Override
    public Page<ExamResultEntity> findAll(Pageable page) {
        return null;
    }

    @Override
    public ExamResultEntity findById(Long id) {
        return null;
    }

    @Override
    public ExamResultEntity add(ExamResultModel model) {
        return null;
    }

    @Override
    public ExamResultEntity update(ExamResultModel model) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
