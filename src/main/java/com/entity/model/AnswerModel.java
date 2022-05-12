package com.entity.model;

import com.entity.AnswerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerModel {
    private Long id;
    private String name;
    private boolean isCorrect;
    private Long questionEntity;

    public AnswerModel(AnswerEntity answerEntity){
        this.id = answerEntity.getId();
        this.name = answerEntity.getName();
        this.isCorrect = answerEntity.get
    }
}
