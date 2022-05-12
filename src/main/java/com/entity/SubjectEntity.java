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
    @Column(name = "time")
    private LocalDateTime time;
    @Column(name = "totalQuestion")
    private Integer totalQuestions;
    @OneToMany(mappedBy = "subjectEntity",cascade = CascadeType.PERSIST)
    private List<QuestionEntity> listQuestionEntity;

}
