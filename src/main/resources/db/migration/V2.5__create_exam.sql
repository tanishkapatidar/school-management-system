Create Table IF NOT EXISTS bodhi.exam(
    id character (255) NOT NULL,
    exam_name character (255),
    subject_id character (255),
    total_marks int,
    CONSTRAINT exam_primary_key PRIMARY KEY (id)
--     CONSTRAINT fk_exam_subject FOREIGN KEY (subject_id) REFERENCES subject(id)
    );
