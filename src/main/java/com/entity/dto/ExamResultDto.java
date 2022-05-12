package com.entity.dto;

import com.entity.ExamResultEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamResultDto {
    private Long examRsId;
    private UserDTO examedUser;
    private Long examedSubject;

    private Integer totalScore;

    private Integer correctAns;

    private Integer wrongAns;

    private Float totalTime;

    public static ExamResultDto toDto(ExamResultEntity examResult) {
        if (examResult == null) throw new IllegalArgumentException("ExamResultEntity is null");
        return ExamResultDto.builder()
                .examRsId(examResult.getExamRsId())
//                .examed_user
//                .examed_subject
                .totalScore(examResult.getTotalScore())
                .correctAns(examResult.getCorrectAns())
                .wrongAns(examResult.getWrongAns())
                .totalTime(examResult.getTotalTime())
                .build();
    }
}


