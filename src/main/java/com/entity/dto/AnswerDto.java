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
    private boolean Correct;
    private Long idQuestion;

    public static AnswerDto EntityToDto(AnswerEntity answerEntity) {
        return AnswerDto.builder()
                .id(answerEntity.getId())
                .name(answerEntity.getName())
                .Correct(answerEntity.isCorrect())
                .idQuestion(answerEntity.getQuestionEntity().getId())
                .build();
    }
}
