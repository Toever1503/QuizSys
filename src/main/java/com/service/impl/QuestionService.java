package com.service.impl;

import com.entity.dto.QuestionDto;
import com.entity.model.QuestionModel;
import com.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class QuestionService implements com.service.QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public QuestionDto findById(Long id) {
        return new QuestionDto();
    }

    @Override
    public Page<QuestionDto> findAll(Pageable page) {
        return null;
    }

    @Override
    public List<QuestionDto> findAll() {
        return null;
    }

    @Override
    public Page<QuestionDto> search(String q, Pageable page) {
        return null;
    }

    @Override
    public QuestionDto add(QuestionModel model) {
        return null;
    }

    @Override
    public QuestionDto update(QuestionModel model) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
