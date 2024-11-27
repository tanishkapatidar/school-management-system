package com.bodhi.school.services;

import com.bodhi.school.model.School;
import com.bodhi.school.repository.SchoolRepository;
import org.jooq.Record;
import org.keycloak.admin.client.Keycloak;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SchoolService {

    private final Keycloak keycloakAdminClient;


    private final SchoolRepository schoolRepository;
    public SchoolService(Keycloak keycloakAdminClient, SchoolRepository schoolRepository) {
        this.keycloakAdminClient = keycloakAdminClient;
        this.schoolRepository = schoolRepository;
    }


    public School getById(String id) throws Exception {
        Record record = schoolRepository.findById(id)
                        .orElseThrow(() ->new Exception("School with id %s not found".formatted(id)));
        return record.into(School.class);
    }

    public School create(School school) throws Exception {
        try{
            school.setId(UUID.randomUUID().toString());
            return this.schoolRepository.save(school);
        }catch (Exception e){
           throw  new Exception("Unable to save school with id %s ".formatted(school.getId()));
        }
    }
}
