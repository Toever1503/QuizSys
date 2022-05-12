package com.service.impl;

import com.entity.AnswerEntity;
import com.entity.QuestionEntity;
import com.entity.model.AnswerModel;
import com.repository.IAnswerRepository;
import com.repository.IQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerServiceImpl implements com.service.AnswerService {

    private IAnswerRepository iAnswerRepository;
    private static IQuestionRepository questionRepository;

    public AnswerServiceImpl(IAnswerRepository iAnswerRepository, IQuestionRepository questionRepository) {
        this.iAnswerRepository = iAnswerRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public List<AnswerEntity> findAll() {
        return iAnswerRepository.findAll();
    }

    @Override
    public Page<AnswerEntity> findAll(Pageable page) {
        return iAnswerRepository.findAll(page);
    }

    @Override
    public AnswerEntity findById(Long id) {
        return iAnswerRepository.findById(id).get();
    }

    @Override
    public AnswerEntity add(AnswerModel model) {
        AnswerEntity answerEntity = modelToEntity(model);
        return iAnswerRepository.save(answerEntity);
    }

    @Override
    public List<AnswerEntity> add(List<AnswerModel> answerModelList) {
        List<AnswerEntity> answerEntityList = answerModelList.stream().map(AnswerServiceImpl::modelToEntity).collect(Collectors.toList());
        return iAnswerRepository.saveAll(answerEntityList);
    }

    @Override
    public AnswerEntity update(AnswerModel model) {
        AnswerEntity answerEntity = findById(model.getId());
        answerEntity.setName(model.getName());
        answerEntity.setCorrect(model.isCorrect());
        answerEntity.setQuestionEntity(questionRepository.findById(model.getQuestionId()).get());
        return iAnswerRepository.save(answerEntity);
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            iAnswerRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static AnswerEntity modelToEntity(AnswerModel answerModel){
        if(answerModel == null) throw new IllegalArgumentException("AnswerModel null");
        return AnswerEntity.builder()
                .id(answerModel.getId())
                .name(answerModel.getName())
                .isCorrect(answerModel.isCorrect())
                .questionEntity(answerModel.getQuestionId() == null ? null : questionRepository.findById(answerModel.getQuestionId()).get())
                .build();
    }
}
