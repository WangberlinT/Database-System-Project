package bean;

import java.util.Date;
import java.util.List;

public class User {
    private int User_ID; //用户ID
    private String Password; //密码
    private String Name; //姓名
    private String Sex = null; //性别
    private Date Born = null; //出生日期
    private String Major = null; //专业
    private String Address = null; //地址
    private String Phone_Number = null; //电话号码
    private boolean Born_access = false;
    private boolean Address_Access = false;
    private boolean Phone_Access = false;
    public final static String[] GENDER = {"男", "女"};
    private List<Club> clubList; //加入的社团
    private List<Integer> authority;//权限

    public User() {
    }

    public User(int ID, String Password,String Name,String Sex) {
        this.User_ID = ID;
        this.Password = Password;
        this.Name = Name;
        this.Sex = Sex;
    }


    //getter and setter
    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
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

    public Date getBorn() {
        return Born;
    }

    public void setBorn(Date born) {
        Born = born;
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

    public boolean isBorn_access() {
        return Born_access;
    }

    public void setBorn_access(boolean born_access) {
        Born_access = born_access;
    }

    public boolean isAddress_Access() {
        return Address_Access;
    }

    public void setAddress_Access(boolean address_Access) {
        Address_Access = address_Access;
    }

    public boolean isPhone_Access() {
        return Phone_Access;
    }

    public void setPhone_Access(boolean phone_Access) {
        Phone_Access = phone_Access;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone_Number() {
        return Phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        Phone_Number = phone_Number;
    }

}
