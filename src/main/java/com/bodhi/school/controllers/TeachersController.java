package com.bodhi.school.controllers;

import com.bodhi.school.model.Teachers;
import com.bodhi.school.services.TeachersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/teachers")
public class TeachersController {

    private final TeachersService teachersService;

    public TeachersController(TeachersService teachersService) {
        this.teachersService = teachersService;
    }

    @GetMapping("/{id}")
    public Teachers getTeachersById(@PathVariable("id") String id) throws Exception {
        return teachersService.getById(id);
    }

    @PostMapping("/")
    public Teachers createTeachers(@RequestBody Teachers teacher) throws Exception {
        return this.teachersService.create(teacher);
    }
}
