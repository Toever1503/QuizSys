package com.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="question")
@Builder
public class QuestionEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="hasmore")
    private boolean hasmore;
    @Column(name="content")
    private String content;
    @ManyToOne
    private SubjectEntity subjectEntity;
    @OneToMany(mappedBy = "questionEntity", cascade = CascadeType.ALL)
    private List<AnswerEntity> listaAnswerEntity;
}
