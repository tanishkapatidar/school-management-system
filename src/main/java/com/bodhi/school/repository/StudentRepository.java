package com.bodhi.school.repository;

import com.bodhi.school.model.Student;
import lombok.RequiredArgsConstructor;
import org.jooq.*;
import org.jooq.Record;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.jooq.impl.DSL.*;

@Component
@RequiredArgsConstructor
public class StudentRepository {
    private final DSLContext dslContext;

    private Table<Record> getStudentTable(){
        return table(name("bodhi","students"));
    }

    public Student save(Student student) {
        dslContext.insertInto(getStudentTable())
                .set(field("id"),student.getId())
                .set(field("name"), student.getName())
                .set(field("fathers_name"), student.getFathers_name())
                .set(field("mothers_name"),student.getMothers_name())
                .set(field("address"),student.getAddress())
                .set(field("contact"),student.getContact())
                .set(field("date_of_birth"),student.getDate_of_birth())
                .set(field("gender"),student.getGender())
                .set(field("email_id"),student.getEmail_id())
                .set(field("std_id"),student.getStd_id())

                .execute();
        return student;
    }

    public Student update(Student student) {
        return dslContext.update(getStudentTable())
                .set(field("id"),student.getId())
                .set(field("name"), student.getName())
                .set(field("fathers_name"), student.getFathers_name())
                .set(field("mothers_name"),student.getMothers_name())
                .set(field("address"),student.getAddress())
                .set(field("contact"),student.getContact())
                .set(field("date_of_birth"),student.getDate_of_birth())
                .set(field("gender"),student.getGender())
                .set(field("email_id"),student.getEmail_id())
                .set(field("std_id"),student.getStd_id())
                .where(field("id").eq(student.getId()))
                .returning(field("*"))
                .fetchOneInto(Student.class);
    }

    public Optional<Record> findById(String id) {
        return Optional.ofNullable(
                dslContext.selectFrom(getStudentTable())
                        .where(field("id").eq(id))
                        .fetchOne()
        );
    }

    public int deleteById(String id) {
        return dslContext.deleteFrom(getStudentTable())
                .where(field("id").eq(id))
                .execute();
    }

    public Result<Record> findAll(String stdId){
        return dslContext.selectFrom(getStudentTable())
                .where(field("std_id").eq(stdId))
                .fetch();
    }
}
