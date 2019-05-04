USE mydb;

INSERT INTO user
(User_ID,Password, Name, Sex, Born, Nick_Name, Major, Address, communicate, Name_Access, Sex_Access, Born_Access, Major_Access, Address_Access, Communicate_Access)
VALUES
(11711613,123456,'王天麒','M',20000927,'Wangberlin','CSE','湖畔五栋','13008881266',0,0,0,0,0,0);

INSERT INTO club
(Club_ID, Club_Name, Club_Type, Club_Intro, Club_Leader)
VALUES
(1,'Basketball Club','Sport','Play basketball',11711613);

SELECT * FROM user;

SELECT * FROM club;

DELETE FROM club;