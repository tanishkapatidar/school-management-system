package com.bodhi.school.controllers;

import com.bodhi.school.model.School;
import com.bodhi.school.services.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/schools")
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("/{id}")
    public School getSchoolById(@PathVariable("id") String id) throws Exception {
        return schoolService.getById(id);
    }

    @PostMapping("/")
    public School createSchool(@RequestBody School school) throws Exception {
        return this.schoolService.create(school);
    }
}
