package com.bodhi.school.controllers;

import com.bodhi.school.model.Student;
import com.bodhi.school.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable("id") String id) throws Exception {
        return studentService.getById(id);
    }

    @PostMapping("/")
    public Student createStudent(@RequestBody Student student) throws Exception {
        return this.studentService.create(student);
    }
}

