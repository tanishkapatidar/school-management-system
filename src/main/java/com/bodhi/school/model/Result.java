package com.bodhi.school.model;

import lombok.Data;

@Data
public class Result {
    private String id;
    private String student_id;
    private String exam_id;
    private Integer obtained_marks;
}
