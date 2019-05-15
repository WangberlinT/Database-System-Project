* **用户表**

  1. User_ID(User_ID,INT)主键
  2. 密码(Password,VARCHAR
  3. 姓名(Name,VARCHAR
  4. 性别(Sex,VARCHAR)
  5. 出生日期(Born_Date,DATE)
  6. 昵称(Nick_Name,VARCHAR)
  7. 专业(Major,VARCHAR
  8. 地址(Address,VARCHAR
  9. 联系方式(Communicate VARCHAR)

* **社团表**

  1. 社团ID(Club_ID,INT)主键(首位为0)
  2. 名称(Club_Name,VARCHAR(45))
  3. 类型(Club_Type,VARCHAR(45))
  4. 简介(Club_Intro,VARCHAR(200))
  5. 负责人(Club_Leader,INT)外键连接User_ID

* **社团指导老师连接**

  1. 指导老师ID(Tutor_ID)主键，外键连接UserID
  2. 社团ID(Club_ID)主键，外键连接社团ID

  一个社团有多个指导老师，一个老师指导多个社团

* **子部门、子社团**

  1. ID(Apartment_ID,INT)主键
  2. 部门介绍(Intro,VARCHAR)
  3. 父社团ID(Club_ID,INT)外键连接社团ID

* **人员-社团部门连接**

  1. 用户ID(User_ID,INT)主键外键连接User_ID
  2. 社团ID(Club_ID,INT)主键外键连接Club_ID
  3. 权限级别(Authority,INT)
  4. 职位名称(Work_Name,VARCHAR)
  5. 部门ID(Department_ID,INT)外键连接部门ID

  一个人可以加入多个社团，一个社团有多个人

* **活动**

  1. 活动ID(Activity_ID,INT)-primry key
  2. 活动名称(Activity_Name,VARCHAR(45))
  3. 开始时间(Start_Time,DATETIME)
  4. 结束时间(End_Time,DATETIME)
  5. 审批人ID(Approver_ID,INT)外键连接Tutor_ID
  6. 负责人ID(Response_ID,INT)外键连接User_ID
  7. 活动可见性(公共：1)

  活动表用于记录学生发起的活动信息

  活动可见性为说明此活动是社团内可见还是全体可见(上层实现)

* **活动-社团连接**

  1. 社团ID(Club_ID,INT)外键连接社团ID
  2. 活动ID(Activity_ID,INT)外键连接活动ID

  一个社团可以发起多个活动，多个社团可以联合发起一个活动

* **活动-人员连接**

  1. 活动ID(Activity_ID,INT)外键连接活动ID
  2. 用户ID(User_ID,INT)外键连接用户ID

  一个用户可以参加多个活动，一个活动有多个用户参加

* **评价(活动和人的评价模板)**

  1. 评价ID(Evaluation_ID,INT)
  2. 内容(Content,VARCHAR(100))
  3. 时间(Time,DATETIME)
  4. 评价等级(Level,INT)//需要限制小于100大于0

* **绩效考核连接**(人员评价)

  1. 被评价人(User_ID,INT)外键连接User_ID
  2. 评价人(User_ID,INT)外键连接User_ID

* **活动评价连接**(活动评价)

  1. 被评价活动(Activity_ID,INT)主键，外键连接Activity_ID
  2. 评价人(User_ID,INT)主键，外键连接用户ID

* **资产**

  1. 资产ID(Item_ID,INT)主键
  2. 资产名称(Item_Name,VARCHAR)
  3. 资产金额(Item_Value,INT)

* **资源借用**

  1. 资产ID(Item_ID,INT)主键，外键连接资产
  2. 借用人(User_ID,INT)主键，外键连接用户ID
  3. 借用开始时间(Start_Time,DATETime)
  4. 借用结束时间(End_Time,DATETIME)
  5. 归还时间(Return_Time,DATETIME)

* **资源归属人**

  1. 资产ID(Item_ID,INT)主键，外键连接Item_ID
  2. 归属社团ID(Club_ID,INT)主键，外键连接Club_ID

* **申请**

  1. 申请ID(Apply_ID,INT)主键
  2. 申请类型(Apply_Type,VARCHAR)
  3. 申请人(Apply_From,INT)
  4. 受理人(Apply_To,INT)

* **公告**

  1. 公告ID(Announcement_ID,INT)主键
  2. 内容(Countent,VARCHAR)
  3. 发布时间(Time,DATETIME)
  4. 标题(Title,VARCHAR)
  5. 社团ID(Club_ID,INT)外键连接Club_ID
  6. 发布者ID(User_ID,INT)外键连接User_ID

* **管理员表**

  1. ID
  2. 用户名
  3. 密码

  

  

