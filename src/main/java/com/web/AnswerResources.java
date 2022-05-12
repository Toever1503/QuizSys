package com.web;

import com.entity.AnswerEntity;
import com.entity.QuestionEntity;
import com.entity.dto.AnswerDto;
import com.entity.dto.QuestionDto;
import com.entity.dto.ResponseDto;
import com.entity.model.AnswerModel;
import com.service.impl.AnswerServiceImpl;
import com.service.impl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/answers")
public class AnswerResources {
    @Autowired
    AnswerServiceImpl answerService;
    @GetMapping
    public Object getAnswers(Pageable pageable) {
        return ResponseDto.of(this.answerService.findAll(pageable).map(ans -> AnswerDto.EntityToDto(ans)), "Get all answers successfully");
    }

    @GetMapping("/{id}")
    public Object getAnswerById(@PathVariable("id") Long id) {
        return ResponseDto.of(this.answerService.findById(id), "Get answer successfully");
    }

    @PostMapping
    public Object createAnswer(@RequestBody AnswerModel answerModel) {
        AnswerEntity answerEntity = answerService.modelToEntity(answerModel);
        AnswerDto answerDto = AnswerDto.EntityToDto(answerEntity);
        return ResponseDto.of(answerDto, "add answer successfully");
    }

    @PatchMapping("/{id}")
    public Object updateAnswer(@PathVariable("id") Long id, @RequestBody AnswerModel answerModel) {
        answerModel.setId(id);
        AnswerEntity answerEntity = AnswerServiceImpl.modelToEntity(answerModel);
        AnswerDto answerDto = AnswerDto.EntityToDto(answerEntity);
        return ResponseDto.of(answerDto, "update answer successfully");
    }

    @DeleteMapping("/{id}")
    public Object deleteAnswer(@PathVariable("id") Long id) {
        return ResponseDto.of(this.answerService.deleteById(id), "Delete answer successfully");
    }
}
