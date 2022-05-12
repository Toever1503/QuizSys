package com.service.impl;

import com.entity.AnswerEntity;
import com.entity.QuestionEntity;
import com.entity.model.AnswerModel;
import com.entity.model.QuestionModel;
import com.entity.model.SubjectModel;
import com.repository.IAnswerRepository;
import com.repository.IQuestionRepository;
import com.repository.ISubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements com.service.QuestionService {

    @Autowired
    IQuestionRepository questionRepository;
    @Autowired
    private static ISubjectRepository iSubjectRepository;
    @Autowired
    IAnswerRepository iAnswerRepository;

    @Override
    public List<QuestionEntity> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Page<QuestionEntity> findAll(Pageable page) {
        return questionRepository.findAll(page);
    }

    @Override
    public QuestionEntity findById(Long id) {
        return questionRepository.findById(id).orElseThrow((() -> new RuntimeException("Not found")));
    }

    @Override
    public QuestionEntity add(QuestionModel model) {
        QuestionEntity questionEntity = modelToEntity(model);
        return questionRepository.save(questionEntity);
    }

    @Override
    public List<QuestionEntity> add(List<QuestionModel> questionModels) {
        return null;
    }

    @Override
    public QuestionEntity update(QuestionModel model) {
        QuestionEntity questionEntity = modelToEntity(model);
        return questionRepository.save(questionEntity);
    }

    @Override
    public boolean deleteById(Long id) {
        try{
            questionRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static QuestionEntity modelToEntity(QuestionModel questionModel) {
        if(questionModel == null) throw new IllegalArgumentException("questionModel is null");
        if(questionModel.getSubjectId() == null) throw new IllegalArgumentException("subject is null");
        return QuestionEntity.builder()
                .id(questionModel.getId())
                .hasmore(questionModel.isHasmore())
                .content(questionModel.getContent())
                .subjectEntity(iSubjectRepository.findById(questionModel.getSubjectId()).orElseThrow(() -> new RuntimeException("Not found")))
                .listaAnswerEntity(questionModel.getAnses() == null ? null : questionModel.getAnses().stream().map(AnswerServiceImpl::modelToEntity).collect(Collectors.toList()))
                .build();
    }
}
