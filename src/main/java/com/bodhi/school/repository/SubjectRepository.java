package com.bodhi.school.repository;

import com.bodhi.school.model.Subject;
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
public class SubjectRepository {
    private final DSLContext dslContext;

    private Table<Record> getSubjectTable(){
        return table(name("bodhi","subject"));
    }

    public Subject save(Subject subject) {
        dslContext.insertInto(getSubjectTable())
                .set(field("id"),subject.getId())
                .set(field("sub_name"), subject.getSub_name())
                .set(field("teacher_name"), subject.getTeacher_name())
                .set(field("class_id"),subject.getClass_id())
                .execute();
        return subject;
    }

    public Subject update(Subject subject) {
        return dslContext.update(getSubjectTable())
                .set(field("id"),subject.getId())
                .set(field("sub_name"), subject.getSub_name())
                .set(field("teacher_name"), subject.getTeacher_name())
                .set(field("class_id"),subject.getClass_id())
                .where(field("id").eq(subject.getId()))
                .returning(field("*"))
                .fetchOneInto(Subject.class);
    }

    public Optional<Record> findById(String id) {
        return Optional.ofNullable(
                dslContext.selectFrom(getSubjectTable())
                        .where(field("id").eq(id))
                        .fetchOne()
        );
    }

    public int deleteById(String id) {
        return dslContext.deleteFrom(getSubjectTable())
                .where(field("id").eq(id))
                .execute();
    }
}

