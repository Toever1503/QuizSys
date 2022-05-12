package com.entity.dto;

import com.entity.QuestionEntity;
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
    private Long idSubjectEntity;
    private List<AnswerDto> anses;

    public static QuestionDto EntityToDto(QuestionEntity questionEntity) {
        return QuestionDto.builder()
                .id(questionEntity.getId())
                .hasmore(questionEntity.isHasmore())
                .content(questionEntity.getContent())
                .idSubjectEntity(questionEntity.getSubjectEntity().getId())
                .anses(questionEntity.getListaAnswerEntity() != null ? questionEntity.getListaAnswerEntity().stream().map(AnswerDto::EntityToDto).collect(java.util.stream.Collectors.toList()) : null)
                .build();
    }
}
