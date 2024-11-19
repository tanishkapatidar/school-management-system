Alter Table if exists bodhi.teachers
Drop column contact_information cascade,
Add column contact character (255)