package com.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name="answer")
public class AnswerEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_answer")
    private String name;
    private boolean isCorrect;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private QuestionEntity questionEntity;
}
