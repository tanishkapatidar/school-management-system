package com.bodhi.school.repository;

import com.bodhi.school.model.School;
import lombok.RequiredArgsConstructor;
import org.jooq.*;
import org.jooq.Record;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.jooq.impl.DSL.*;

@Component
@RequiredArgsConstructor
public class SchoolRepository {
    private final DSLContext dslContext;

    private Table<Record> getSchoolTable(){
        return table(name("bodhi","schools"));
    }

    public School save(School school) {
        dslContext.insertInto(getSchoolTable())
                .set(field("id"),school.getId())
                .set(field("name"), school.getName())
                .set(field("affiliation_number"), school.getAffiliation_number())
                .set(field("exam_board"),school.getExam_board())
                .set(field("address"),school.getAddress())
                .set(field("contact"),school.getContact())
                .set(field("email_id"),school.getEmail_id())
                .execute();
        return school;
    }

    public School update(School school) {
        return dslContext.update(getSchoolTable())
                .set(field("name"), school.getName())
                .set(field("affiliation_number"), school.getAffiliation_number())
                .set(field("exam_board"),school.getExam_board())
                .set(field("address"),school.getAddress())
                .set(field("contact"),school.getContact())
                .set(field("email_id"),school.getEmail_id())
                .where(field("id").eq(school.getId()))
                .returning(field("*"))
                .fetchOneInto(School.class);
    }

    public Optional<Record> findById(String id) {
        return Optional.ofNullable(
                dslContext.selectFrom(getSchoolTable())
                .where(field("id").eq(id))
                .fetchOne()
        );
    }

    public int deleteById(String id) {
        return dslContext.deleteFrom(getSchoolTable())
                .where(field("id").eq(id))
                .execute();
    }
}
