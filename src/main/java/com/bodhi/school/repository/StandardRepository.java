package com.bodhi.school.repository;

import com.bodhi.school.model.Standard;
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
public class StandardRepository {
    private final DSLContext dslContext;

    private Table<Record> getStandardTable(){
        return table(name("bodhi","class"));
    }

    public Standard save(Standard standard) {
        dslContext.insertInto(getStandardTable())
                .set(field("id"),standard.getId())
                .set(field("grade"), standard.getGrade())
                .set(field("section"), standard.getGrade())
                .set(field("teacher_id"),standard.getTeacher_id())
                .execute();
        return standard;
    }

    public Standard update(Standard standard) {
        return dslContext.update(getStandardTable())
                .set(field("id"),standard.getId())
                .set(field("grade"), standard.getGrade())
                .set(field("section"), standard.getGrade())
                .set(field("teacher_id"),standard.getTeacher_id())
                .where(field("id").eq(standard.getId()))
                .returning(field("*"))
                .fetchOneInto(Standard.class);
    }

    public Optional<Record> findById(String id) {
        return Optional.ofNullable(
                dslContext.selectFrom(getStandardTable())
                        .where(field("id").eq(id))
                        .fetchOne()
        );
    }

    public int deleteById(String id) {
        return dslContext.deleteFrom(getStandardTable())
                .where(field("id").eq(id))
                .execute();
    }

    public Optional<Record> findByGradeAndSection(String grade, String section) {
        return Optional.ofNullable(dslContext.selectFrom(getStandardTable())
                .where(field("grade").eq(grade))
                .and(field("section").eq(section))
                .fetchOne()
        );
    }
}

