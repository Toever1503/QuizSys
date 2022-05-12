package com.entity.model;

import com.entity.AnswerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerModel {
    private Long id;
    private String name;
    private boolean isCorrect;
    private Long questionId;
}
