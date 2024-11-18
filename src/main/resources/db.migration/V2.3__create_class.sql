Create Table IF NOT EXISTS ${flyway.defaultSchema}.class (
    id character NOT NULL(255),
    grade character (255),
    section character (255),
    teacher_id character (255),
    CONSTRAINT class_primary_key PRIMARY KEY (id),
    CONSTRAINT fk_class_teacher FOREIGN KEY (teacher_id) REFERENCES teachers(id)
    );