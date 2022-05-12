package com.entity.model;

import com.entity.QuestionEntity;
import com.entity.SubjectEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SubjectModel {
    private Long id;
    private Integer maxTime;
    private Integer totalQuestions;

    public static SubjectEntity modelToEntity(SubjectModel subjectModel) {
        if(subjectModel == null) throw new IllegalArgumentException("SubjectModel is null");
        return SubjectEntity.builder()
                .id(subjectModel.getId())
                .maxTime(subjectModel.getMaxTime())
                .totalQuestions(subjectModel.getTotalQuestions())
                .build();
    }
}
