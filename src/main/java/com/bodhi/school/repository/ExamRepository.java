package com.bodhi.school.repository;

import com.bodhi.school.model.Exam;
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
public class ExamRepository {
    private final DSLContext dslContext;

    private Table<Record> getExamTable(){
        return table(name("bodhi","exam"));
    }

    public Exam save(Exam exam) {
        dslContext.insertInto(getExamTable())
                .set(field("id"),exam.getId())
                .set(field("exam_name"), exam.getExam_name())
                .set(field("subject_id"), exam.getSubject_id())
                .set(field("total_marks"),exam.getTotal_marks())
                .execute();
        return exam;
    }

    public Exam update(Exam exam) {
        return dslContext.update(getExamTable())
                .set(field("id"),exam.getId())
                .set(field("exam_name"), exam.getExam_name())
                .set(field("subject_id"), exam.getSubject_id())
                .set(field("total_marks"),exam.getTotal_marks())
                .where(field("id").eq(exam.getId()))
                .returning(field("*"))
                .fetchOneInto(Exam.class);
    }

    public Optional<Record> findById(String id) {
        return Optional.ofNullable(
                dslContext.selectFrom(getExamTable())
                        .where(field("id").eq(id))
                        .fetchOne()
        );
    }

    public Result<Record> findBySubjectId(String subjectId) {
        return dslContext.selectFrom(getExamTable())
                .where(field("subject_id").eq(subjectId))
                .fetch();
    }

    public int deleteById(String id) {
        return dslContext.deleteFrom(getExamTable())
                .where(field("id").eq(id))
                .execute();
    }
}

