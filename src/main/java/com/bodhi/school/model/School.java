package com.bodhi.school.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class School {
    private String id;
    private String name;
    private String affiliation_number;
    private String exam_board;
    private String address;
    private String contact;
    private String email_id;
}
