Alter Table if exists bodhi.students
Drop column contact_information cascade,
Add column contact character (255)