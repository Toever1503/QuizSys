package com.service.impl;

import com.entity.dto.AnswerDto;
import com.entity.model.AnswerModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class AnswerService implements com.service.AnswerService {
    @Override
    public AnswerDto findById(Long id) {
        return null;
    }

    @Override
    public Page<AnswerDto> findAll(Pageable page) {
        return null;
    }

    @Override
    public List<AnswerDto> findAll() {
        return null;
    }

    @Override
    public Page<AnswerDto> search(String q, Pageable page) {
        return null;
    }

    @Override
    public AnswerDto add(AnswerModel model) {
        return null;
    }

    @Override
    public AnswerDto update(AnswerModel model) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
