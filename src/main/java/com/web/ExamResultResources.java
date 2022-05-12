package com.web;

import com.entity.dto.ExamResultDto;
import com.entity.dto.ResponseDto;
import com.entity.model.ExamResultModel;
import com.service.IExamResultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/examResults")
public class ExamResultResources {

    private final IExamResultService examResultService;

    public ExamResultResources(IExamResultService examResultService) {
        this.examResultService = examResultService;
    }
    @Transactional
    @GetMapping
    public Object getExamResults(@ApiParam(example = "?page=0&size=10&sort=id,desc")Pageable page) {
        return ResponseDto.of(examResultService.findAll(page).map(ExamResultDto::toDto), "Get exam results");
    }
    @Transactional
    @GetMapping("{id}")
    public Object getExamResult(@ApiParam(name="Id of exam result") @PathVariable Long id) {
        return ResponseDto.of(ExamResultDto.toDto(examResultService.findById(id)), "Get exam result");
    }
    @Transactional
    @GetMapping("user/{id}")
    public Object getExamResultsByUser(@ApiParam(name="Id of user who has examed")Long id,@ApiParam(example = "?page=0&size=10&sort=id,desc")Pageable page) {
        return ResponseDto.of(examResultService.findAllByUser(id, page).map(ExamResultDto::toDto), "Get exam results by user");
    }
    @Transactional
    @GetMapping("subject/{id}")
    public Object getExamResultsBySubject(@ApiParam(name="Id of subject")Long id, Pageable page) {
        return ResponseDto.of(examResultService.findAllBySubject(id, page).map(ExamResultDto::toDto), "Get exam results by subject");
    }
    @Transactional
    @PostMapping
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public Object createExamResult(@RequestBody ExamResultModel examResultModel) {
        examResultModel.setExamRsId(null);
        return ResponseDto.of(ExamResultDto.toDto(examResultService.add(examResultModel)), "Create exam result");
    }
    @Transactional
    @PatchMapping("{id}")
    public Object updateExamResult(@ApiParam(name="Id of exam result")@PathVariable Long id, @RequestBody ExamResultModel examResultModel) {
        examResultModel.setExamRsId(id);
        return ResponseDto.of(ExamResultDto.toDto(examResultService.update(examResultModel)), "Update exam result");
    }
    @Transactional
    @DeleteMapping("{id}")
    public Object deleteExamResult(@ApiParam(name="Id of exam result")@PathVariable Long id) {
        return ResponseDto.of(examResultService.deleteById(id), "Delete exam result");
    }
    @Transactional
    @DeleteMapping("batch/{ids}")
    public Object deleteExamResults(@ApiParam(name="array Id of exam result")@PathVariable("ids") List<Long> ids) {
        return ResponseDto.of(examResultService.deleteByIds(ids), "Delete exam results");
    }

}
