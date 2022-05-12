package com.web;

import com.entity.dto.AnswerDto;
import com.entity.dto.ResponseDto;
import com.entity.model.AnswerModel;
import com.service.impl.AnswerServiceImpl;
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
        return ResponseDto.of(this.answerService.findAll(pageable).map(ans -> AnswerDto.EntityToDto(ans)), "Get all");
    }
    @PostMapping
    public Object addAnswer(@RequestBody AnswerModel answerModel){
        AnswerDto answerDto = AnswerDto.EntityToDto(answerService.add(answerModel));
        return ResponseDto.of(answerDto,"add");
    }

    @PatchMapping(value = "{id}")
    public Object updateAnswer(@PathVariable Long id,@RequestBody AnswerModel answerModel){
        AnswerDto answerDto = AnswerDto.EntityToDto(answerService.update(answerModel));
        return ResponseDto.of(answerDto,"update");
    }
    @DeleteMapping(value = "{id}")
    public Object deleteAnswer(@PathVariable Long id){
        return ResponseDto.of(answerService.deleteById(id),"Delete");
    }
}
