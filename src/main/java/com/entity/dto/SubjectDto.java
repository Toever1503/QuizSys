package com.entity.dto;

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
public class SubjectDto {
    private Long id;
    private Integer maxTime;
    private Integer totalQuestions;
    private String subjectName;

    public static SubjectDto entityToDto(SubjectEntity subjectEntity) {
        return SubjectDto.builder()
                .id(subjectEntity.getId())
                .maxTime(subjectEntity.getMaxTime())
                .totalQuestions(subjectEntity.getTotalQuestions())
                .subjectName(subjectEntity.getSubjectName())
                .build();
    }
}
