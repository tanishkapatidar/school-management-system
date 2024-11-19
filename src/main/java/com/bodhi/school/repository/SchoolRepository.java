package com.bodhi.school.repository;

import com.bodhi.school.model.School;
import org.jooq.*;
import org.jooq.Record;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import static org.jooq.impl.DSL.*;

@Component
public class SchoolRepository {
    private DSLContext dslContext;

    public School save(School school) {
        Table<Record> schoolTable = table(name("bodhi","school"));
        UUID id =  dslContext.insertInto(schoolTable)
                .set(field("name"), school.getName())
                .set(field("affiliation_number"), school.getAffiliation_number())
                .set(field("exam_board"),school.getExam_board())
                .set(field("address"),school.getAddress())
                .set(field("contact_information"),school.getContact_information())
                .set(field("email_id"),school.getEmail_id())
                .returning(field("*")).fetchOneInto(UUID.class);

        school.setId(String.valueOf(id));
        return school;
    }

//    public School update(School school) {
//        Table<Record> schoolTable = table(name("bodhi","school"));
//
//        Integer rowsUpdated = dslContext.update(schoolTable)
//                .set(field("name"), school.getName())
//                .set(field("affiliation_number"), school.getAffiliation_number())
//                .set(field("exam_board"),school.getExam_board())
//                .set(field("address"),school.getAddress())
//                .set(field("contact_information"),school.getContact_information())
//                .set(field("email_id"),school.getEmail_id())
//                .where(field("id")).equals(UUID.fromString(school.getId()))
//                .execute();
//
//    }
//
//    public School findById(UUID id) {
//        Table<Record> schoolTable = table(name("bodhi","school"));
//
//        Record data = dslContext.selectFrom()
//    }
}
