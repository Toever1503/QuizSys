package com.web;

import com.entity.QuestionEntity;
import com.entity.dto.QuestionDto;
import com.entity.dto.ResponseDto;
import com.entity.model.QuestionModel;
import com.service.impl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/questions")
public class QuestionResources {
    @Autowired
    QuestionServiceImpl questionService;
    @GetMapping
    public Object getQuestions(Pageable pageable) {
        return ResponseDto.of(this.questionService.findAll(pageable).map(ans -> QuestionDto.EntityToDto(ans)), "Get all");
    }
    @PostMapping
    public Object addQuestion(@RequestBody QuestionModel questionModel){
        QuestionDto questionDto = QuestionDto.EntityToDto(questionService.add(questionModel));
        return ResponseDto.of(questionModel,"add");
    }

    @PostMapping("batch")
    public Object addQuestions(@RequestBody List<QuestionModel> questionModel){
        QuestionDto questionDto = QuestionDto.EntityToDto((QuestionEntity) questionService.add(questionModel));
        return ResponseDto.of(questionModel,"add");
    }

    @PatchMapping(value = "{id}")
    public Object updateQuestion(@PathVariable Long id,@RequestBody QuestionModel questionModel){
        QuestionDto questionDto = QuestionDto.EntityToDto(questionService.update(questionModel));
        return ResponseDto.of(questionDto,"update");
    }
    @DeleteMapping(value = "{id}")
    public Object deleteQuestion(@PathVariable Long id){
        return ResponseDto.of(questionService.deleteById(id),"Delete");
    }
}
