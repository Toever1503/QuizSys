package com.entity.model;

import com.entity.QuestionEntity;
import com.entity.SubjectEntity;
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
    private Long subjectId;
    private List<AnswerModel> anses;

}
