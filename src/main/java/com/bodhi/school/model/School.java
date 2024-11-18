package com.bodhi.school.model;


public class School {

    private String id;
    private String name;
    private String affiliation_number;
    private String exam_board;
    private String address;
    private Integer contact_information;
    private String email_id;

    public String getId() {
        return id;
    }
    public void setId(String id) {
     this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAffiliation_number() {
        return affiliation_number;
    }
    public void setAffiliation_number(String affiliation_number) {
        this.affiliation_number = affiliation_number;
    }
    public String getExam_board() {
        return exam_board;
    }
    public void setExam_board(String exam_board) {
        this.exam_board = exam_board;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Integer getContact_information() {
        return contact_information;
    }
    public void setContact_information(Integer contact_information) {
        this.contact_information = contact_information;
    }
    public String getEmail_id() {
        return email_id;
    }
    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

}
