package com.entity.model;

import com.entity.QuestionEntity;
import com.entity.SubjectEntity;
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
    private SubjectModel subjectModel;
    private List<AnswerModel> anses;

    public static QuestionEntity modelToEntity(QuestionModel questionModel) {

        return QuestionEntity.builder()
                .id(questionModel.getId())
                .hasmore(questionModel.isHasmore())
                .content(questionModel.getContent())
                .subjectEntity(SubjectModel.modelToEntity(questionModel.getSubjectModel()))
                .listaAnswerEntity(questionModel.anses.stream().map(AnswerModel::toEntity).collect(java.util.stream.Collectors.toList()) == null ? null : questionModel.anses.stream().map(AnswerModel::toEntity).collect(java.util.stream.Collectors.toList()))
                .build();
    }
}
