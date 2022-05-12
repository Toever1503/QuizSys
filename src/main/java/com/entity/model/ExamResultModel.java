package com.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamResultModel {
    private Long examRsId;

    private Long examedUser;

    private Long examedSubject;

    private Integer totalScore;

    private Integer correctAns;

    private Integer wrongAns;

    private Float totalTime;
}


