package com.bodhi.school.model;

import lombok.Data;

@Data
public class Exam {
    private String id;
    private String exam_name;
    private String subject_id;
    private Integer total_marks;
}
