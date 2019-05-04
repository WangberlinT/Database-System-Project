USE mydb;

INSERT INTO user
(User_ID,Password, Name, Sex, Born, Nick_Name, Major, Address, communicate, Name_Access, Sex_Access, Born_Access, Major_Access, Address_Access, Communicate_Access)
VALUES
(11711613,123456,'王天麒','M',20000927,'Wangberlin','CSE','湖畔五栋','13008881266',0,0,0,0,0,0);

INSERT INTO user
(User_ID,Password, Name, Sex, Born, Nick_Name, Major, Address, communicate, Name_Access, Sex_Access, Born_Access, Major_Access, Address_Access, Communicate_Access)
VALUES
(11712208,654321,'姜朋坤','M',19990920,'RubbishFISH','CSE','湖畔一栋','1111111111',0,0,0,0,0,0);

INSERT INTO user
(User_ID,Password, Name, Sex, Born, Nick_Name, Major, Address, communicate, Name_Access, Sex_Access, Born_Access, Major_Access, Address_Access, Communicate_Access)
VALUES
(11111111,000000,'蔡徐坤','F',20200101,'鸡你太美','RAP','四海为家','律师函',0,0,0,0,0,0);


INSERT INTO club
(Club_ID, Club_Name, Club_Type, Club_Intro, Club_Leader)
VALUES
(1,'Basketball Club','Sport','Play basketball',11711613);

INSERT INTO club
(Club_ID, Club_Name, Club_Type, Club_Intro, Club_Leader)
VALUES
(2,'Sleeping Club','Sport','Sleep everyday',11712208);

insert into activity
(Activity_ID, Activity_Name, Start_Time, End_Time, Approver_ID, Response_ID, `Range`, State) VALUES
(1,'唱跳rap',20190101,20190909,11711613,20475048,1,1);

insert into activity
(Activity_ID, Activity_Name, Start_Time, End_Time, Approver_ID, Response_ID, `Range`, State) VALUES
(2,'篮球招新',20190101,20190909,11711613,11711613,1,1);

insert into activity_user (Activity_ID, User_ID)
values (1,20475048);
insert into activity_user (Activity_ID, User_ID)
values (1,11711613);
insert into activity_user (Activity_ID, User_ID)
values (2,11711613);


insert into apply_to_studert
(Apply_ID, Apply_Type, Apply_From, Apply_To) VALUES
(1,'入团申请',20475048,11711613);

SELECT * FROM user;

SELECT * FROM club;
select * from activity;
select * from apply_to_studert;