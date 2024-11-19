package com.bodhi.school.services;

import com.bodhi.school.model.Subject;
import com.bodhi.school.repository.SubjectRepository;
import org.jooq.Record;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject getById(String id) throws Exception {
        Record record = subjectRepository.findById(id)
                .orElseThrow(() ->new Exception("Subject with id %s not found".formatted(id)));
        return record.into(Subject.class);
    }

    public Subject create(Subject subject) throws Exception {
        try{
            subject.setId(UUID.randomUUID().toString());
            return this.subjectRepository.save(subject);
        }catch (Exception e){
            throw  new Exception("Unable to save subject with id %s ".formatted(subject.getId()));
        }
    }
}
