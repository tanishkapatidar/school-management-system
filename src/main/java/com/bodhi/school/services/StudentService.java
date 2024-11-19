package com.bodhi.school.services;

import com.bodhi.school.model.Student;
import com.bodhi.school.repository.StudentRepository;
import org.jooq.Record;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getById(String id) throws Exception {
        Record record = studentRepository.findById(id)
                .orElseThrow(() ->new Exception("Student with id %s not found".formatted(id)));
        return record.into(Student.class);
    }

    public Student create(Student student) throws Exception {
        if (student.getName() == null || student.getName().isEmpty()) {
            throw new Exception("Student name is null");
        }
        if(ObjectUtils.isEmpty(student.getId())){
            throw new Exception("Student id is null");
        }

        try{
            student.setId(UUID.randomUUID().toString());
            return this.studentRepository.save(student);
        }catch (Exception e){
            throw  new Exception("Unable to save student with id %s ".formatted(student.getId()));
        }
    }
}

