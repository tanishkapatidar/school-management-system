package com.bodhi.school.repository;

import com.bodhi.school.model.Result;
import lombok.RequiredArgsConstructor;
import org.jooq.*;
import org.jooq.Record;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.jooq.impl.DSL.*;

@Component
@RequiredArgsConstructor
public class ResultRepository {
    private final DSLContext dslContext;

    private Table<Record> getResultTable(){
        return table(name("bodhi","result"));
    }

    public Result save(Result result) {
        dslContext.insertInto(getResultTable())
                .set(field("id"),result.getId())
                .set(field("student_id"), result.getStudent_id())
                .set(field("exam_id"),result.getExam_id())
                .set(field("obtained_marks"),result.getObtained_marks())
                .execute();
        return result;
    }

    public Result update(Result result) {
        return dslContext.update(getResultTable())
                .set(field("id"),result.getId())
                .set(field("student_id"), result.getStudent_id())
                .set(field("exam_id"),result.getExam_id())
                .set(field("obtained_marks"),result.getObtained_marks())
                .where(field("id").eq(result.getId()))
                .returning(field("*"))
                .fetchOneInto(Result.class);
    }

    public Optional<Record> findById(String id) {
        return Optional.ofNullable(
                dslContext.selectFrom(getResultTable())
                        .where(field("id").eq(id))
                        .fetchOne()
        );
    }

    public Record findByExamIdAndStudentId(String examId, String studentId) {
        return dslContext.selectFrom(getResultTable())
                        .where(field("exam_id").eq(examId).and(field("student_id").eq(studentId)))
                        .fetchOne();
    }

    public int deleteById(String id) {
        return dslContext.deleteFrom(getResultTable())
                .where(field("id").eq(id))
                .execute();
    }
}
