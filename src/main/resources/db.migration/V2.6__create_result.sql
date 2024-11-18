Create Table IF NOT EXISTS ${flyway.defaultSchema}.result (
    id character (255) NOT NULL,
    student_id character (255),
    exam_id character(255),
    obtained_marks int,
    CONSTRAINT result_primary_key PRIMARY KEY (id),
    CONSTRAINT fk_result_exam FOREIGN KEY (exam_id) REFERENCES exam(id)
    );

