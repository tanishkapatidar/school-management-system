package com.bodhi.school.services;

import com.bodhi.school.model.Teachers;
import com.bodhi.school.repository.TeachersRepository;
import org.jooq.Record;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TeachersService {
    private final TeachersRepository teachersRepository;
    public TeachersService(TeachersRepository teachersRepository) {
        this.teachersRepository = teachersRepository;
    }

    public Teachers getById(String id) throws Exception {
        Record record = teachersRepository.findById(id)
                .orElseThrow(() ->new Exception("Teacher with id %s not found".formatted(id)));
        return record.into(Teachers.class);
    }

    public Teachers create(Teachers teacher) throws Exception {
        try{
            teacher.setId(UUID.randomUUID().toString());
            return this.teachersRepository.save(teacher);
        }catch (Exception e){
            throw  new Exception("Unable to save teacher with id %s ".formatted(teacher.getId()));
        }
    }
}

