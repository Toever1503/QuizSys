package com.service;

import com.entity.QuestionEntity;
import com.entity.dto.AnswerDto;
import com.entity.dto.QuestionDto;
import com.entity.model.AnswerModel;
import com.entity.model.QuestionModel;

import java.util.List;

public interface QuestionService extends IBaseService<QuestionEntity, QuestionModel,Long>{
    List<QuestionEntity> updateBatch(List<QuestionModel> models);
}