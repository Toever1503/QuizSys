package com.service.impl;

import com.entity.SubjectEntity;
import com.entity.model.SubjectModel;
import com.repository.ISubjectRepository;
import com.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceEmpl implements ISubjectService {
    @Autowired
    ISubjectRepository subjectRepository;
    private static QuestionServiceImpl questionService;
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
        SubjectEntity subjectEntity = modelToEntity(model);
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
        try {
            this.subjectRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static SubjectEntity modelToEntity(SubjectModel subjectModel) {
        if(subjectModel == null) throw new IllegalArgumentException("SubjectModel is null");
        return SubjectEntity.builder()
                .id(subjectModel.getId())
                .maxTime(subjectModel.getMaxTime())
                .totalQuestions(subjectModel.getTotalQuestions())
                .listQuestionEntity(subjectModel.getQuestionModels() == null ? null : subjectModel.getQuestionModels().stream().map(QuestionServiceImpl::modelToEntity).collect(java.util.stream.Collectors.toList()))
                .build();
    }
}
