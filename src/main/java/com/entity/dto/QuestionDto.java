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
    private SubjectEntity subjectEntity;
    private List<AnswerDto> answerDtoList;

    public static QuestionDto EntityToDto(QuestionEntity questionEntity) {
        return QuestionDto.builder()
                .id(questionEntity.getId())
                .hasmore(questionEntity.isHasmore())
                .content(questionEntity.getContent())
                .subjectEntity(questionEntity.getSubjectEntity())
                .answerDtoList(questionEntity.getListaAnswerEntity().stream().map(AnswerDto::EntityToDto).collect(java.util.stream.Collectors.toList()))
                .build();
    }
}
