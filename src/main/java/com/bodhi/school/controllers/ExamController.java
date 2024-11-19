package com.bodhi.school.controllers;

import com.bodhi.school.model.Exam;
import com.bodhi.school.services.ExamService;
import com.bodhi.school.services.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/exam")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping("/{id}")
    public Exam getExamById(@PathVariable("id") String id) throws Exception {
        return examService.getById(id);
    }

    @PostMapping("/")
    public Exam createExam(@RequestBody Exam exam) throws Exception {
        return this.examService.create(exam);
    }
}
