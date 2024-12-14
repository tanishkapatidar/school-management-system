package com.bodhi.school.services;

import com.bodhi.school.model.Exam;
import com.bodhi.school.model.Standard;
import com.bodhi.school.model.Student;
import com.bodhi.school.model.Subject;
import com.bodhi.school.repository.StudentRepository;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Record;
import org.jooq.Result;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Slf4j
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final Keycloak keycloakAdminClient;
    private final StandardService standardService;
    private final SubjectService subjectService;
    private final ExamService examService;
    private final ResultService resultService;


    public StudentService(StudentRepository studentRepository, Keycloak keycloakAdminClient, StandardService standardService, SubjectService subjectService, ExamService examService, ResultService resultService) {
        this.studentRepository = studentRepository;
        this.keycloakAdminClient = keycloakAdminClient;
        this.standardService = standardService;
        this.subjectService = subjectService;
        this.examService = examService;
        this.resultService = resultService;
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

    public List<Student> getAllStudentsByGradeAndSection(String grade, String section) throws Exception {
        Standard std =standardService.findByGradeAndSection(grade,section);
        Result<Record> result = studentRepository.findAll(std.getId());
        List<Student> students = new ArrayList<>();
        result.forEach(record -> {
            Student student = record.into(Student.class);
            students.add(student);
        });
        return students;
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

    public Map<String, Object> getResultById(String id) throws Exception {
        Student student = getById(id);
        String stdId = student.getId();
        List<Subject> subjects = subjectService.getAllByStdId(stdId);
        HashMap<String, Object> subjectMap = new HashMap<>();
        subjects.forEach(subject -> {
            String subjectId = subject.getId();
            List<Exam> subjectExams = examService.getAllBySubjectId(subjectId);

            HashMap<String, Object> examMap = new HashMap<>();
            subjectExams.forEach(exam -> {
                String examId = exam.getId();
                com.bodhi.school.model.Result examResult = resultService.getByExamIdAndStudentId(examId, student.getId());
                HashMap<String, Object> examResultMap = new HashMap<>();
                examResultMap.put("Total Marks", exam.getTotal_marks());
                examResultMap.put("Obtained Marks", examResult.getObtained_marks());

                examMap.put(exam.getExam_name(), examResultMap);
            });
            subjectMap.put(subject.getSub_name(), examMap);
        });

        return subjectMap;
    }
}

// find the result:
// 1- find student by id
// 2- get std_id using student
// 3- find subject_id using std_id
// 4- find exam_id using subject_id
// 5 - find result using exam_id and student_id

//{}