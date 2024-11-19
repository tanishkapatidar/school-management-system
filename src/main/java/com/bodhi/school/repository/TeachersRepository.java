package com.bodhi.school.repository;

import com.bodhi.school.model.Teachers;
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
public class TeachersRepository {
    private final DSLContext dslContext;

    private Table<Record> getTeachersTable(){
        return table(name("bodhi","teachers"));
    }

    public Teachers save(Teachers teacher) {
        dslContext.insertInto(getTeachersTable())
                .set(field("id"),teacher.getId())
                .set(field("name"), teacher.getName())
                .set(field("specialization"), teacher.getSpecialization())
                .set(field("gender"),teacher.getGender())
                .set(field("address"),teacher.getAddress())
                .set(field("contact"),teacher.getContact())
                .set(field("email"),teacher.getEmail())
                .execute();
        return teacher;
    }

    public Teachers update(Teachers teacher) {
        return dslContext.update(getTeachersTable())
                .set(field("name"), teacher.getName())
                .set(field("specialization"), teacher.getSpecialization())
                .set(field("gender"),teacher.getGender())
                .set(field("address"),teacher.getAddress())
                .set(field("contact"),teacher.getContact())
                .set(field("email"),teacher.getEmail())
                .where(field("id").eq(teacher.getId()))
                .returning(field("*"))
                .fetchOneInto(Teachers.class);
    }

    public Optional<Record> findById(String id) {
        return Optional.ofNullable(
                dslContext.selectFrom(getTeachersTable())
                        .where(field("id").eq(id))
                        .fetchOne()
        );
    }

    public int deleteById(String id) {
        return dslContext.deleteFrom(getTeachersTable())
                .where(field("id").eq(id))
                .execute();
    }
}
