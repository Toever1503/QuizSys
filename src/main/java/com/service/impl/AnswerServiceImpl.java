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

    @Autowired
    private IAnswerRepository iAnswerRepository;
    @Autowired
    private IQuestionRepository questionRepository;

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
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setName(model.getName());
        QuestionEntity questionEntity = questionRepository.findById(model.getQuestionEntity()).get();
        answerEntity.setQuestionEntity(questionEntity);
        answerEntity.setCorrect(model.isCorrect());
        AnswerEntity answerSave = iAnswerRepository.save(answerEntity);
        return answerSave;
    }

    @Override
    public List<AnswerEntity> add(List<AnswerModel> model) {
//        QuestionEntity ques = questionRepository.findById(model.get(0).getQuestionEntity())
//                .orElseThrow(() -> new RuntimeException("Question id empty"));
//
//        List<AnswerEntity> list = model.stream().map(answerModel -> {
//            AnswerEntity ans = AnswerModel.toEntity(answerModel);
//            ans.setQuestionEntity(ques);
//            return ans;
//        }).collect(Collectors.toList());

        return null;
    }

    @Override
    public AnswerEntity update(AnswerModel model) {
        AnswerEntity answerEntity = iAnswerRepository.findById(model.getId()).get();
        answerEntity.setName(model.getName());
        QuestionEntity questionEntity = questionRepository.findById(model.getQuestionEntity()).get();
        answerEntity.setQuestionEntity(questionEntity);
        answerEntity.setCorrect(model.isCorrect());
        AnswerEntity answerSave = iAnswerRepository.save(answerEntity);
        return answerSave;
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
}
