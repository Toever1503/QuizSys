package com.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/examResults")
public class ExamResultResources {

    @GetMapping
    public Object getExamResults() {
        return "Exam results";
    }

}
