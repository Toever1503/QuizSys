package com.service.impl;

import com.entity.AnswerEntity;
import com.entity.QuestionEntity;
import com.entity.model.QuestionModel;
import com.repository.IAnswerRepository;
import com.repository.IQuestionRepository;
import com.repository.ISubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class QuestionServiceImpl implements com.service.QuestionService {

    @Autowired
    IQuestionRepository questionRepository;
    @Autowired
    ISubjectRepository iSubjectRepository;
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
        return questionRepository.findById(id).get();
    }

    @Override
    public QuestionEntity add(QuestionModel model) {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setContent(model.getContent());
        questionEntity.setHasmore(model.isHasmore());
        questionEntity.setSubjectEntity(iSubjectRepository.findById(model.getSubjectEntity()).get());
        List<AnswerEntity> list = new ArrayList<>();
        if (model.getAnses() != null){
            List<Long> ids = new ArrayList<>();
            model.getAnses().stream().forEach(x->ids.add(x.getId()));
            list = iAnswerRepository.getAllByQuestionId(ids);
        }

        questionEntity.setListaAnswerEntity(list);
        return questionRepository.save(questionEntity);
    }

    @Override
    public List<QuestionEntity> add(List<QuestionModel> model) {
        return null;
    }

    @Override
    public QuestionEntity update(QuestionModel model) {
        QuestionEntity questionEntity = questionRepository.findById(model.getId()).get();
        questionEntity.setContent(model.getContent());
        questionEntity.setHasmore(model.isHasmore());
        questionEntity.setSubjectEntity(iSubjectRepository.findById(model.getSubjectEntity()).get());
        List<AnswerEntity> list = new ArrayList<>();
        if (questionEntity.getSubjectEntity() != null){
            List<Long> ids = new ArrayList<>();
            model.getAnses().stream().forEach(x->ids.add(x.getId()));
            list = iAnswerRepository.getAllByQuestionId(ids);
        }

        questionEntity.setListaAnswerEntity(list);
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
}
