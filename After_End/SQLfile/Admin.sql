/*Admin SQL*/
/*add club*/
INSERT INTO mydb.club
VALUES (Club_ID,Club_Name,Club_Type,Club_Intro,Club_Leader);

/*update club*/
UPDATE mydb.club
set Club_Name = 'input'
WHERE Club_ID = 1;
