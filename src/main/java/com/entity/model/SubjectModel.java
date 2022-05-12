package com.entity.model;

import com.entity.QuestionEntity;
import com.entity.SubjectEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SubjectModel {
    private Long id;
    private Integer maxTime;
    private Integer totalQuestions;
    private List<QuestionModel> questionModels;

    public static SubjectEntity modelToEntity(SubjectModel subjectModel) {
        if(subjectModel == null) throw new IllegalArgumentException("SubjectModel is null");
        return SubjectEntity.builder()
                .id(subjectModel.getId())
                .maxTime(subjectModel.getMaxTime())
                .totalQuestions(subjectModel.getTotalQuestions())
                .listQuestionEntity(subjectModel.questionModels == null ? null : subjectModel.questionModels.stream().map(QuestionModel::modelToEntity).collect(Collectors.toList()))
                .build();
    }
}
