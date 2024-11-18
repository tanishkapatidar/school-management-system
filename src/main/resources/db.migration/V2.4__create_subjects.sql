Create Table IF NOT EXISTS ${flyway.defaultSchema}.subject (
    id character NOT NULL(255),
    sub_name character (255),
    teacher_name character (255),
    class_id character (255),
    CONSTRAINT subject_primary_key PRIMARY KEY (id),
    CONSTRAINT fk_subject_class FOREIGN KEY (class_id) REFERENCES class(id)
);