Create Table IF NOT EXISTS bodhi.schools (
    id character (255) NOT NULL,
    name character (255),
    affiliation_number character(255),
    exam_board character(255),
    address character (255),
    contact_information int,
    email_id character (255),
    CONSTRAINT schools_primary_key PRIMARY KEY (id)
);

