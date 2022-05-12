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
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean hasmore;
    private String content;
    private String fileUpload;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private SubjectEntity subjectEntity;
    @OneToMany(mappedBy = "questionEntity" ,cascade = CascadeType.PERSIST)
    private List<AnswerEntity> listaAnswerEntity;
}
