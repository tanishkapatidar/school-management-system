package com.bodhi.school.services;

import com.bodhi.school.model.Exam;
import com.bodhi.school.model.Subject;
import com.bodhi.school.repository.ExamRepository;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public List<Exam> getAllBySubjectId(String subjectId){
        Result<Record> records = examRepository.findBySubjectId(subjectId);
        List<Exam> exams = new ArrayList<>();
        records.forEach(record -> exams.add(record.into(Exam.class)));
        return exams ;
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
