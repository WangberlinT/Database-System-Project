package bean;

import java.util.Date;

public class Student {
    private int UserID; //用户ID
    private char[] Password; //密码
    private String Name; //姓名
    private String Sex; //性别
    private Date BornDate; //出生日期
    private String NickName; //昵称
    private String Major; //专业
    private String Address; //地址
    private String Communicate; //联系方式

    public Student() {
    }

    //getter and setter
    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public char[] getPassword() {
        return Password;
    }

    public void setPassword(char[] password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public Date getBornDate() {
        return BornDate;
    }

    public void setBornDate(Date bornDate) {
        BornDate = bornDate;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String major) {
        Major = major;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCommunicate() {
        return Communicate;
    }

    public void setCommunicate(String communicate) {
        Communicate = communicate;
    }
}
