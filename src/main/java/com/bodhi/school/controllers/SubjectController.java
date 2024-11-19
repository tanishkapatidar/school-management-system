package com.bodhi.school.controllers;

import com.bodhi.school.model.Subject;
import com.bodhi.school.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/subject")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/{id}")
    public Subject getSubjectById(@PathVariable("id") String id) throws Exception {
        return subjectService.getById(id);
    }

    @PostMapping("/")
    public Subject createSubject(@RequestBody Subject subject) throws Exception {
        return this.subjectService.create(subject);
    }
}
