package com.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class QuestionModel {
    private Long id;
    private boolean hasmore;
    private String content;
    private Long subjectEntity;
    private List<AnswerModel> anses;
}
