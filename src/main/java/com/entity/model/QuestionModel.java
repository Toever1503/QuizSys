package com.entity.model;

import com.entity.QuestionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class QuestionModel {
    private Long id;
    private boolean hasmore;
    private String content;
    private Long subjectEntity;
    private List<AnswerModel> anses;

    public static QuestionModel build(QuestionEntity questionEntity) {
        return QuestionModel.builder()
                .id(questionEntity.getId())
                .hasmore(questionEntity.isHasmore())
                .content(questionEntity.getContent())
                .subjectEntity(questionEntity.getSubjectEntity().getId())
                .anses(AnswerModel.build(questionEntity.getAnses()))
                .build();
    }
}
