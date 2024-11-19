package com.bodhi.school.controllers;

import com.bodhi.school.model.Result;
import com.bodhi.school.services.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/result")
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping("/{id}")
    public Result getSchoolById(@PathVariable("id") String id) throws Exception {
        return resultService.getById(id);
    }

    @PostMapping("/")
    public Result createSchool(@RequestBody Result result) throws Exception {
        return this.resultService.create(result);
    }
}

