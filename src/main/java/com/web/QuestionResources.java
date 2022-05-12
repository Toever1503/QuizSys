package com.web;

import com.entity.QuestionEntity;
import com.entity.dto.QuestionDto;
import com.entity.dto.ResponseDto;
import com.entity.model.QuestionModel;
import com.service.impl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/questions")
public class QuestionResources {
    @Autowired
    QuestionServiceImpl questionService;
    @GetMapping
    @Transactional
    public Object getQuestions(Pageable pageable) {
        return ResponseDto.of(this.questionService.findAll(pageable).map(ans -> QuestionDto.EntityToDto(ans)), "Get all");
    }
    @PostMapping
    @Transactional
    public Object addQuestion(@RequestBody QuestionModel questionModel){
        System.out.println(questionModel.getAnses());
        QuestionDto questionDto = QuestionDto.EntityToDto(questionService.add(questionModel));
        return ResponseDto.of(questionDto,"add");
    }
    @PostMapping("batch")
    @Transactional
    public Object addQuestions(@RequestBody List<QuestionModel> questionModel){
        List<QuestionDto> questionDto = questionService.add(questionModel).stream().map(x -> QuestionDto.EntityToDto(x)).collect(Collectors.toList());
        return ResponseDto.of(questionDto,"add");
    }
    @PatchMapping(value = "{id}")
    @Transactional
    public Object updateQuestion(@PathVariable Long id,@RequestBody QuestionModel questionModel){
        questionModel.setId(id);
        QuestionDto questionDto = QuestionDto.EntityToDto(questionService.update(questionModel));
        return ResponseDto.of(questionDto,"update");
    }
    @DeleteMapping(value = "{id}")
    @Transactional
    public Object deleteQuestion(@PathVariable Long id){
        return ResponseDto.of(questionService.deleteById(id),"Delete");
    }
}
