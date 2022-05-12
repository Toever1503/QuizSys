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

    public static AnswerEntity toEntity(AnswerModel answerModel){
        if(answerModel == null) throw new IllegalArgumentException("AnswerModel null");
        return AnswerEntity.builder()
                .id(answerModel.getId())
                .isCorrect(answerModel.isCorrect())
                .name(answerModel.getName())
                .build();
    }
}
