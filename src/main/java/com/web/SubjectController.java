package com.web;

import com.entity.SubjectEntity;
import com.entity.dto.ResponseDto;
import com.entity.dto.SubjectDto;
import com.entity.model.SubjectModel;
import com.service.impl.SubjectServiceEmpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private SubjectServiceEmpl subjectServiceEmpl;

    public SubjectController(SubjectServiceEmpl subjectServiceEmpl) {
        this.subjectServiceEmpl = subjectServiceEmpl;
    }

    @GetMapping
    public Object getAllQuestions(Pageable page) {
        Page<SubjectDto> subjectDtoList = subjectServiceEmpl.findAll(page).map(SubjectDto::entityToDto);
        return ResponseDto.of(subjectDtoList, "Get all questions");
    }

    @GetMapping("/{id}")
    public Object getById(@PathVariable Long id) {
        SubjectDto subjectDto = SubjectDto.entityToDto(subjectServiceEmpl.findById(id));
        return ResponseDto.of(subjectDto, "Get question by id");
    }

    @PostMapping
    public Object addSubject(@RequestBody SubjectModel subjectModel) {
        SubjectEntity subjectEntity = subjectServiceEmpl.add(subjectModel);
        SubjectDto subjectDto = SubjectDto.entityToDto(subjectEntity);
        return ResponseDto.of(subjectDto, "Add subject success");
    }

    @PatchMapping("/{id}")
    public Object editSubject(@PathVariable Long id, @RequestBody SubjectModel subjectModel) {
        subjectModel.setId(id);
        SubjectEntity subjectEntity = subjectServiceEmpl.update(subjectModel);
        SubjectDto subjectDto = SubjectDto.entityToDto(subjectEntity);
        return ResponseDto.of(subjectDto, "Edit subject success");
    }

    @DeleteMapping("/{id}")
    public Object deleteSubject(@PathVariable Long id, Pageable page) {
        if (subjectServiceEmpl.deleteById(id)) {
            Page<SubjectDto> subjectDtoList = subjectServiceEmpl.findAll(page).map(SubjectDto::entityToDto);
            return ResponseDto.of(subjectDtoList, "Delete subject success");
        } else {
            return ResponseDto.of(null, "Subject not found, delete subject failed");
        }
    }
}
