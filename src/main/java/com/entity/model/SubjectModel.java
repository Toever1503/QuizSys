package com.entity.model;

import com.entity.QuestionEntity;
import com.entity.SubjectEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SubjectModel {
    private Long id;
    private Integer maxTime;
    private Integer totalQuestions;
    private List<QuestionModel> questionModels;
    private String nameSubject;
}
