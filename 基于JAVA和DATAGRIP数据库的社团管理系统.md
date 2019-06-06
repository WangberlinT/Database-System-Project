# 基于JAVA和DATAGRIP数据库的社团管理系统

Group506   成员：姜朋坤，贺贵岩，许姜涵，王天麒，李艺聪

## 1 概述

通过南科大的调查得知，学生社团信息管理主要是依据纸质和手工作业处理，人工的对大量成员的基本资料进行档案式管理，此种处理方式数据量大，管理模式和方法滞后，存放时间不能长久和数据更新速度慢。

考虑现存的情况，建立一个系统化的学生社团管理系统是十分必要的。比如由原来的档案式保存会员信息变为将信息存入数据库中进行系统管理；分散在群，空间和公众号的公告和活动变为直接在系统内发布社团活动的时间和地点等；文件式申请社团的创建变为规范格式的网上申请，提交表格，再由系统管理员审核、批准；此系统还可以方便会员对社团动态进行查看、为社团打分、借用物品等。

## 2 系统需求分析和定义

 学生社团管理信息系统是一个操作简单、使用方便的系统。它的建立既是为了更加高效、规范地实现对社团动态进行管理，又是为了方便用户及时查看社团信息，保证信息的时效性和高效性。

**此学生社团管理信息系统应达到以下目标：**

- 在登录时，可根据权限来选择不同用户可以对系统调用不同的功能
- 迅速发布社团动态，对社团活动申请进行规范化和程序化管理
- 能够大量存储社团会员信息，方便会员查看社团信息、留言、进行评分等操作
- 社团管理员可以通过后台登录，对社团信息、活动和注册的用户进行查看和管理，同时对成立新社团的申请进行审核
- 通过查询、添加、修改等操作，对社团信息、用户资料等模块进行管理
- 系统管理员可以修改个人密码，可以对社团的各项活动进行管理和监督
- 搜索不同的社团和所拥有物品，以及不同社员的信息

系统采用 Datagrip 数据库，开发语言为 Java，数据库的存储容量足够大，而且比较稳定，能够较长时间保存数据。

**数据输入事务**

o   添加社团

o   添加社团评价

o   添加成员评价

o   添加社团活动

o   添加社团用户

o   添加系统管理者

o   添加游客用户

o   添加物品

o   添加物品借用

o   添加物品归属

o   添加申请

o   添加通知

o   添加社长

o   添加社团活动成员​    

**数据更新删除**

o   更新社团

o   更新/删除社团评价

o   更新/删除成员评价

o   更新/删除社团活动

o   更新/删除社团用户

o   更新/删除系统管理者

o   更新/删除游客用户

o   更新/删除物品

o   更新/删除物品借用

o   更新/删除物品归属

o   更新/删除申请

o   更新/删除通知

o   更新/删除社长

o   更新/删除社团活动成员

**数据输出事务**

o   查询所有社团活动

o   查询某个社团的活动

o   查询某活动对应的所有参与社团

o   查看社团评价

o   查看所有社团基本信息

o   查看某社团的用户列表

o   查询某社团的公告

o   查询某学生所参加的社团

o   查询某社团拥有物品

o   查看物品借用记录

## 3.数据需求分析和定义

一般来说，在拥有了需求之后，只需要根据需求和实际情况来建立数据，关系即可。在一个大学生的社团管理系统中，首先是用户和社团，然后是社团所拥有的物品，举办的活动，社团里的成员，社团的知道老师，然后再加上通知，公告，评价，申请，建立完整的数据和表格。我们也需要比较谨慎的考虑他们之间的关系，每一个社团里的成员首先得是该大学的学生，每一个活动必须有对应的举办社团，每一个评价需要评价人和评价对象，每一个物品都要有其归属，每一次物品借用需要明确借用人和借用时间。同时我们也需要规范的建立表格，避免冗余信息，满足3个范式。

**用户表**(User)

1. User_ID(User_ID,INT)主键

2. 密码(Password,VARCHAR)

3. 姓名(Name,VARCHAR)

4. 性别(Sex,VARCHAR)

5. 出生日期(Born,DATE)

6. 专业(Major,VARCHAR)

7. 地址(Address,VARCHAR)

