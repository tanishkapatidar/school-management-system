Create Table IF NOT EXISTS bodhi.students (
    id character (255) NOT NULL,
    name character (255),
    fathers_name character(255),
    mothers_name character(255),
    address character (255),
    contact_information int,
    date_of_birth date,
    gender character (255),
    email_id character (255),
    std_id character (255),
    CONSTRAINT students_primary_key PRIMARY KEY (id)
);

