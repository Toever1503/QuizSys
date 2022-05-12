package com.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name ="subject")
public class SubjectEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name  ="subject_name")
    private String subjectName;
    @Column(name = "max_time")
    private Integer maxTime;
    @Column(name = "total_question")
    private Integer totalQuestions;
    @OneToMany(mappedBy = "subjectEntity")
    private List<QuestionEntity> listQuestionEntity;

}
