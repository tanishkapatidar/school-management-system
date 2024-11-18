Create Table IF NOT EXISTS bodhi.class (
    id character (255)  NOT NULL,
    grade character (255),
    section character (255),
    teacher_id character (255),
    CONSTRAINT class_primary_key PRIMARY KEY (id)
    );