8. 联系方式(Phone_number,VARCHAR)

9. 出生日期权限(Born_Access,TINYINT)

10. 地址权限(Adress_Access,TINYINT)

11 电话号码权限(Phone_number,TINYINT)

 **社团表**(Club)

1. 社团ID(Club_ID,INT)主键(首位为0)

2. 名称(Club_Name,VARCHAR)

3. 类型(Club_Type,VARCHAR)

4. 简介(Club_Intro,VARCHAR)

5. 负责人(Club_Leader,INT)外键连接User_ID

6. 社团状态(Club_State,TINYINT)

**社团指导老师连接**(Tutor_Club)

1. 指导老师ID(Tutor_ID)主键，外键连接UserID

2. 社团ID(Club_ID)主键，外键连接社团ID

**活动**(Activity)

1. 活动ID(Activity_ID,INT)主键
2. 活动名称(Activity_Name,VARCHAR)
3. 活动内容(Activity_contain,VARCHAR)
4. 开始时间(Start_Time,DATETIME)
5. 结束时间(End_Time,DATETIME)
6. 负责人ID(Response_ID,INT)外键连接User_ID
7. 查看权限(Range,tinyint)
8. 状态(State,tinyint)

 **活动社团连接**(Activity_Club)

1. 社团ID(Club_ID,INT)外键连接社团ID

2. 活动ID(Activity_ID,INT)外键连接活动ID

**活动人员连接**(Activity_User)

1. 活动ID(Activity_ID,INT)外键连接活动ID

2. 用户ID(User_ID,INT)外键连接用户ID

**评价**(Evaluation)

1. 评价ID(Evaluation_ID,INT)

2. 内容(Content,VARCHAR)

3. 时间(Time,DATETIME)

4. 评价等级(Level,INT)

**人员评价**(Evaluation_Member)

1. 被评价人(User_ID,INT)外键连接User_ID

2. 评价人(User_ID,INT)外键连接User_ID

**活动评价**(Evaluation_Activity)

1. 被评价活动(Activity_ID,INT)主键，外键连接Activity_ID

2. 评价人(User_ID,INT)主键，外键连接用户ID

**物品**(Item)

1. 资产ID(Item_ID,INT)主键

2. 资产名称(Item_Name,VARCHAR)

3. 资产金额(Item_Value,INT)

4. 借用状态(Loan_State,TINYINT)

**物品借用**(Item_Loan)

1. 资产ID(Item_ID,INT)主键，外键连接资产

2. 借用人(User_ID,INT)主键，外键连接用户ID

3. 借用开始时间(Start_Time,DATETIME)

4. 借用结束时间(End_Time,DATETIME)

5. 归还时间(Return_Time,DATETIME)

**物品归属**(Item_Belong)

1. 资产ID(Item_ID,INT)主键，外键连接Item_ID

2. 归属社团ID(Club_ID,INT)主键，外键连接Club_ID

 **申请**(Apply_to_Student)

1. 申请ID(Apply_ID,INT)主键

2. 申请类型(Apply_Type,VARCHAR)

3. 申请人(Apply_From,INT)

4. 受理人(Apply_To,INT)

 **公告**(Announcement)

1. 公告ID(Announcement_ID,INT)主键

2. 内容(Countent,VARCHAR)

3. 发布时间(Time,DATETIME)

4. 标题(Title,VARCHAR)

5. 社团ID(Club_ID,INT)外键连接Club_ID

6. 发布者ID(User_ID,INT)外键连接User_ID

 **管理员**(Admin)

1. ID(Admin_ID,INT)主键

2. 密码(Password,VARCHAR)

 **通知**(Announcement)

1. 公告ID(Announcement_ID,INT)主键

2. 用户ID(User_ID,INT)外键

3. 状态(State,TINYINT)
4. 读取时间(Read_Time,DATETIME)

## 4.数据模型设计

![er](C:\Users\ASUS\Desktop\数据库\pro\er.png)

## 5.分工

 11712212 许姜涵，编写报告，绘制E-R图，负责编码中社团部分等，调试代码等工作

 11712208 姜朋坤，负责编码与调试社团部分，道具与公告相关功能的实现


 

 

