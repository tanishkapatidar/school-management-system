Alter Table if exists bodhi.schools
Drop column contact_information cascade,
Add column contact character (255)