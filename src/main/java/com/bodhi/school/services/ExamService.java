package com.bodhi.school.services;

import com.bodhi.school.model.Exam;
import com.bodhi.school.repository.ExamRepository;
import org.jooq.Record;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ExamService {
    private final ExamRepository examRepository;
    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public Exam getById(String id) throws Exception {
        Record record = examRepository.findById(id)
                .orElseThrow(() ->new Exception("Exam with id %s not found".formatted(id)));
        return record.into(Exam.class);
    }

    public Exam create(Exam exam) throws Exception {
        try{
            exam.setId(UUID.randomUUID().toString());
            return this.examRepository.save(exam);
        }catch (Exception e){
            throw  new Exception("Unable to save exam with id %s ".formatted(exam.getId()));
        }
    }
}
