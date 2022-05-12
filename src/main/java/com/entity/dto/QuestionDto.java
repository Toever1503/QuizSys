package com.entity.dto;

import com.entity.QuestionEntity;
import com.entity.SubjectEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDto {
    private Long id;
    private boolean hasmore;
    private String content;
    private Long subjectId;
    private List<AnswerDto> answerDtos;

    public static QuestionDto EntityToDto(QuestionEntity questionEntity) {
        return QuestionDto.builder()
                .id(questionEntity.getId())
                .hasmore(questionEntity.isHasmore())
                .content(questionEntity.getContent())
                .subjectId(questionEntity.getSubjectEntity().getId())
                .answerDtos(questionEntity.getListaAnswerEntity() == null ? null: questionEntity.getListaAnswerEntity().stream().map(AnswerDto::EntityToDto).collect(java.util.stream.Collectors.toList()))
                .build();
    }
}
