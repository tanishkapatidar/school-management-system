package com.bodhi.school.services;

import com.bodhi.school.model.Student;
import com.bodhi.school.repository.StudentRepository;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Record;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final Keycloak keycloakAdminClient;

    public StudentService(StudentRepository studentRepository, Keycloak keycloakAdminClient) {
        this.studentRepository = studentRepository;
        this.keycloakAdminClient = keycloakAdminClient;
    }

    public void createKeycloakUser(Student student) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(student.getId());
        userRepresentation.setFirstName(student.getName());
        userRepresentation.setEmail(student.getEmail_id());
        userRepresentation.setEnabled(true);
        Response response= keycloakAdminClient.realm("master").users()
                .create(userRepresentation);

        if(response.getStatus() != Response.Status.CREATED.getStatusCode()){
            log.error("Keycloak user creation failed");
        }

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
        //student.setId(UUID.randomUUID().toString());
        createKeycloakUser(student);
        try{

            return this.studentRepository.save(student);
        }catch (Exception e){
            throw  new Exception("Unable to save student with id %s ".formatted(student.getId()));
        }
    }
}

