Create Table IF NOT EXISTS bodhi.teachers (
    id character (255) NOT NULL,
    name character (255),
    address character (255),
    contact_information int,
    specialization character(255),
    gender character (255),
    email character (255),
    CONSTRAINT teachers_primary_key PRIMARY KEY (id)
    );