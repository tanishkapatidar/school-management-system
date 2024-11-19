package com.bodhi.school.model;

import lombok.Data;

import java.util.Date;

@Data
public class Student {
    private String id;
    private String name;
    private String fathers_name;
    private String mothers_name;
    private  String address;
    private Integer contact_information;
    private Date date_of_birth;
    private String gender;
    private String email_id;


}
