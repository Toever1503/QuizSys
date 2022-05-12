package com.service.impl;

import com.entity.SubjectEntity;
import com.entity.dto.SubjectDto;
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
        SubjectEntity subjectEntity = SubjectModel.modelToEntity(model);
        return subjectRepository.save(subjectEntity);
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
