package com.service.impl;

import com.entity.QuestionEntity;
import com.entity.SubjectEntity;
import com.entity.dto.SubjectDto;
import com.entity.model.QuestionModel;
import com.entity.model.SubjectModel;
import com.repository.IQuestionRepository;
import com.repository.ISubjectRepository;
import com.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceEmpl implements ISubjectService {

    @Autowired
    ISubjectRepository subjectRepository;
    @Autowired
    IQuestionRepository questionRepository;
    public SubjectEntity toEntity(SubjectModel subjectModel) {
        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setId(subjectModel.getId());
        subjectEntity.setMaxTime(subjectModel.getMaxTime());
        subjectEntity.setContent(subjectModel.getNameSubject());
        subjectEntity.setTotalQuestions(subjectModel.getTotalQuestions());
        List<QuestionEntity> list = questionRepository.findAllBySubjectEntityId(subjectEntity.getId());
        subjectEntity.setListQuestionEntity(list);
        return subjectEntity;
    }
    @Override
    public List<SubjectEntity> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Page<SubjectEntity> findAll(Pageable page) {
        return subjectRepository.findAll(page);
    }

    @Override
    public SubjectEntity findById(Long id) {
        return subjectRepository.findById(id).orElseThrow((() -> new RuntimeException("Not found")));
    }

    @Override
    public SubjectEntity add(SubjectModel model) {
        SubjectEntity subjectEntity = toEntity(model);
        return subjectRepository.save(subjectEntity);
    }

    @Override
    public List<SubjectEntity> add(List<SubjectModel> subjectModels) {
        return subjectModels.stream().map(this::add).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public SubjectEntity update(SubjectModel model) {
        SubjectEntity en  = findById(model.getId());
        en.setMaxTime(model.getMaxTime());
        en.setTotalQuestions(model.getTotalQuestions());
        return subjectRepository.save(en);
    }


    @Override
    public boolean deleteById(Long id) {
        this.subjectRepository.deleteById(id);
        return true;
    }
}
