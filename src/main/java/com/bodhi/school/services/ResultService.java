package com.bodhi.school.services;

import com.bodhi.school.model.Result;
import com.bodhi.school.repository.ResultRepository;
import org.jooq.Record;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ResultService {
    private final ResultRepository resultRepository;
    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public Result getById(String id) throws Exception {
        Record record = resultRepository.findById(id)
                .orElseThrow(() ->new Exception("Result with id %s not found".formatted(id)));
        return record.into(Result.class);
    }

    public Result create(Result school) throws Exception {
        try{
            school.setId(UUID.randomUUID().toString());
            return this.resultRepository.save(school);
        }catch (Exception e){
            throw  new Exception("Unable to save result with id %s ".formatted(school.getId()));
        }
    }
}
