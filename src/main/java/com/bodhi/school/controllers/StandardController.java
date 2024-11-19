package com.bodhi.school.controllers;

import com.bodhi.school.model.Standard;
import com.bodhi.school.services.StandardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/class")
public class StandardController {

    private final StandardService standardService;

    public StandardController(StandardService standardService) {
        this.standardService = standardService;
    }

    @GetMapping("/{id}")
    public Standard getStandardById(@PathVariable("id") String id) throws Exception {
        return standardService.getById(id);
    }

    @PostMapping("/")
    public Standard createStandard(@RequestBody Standard standard) throws Exception {
        return this.standardService.create(standard);
    }
}
