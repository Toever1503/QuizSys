package com.web;

import com.entity.dto.ExamResultDto;
import com.entity.dto.ResponseDto;
import com.entity.model.ExamResultModel;
import com.service.IExamResultService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/examResults")
public class ExamResultResources {

    private final IExamResultService examResultService;

    public ExamResultResources(IExamResultService examResultService) {
        this.examResultService = examResultService;
    }

    @GetMapping
    public Object getExamResults(Pageable page) {
        return ResponseDto.of(examResultService.findAll(page).map(ExamResultDto::toDto), "Get exam results");
    }

    @GetMapping("{id}")
    public Object getExamResult(@PathVariable Long id) {
        return ResponseDto.of(ExamResultDto.toDto(examResultService.findById(id)), "Get exam result");
    }

    @GetMapping("user/{id}")
    public Object getExamResultsByUser(Long id, Pageable page) {
        return ResponseDto.of(examResultService.findAllByUser(id, page).map(ExamResultDto::toDto), "Get exam results by user");
    }

    @GetMapping("subject/{id}")
    public Object getExamResultsBySubject(Long id, Pageable page) {
        return ResponseDto.of(examResultService.findAllBySubject(id, page).map(ExamResultDto::toDto), "Get exam results by subject");
    }

    @PostMapping
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public Object createExamResult(@RequestBody ExamResultModel examResultModel) {
        examResultModel.setExamRsId(null);
        return ResponseDto.of(ExamResultDto.toDto(examResultService.add(examResultModel)), "Create exam result");
    }

    @PatchMapping("{id}")
    public Object updateExamResult(@PathVariable Long id, @RequestBody ExamResultModel examResultModel) {
        examResultModel.setExamRsId(id);
        return ResponseDto.of(ExamResultDto.toDto(examResultService.update(examResultModel)), "Update exam result");
    }

    @DeleteMapping("{id}")
    public Object deleteExamResult(@PathVariable Long id) {
        return ResponseDto.of(examResultService.deleteById(id), "Delete exam result");
    }

    @DeleteMapping("batch/{ids}")
    public Object deleteExamResults(@PathVariable("ids") List<Long> ids) {
        return ResponseDto.of(examResultService.deleteByIds(ids), "Delete exam results");
    }

}
