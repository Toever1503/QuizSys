package com.entity.dto;

import com.entity.AnswerEntity;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerDto {
    private Long id;
    private String name;
    private boolean isCorrect;
    private QuestionDto questionEntity;

    public static AnswerDto EntityToDto(AnswerEntity answerEntity) {
        return AnswerDto.builder()
                .id(answerEntity.getId())
                .name(answerEntity.getName())
                .isCorrect(answerEntity.isCorrect())
                .questionEntity(QuestionDto.EntityToDto(answerEntity.getQuestionEntity()))
                .build();
    }
}
