package com.bodhi.school.services;

import com.bodhi.school.model.Standard;
import com.bodhi.school.repository.StandardRepository;
import org.jooq.Record;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class StandardService {
    private final StandardRepository standardRepository;
    public StandardService(StandardRepository standardRepository) {
        this.standardRepository = standardRepository;
    }

    public Standard getById(String id) throws Exception {
        Record record = standardRepository.findById(id)
                .orElseThrow(() ->new Exception("Standard with id %s not found".formatted(id)));
        return record.into(Standard.class);
    }

    public Standard create(Standard standard) throws Exception {
        try{
            standard.setId(UUID.randomUUID().toString());
            return this.standardRepository.save(standard);
        }catch (Exception e){
            throw  new Exception("Unable to save standard with id %s ".formatted(standard.getId()));
        }
    }
}

