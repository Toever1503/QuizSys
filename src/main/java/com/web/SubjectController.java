package com.web;

import com.entity.SubjectEntity;
import com.entity.dto.ResponseDto;
import com.entity.dto.SubjectDto;
import com.entity.model.SubjectModel;
import com.service.impl.SubjectServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectServiceImpl subjectServiceImpl;

    public SubjectController(SubjectServiceImpl subjectServiceImpl) {
        this.subjectServiceImpl = subjectServiceImpl;
    }

    @Transactional
    @GetMapping
    public Object getAllQuestions(Pageable page) {
        Page<SubjectDto> subjectDtoList = subjectServiceImpl.findAll(page).map(SubjectDto::entityToDto);
        return ResponseDto.of(subjectDtoList, "Get all questions");
    }
    @Transactional
    @GetMapping("/{id}")
    public Object getById(@PathVariable Long id) {
        SubjectDto subjectDto = SubjectDto.entityToDto(subjectServiceImpl.findById(id));
        return ResponseDto.of(subjectDto, "Get question by id");
    }
    @Transactional
    @PostMapping
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public Object addSubject(@RequestBody SubjectModel subjectModel) {
        SubjectEntity subjectEntity = subjectServiceImpl.add(subjectModel);
        SubjectDto subjectDto = SubjectDto.entityToDto(subjectEntity);
        return ResponseDto.of(subjectDto, "Add subject success");
    }
    @Transactional
    @PatchMapping("/{id}")
    public Object editSubject(@PathVariable Long id, @RequestBody SubjectModel subjectModel) {
        subjectModel.setId(id);
        SubjectEntity subjectEntity = subjectServiceImpl.update(subjectModel);
        SubjectDto subjectDto = SubjectDto.entityToDto(subjectEntity);
        return ResponseDto.of(subjectDto, "Edit subject success");
    }
    @Transactional
    @DeleteMapping("/{id}")
    public Object deleteSubject(@PathVariable Long id, Pageable page) {
        if (subjectServiceImpl.deleteById(id)) {
            Page<SubjectDto> subjectDtoList = subjectServiceImpl.findAll(page).map(SubjectDto::entityToDto);
            return ResponseDto.of(subjectDtoList, "Delete subject success");
        } else {
            return ResponseDto.of(null, "Subject not found, delete subject failed");
        }
    }
}
