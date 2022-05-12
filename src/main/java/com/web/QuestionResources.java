package com.web;

import com.entity.QuestionEntity;
import com.entity.SubjectEntity;
import com.entity.dto.QuestionDto;
import com.entity.dto.ResponseDto;
import com.entity.dto.SubjectDto;
import com.entity.model.QuestionModel;
import com.service.impl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/questions")
public class QuestionResources {
    @Autowired
    QuestionServiceImpl questionService;

    @GetMapping
    public Object getQuestions(Pageable pageable) {
        return ResponseDto.of(this.questionService.findAll(pageable).map(ans -> QuestionDto.EntityToDto(ans)), "Get all questions successfully");
    }

    @GetMapping("/{id}")
    public Object getQuestionById(@PathVariable("id") Long id) {
        return ResponseDto.of(this.questionService.findById(id), "Get question by id successfully");
    }

    @PostMapping
    public Object addQuestion(@RequestBody QuestionModel questionModel) {
        QuestionEntity questionEntity = questionService.add(questionModel);
        QuestionDto questionDto = QuestionDto.EntityToDto(questionEntity);
        return ResponseDto.of(questionDto, "add question successfully");
    }

    @PostMapping("batch")
    public Object addQuestions(@RequestBody List<QuestionModel> questionModels) {
        List<QuestionEntity> questionEntityList = questionModels.stream().map(questionModel -> questionService.modelToEntity(questionModel)).collect(Collectors.toList());
        List<QuestionDto> questionDtos = questionEntityList.stream().map(questionEntity -> QuestionDto.EntityToDto(questionEntity)).collect(Collectors.toList());
        return ResponseDto.of(questionDtos, "add questions successfully");
    }

    @PatchMapping(value = "/{id}")
    public Object updateQuestion(@PathVariable Long id, @RequestBody QuestionModel questionModel) {
        questionModel.setId(id);
        QuestionEntity questionEntity = QuestionServiceImpl.modelToEntity(questionModel);
        QuestionDto questionDto = QuestionDto.EntityToDto(questionEntity);
        return ResponseDto.of(questionDto, "update question successfully");
    }

    @DeleteMapping(value = "/{id}")
    public Object deleteQuestion(@PathVariable Long id) {
        return ResponseDto.of(questionService.deleteById(id), "Delete question successfully");
    }
}
