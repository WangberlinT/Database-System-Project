package bean;

import util.StringAlign;

import java.util.Date;

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

    public User() {
    }

    public User(int ID, String Password, String Name, String Sex) {
        this.User_ID = ID;
        this.Password = Password;
        this.Name = Name;
        this.Sex = Sex;
    }

    @Override
    public String toString() {
        StringAlign formatter = new StringAlign(12, StringAlign.JUST_LEFT);
        String born = (Born_access && Born != null) ? Born.toString() : "<隐藏>";
        String addr = (Address_Access && Address != null) ? Address : "<隐藏>";
        String pho = (Phone_Access && Phone_Number != null) ? Phone_Number : "<隐藏>";
        String result = formatter.format(Integer.toString(User_ID));
        formatter.setMaxChars(20);
        result += formatter.format(Name);
        formatter.setMaxChars(5);
        result += formatter.format(Sex);
        formatter.setMaxChars(20);
        result += formatter.format(Major)+formatter.format(born);
        formatter.setMaxChars(30);
        result += formatter.format(addr)+formatter.format(pho);
        return result;
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
