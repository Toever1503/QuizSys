package com.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamResultDto {
    private Long examRs_id;
    private Long user_id;
    private Long subject_id;

    private Integer totalScore;

    private Integer correctAns;

    private Integer wrongAns;

    private Float totalTime;

}


