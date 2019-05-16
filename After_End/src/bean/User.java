package bean;

import java.util.Date;
import java.util.List;

public class User {
    private int UserID; //用户ID
    private String Password; //密码
    private String Name; //姓名
    private String Sex; //性别
    private Date BornDate; //出生日期
    private String Major; //专业
    private String Address; //地址
    private int Communicate; //电话号码
    public final static String[] GENDER = {"男","女"};
    private List<Club> clubList; //加入的社团
    private List<Integer> authority;//权限
    public User() {
    }

    public User(int ID,String Password)
    {
        this.UserID = ID;
        this.Password = Password;
    }


    //getter and setter
    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
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

    public int getCommunicate() {
        return Communicate;
    }

    public void setCommunicate(int communicate) {
        Communicate = communicate;
    }

    public void update(){}//更新数据库信息
}
