Create Table IF NOT EXISTS ${flyway.defaultSchema}.exam(
    id character (255) NOT NULL,
    exam_name character (255),
    subject_id character (255),
    total marks int,
    CONSTRAINT exam_primary_key PRIMARY KEY (id),
    CONSTRAINT fk_exam_subject FOREIGN KEY (subject_id) REFERENCES subject(id)
    );
