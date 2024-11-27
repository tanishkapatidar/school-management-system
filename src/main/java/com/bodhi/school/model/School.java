package com.bodhi.school.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class School {
    private String id;
    private String name;
    private String affiliation_number;
    private String exam_board;
    private String address;
    private String contact;
    private String email_id;
}
