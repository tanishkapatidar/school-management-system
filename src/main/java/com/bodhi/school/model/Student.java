package com.bodhi.school.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@NoArgsConstructor
public class Student {
    private String id;
    private String name;
    @NonNull
    private String fathers_name;
    private String mothers_name;
    private String address;
    private String contact;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date_of_birth;
    private String gender;
    private String email_id;


}
