package com.entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamResultModel {
    private Long examRs_id;
    private Long user_id;
    private Long subject_id;
    private Integer totalScore;
    private Integer correctAns;
    private Integer wrongAns;
    private Float totalTime;
}


