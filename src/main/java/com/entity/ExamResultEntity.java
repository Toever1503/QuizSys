package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Id;


@Entity
@Table(name = "exam_results")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "exam_result_sequence", sequenceName = "exam_result_id")
    private Long examRs_id;
    private Long user_id;
    private Long subject_id;

    @Column(name = "total_score")
    private Integer totalScore;

    @Column(name = "correct_ans")
    private Integer correctAns;

    @Column(name = "wrong_ans")
    private Integer wrongAns;

    @Column(name = "total_time")
    private Float totalTime;

}


