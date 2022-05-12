package com.service.impl;

import com.entity.AnswerEntity;
import com.entity.QuestionEntity;
import com.entity.SubjectEntity;
import com.entity.dto.QuestionDto;
import com.entity.dto.ResponseDto;
import com.entity.model.AnswerModel;
import com.entity.model.QuestionModel;
import com.repository.IAnswerRepository;
import com.repository.IQuestionRepository;
import com.repository.ISubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements com.service.QuestionService {

    @Autowired
    IQuestionRepository questionRepository;
    @Autowired
    ISubjectRepository iSubjectRepository;
    @Autowired
    IAnswerRepository iAnswerRepository;

    AnswerEntity toEntity(AnswerModel answerModel) {
        AnswerEntity answer = new AnswerEntity();
        answer.setId(answerModel.getId());
        answer.setName(answerModel.getName());
        answer.setCorrect(answerModel.isCorrect());
        return answer;
    }

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

    public QuestionEntity toEntity(QuestionModel questionModel) {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setId(questionModel.getId());
        questionEntity.setContent(questionModel.getContent());
        questionEntity.setHasmore(questionModel.isHasmore());
        questionEntity.setSubjectEntity(iSubjectRepository.findById(questionModel.getIdsubject()).get());
        return questionEntity;
    }
    @Override
    public QuestionEntity add(QuestionModel model) {
        SubjectEntity subjectEntity = this.iSubjectRepository.findById(model.getIdsubject())
                .orElseThrow(() -> new RuntimeException("Subject not found"));
        QuestionEntity questionEntity = toEntity(model);
        List<AnswerEntity> ans = model.getAnses().stream().map(ansModel -> {
            AnswerEntity answerEntity = toEntity(ansModel);
            answerEntity.setQuestionEntity(questionEntity);
            return answerEntity;
        }).collect(Collectors.toList());
        questionEntity.setSubjectEntity(subjectEntity);
        questionEntity.setListaAnswerEntity(ans);

        return questionRepository.save(questionEntity);
    }

    @Override
    public List<QuestionEntity> add(List<QuestionModel> model) {
        return model.stream().map(this::add).collect(Collectors.toList());
    }

    @Override
    public QuestionEntity update(QuestionModel model) {
        QuestionEntity original = questionRepository.findById(model.getId())
                .orElseThrow(() -> new RuntimeException("Ques not found"));
        SubjectEntity subjectEntity = this.iSubjectRepository.findById(model.getIdsubject())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        List<Long> ansIds = new ArrayList<>();
        List<AnswerEntity> ans = model.getAnses().stream().map(ansModel -> {
            AnswerEntity answerEntity = toEntity(ansModel);
            answerEntity.setQuestionEntity(original);
            if (ansModel.getId() != null)
                ansIds.add(ansModel.getId());
            return answerEntity;
        }).collect(Collectors.toList());
        if (!ansIds.isEmpty())
            this.iAnswerRepository.deleteAllByIdNotIn(ansIds);
        original.setListaAnswerEntity(ans);
        original.setSubjectEntity(subjectEntity);

        original.setHasmore(model.isHasmore());
        original.setContent(model.getContent());
        original.setSubjectEntity(subjectEntity);
        return questionRepository.save(original);
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            questionRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteByIds(List<Long> id) {
        return false;
    }

    @Override
    public List<QuestionEntity> updateBatch(List<QuestionModel> models) {
        return models.stream().map(this::update).collect(Collectors.toList());
    }
}
