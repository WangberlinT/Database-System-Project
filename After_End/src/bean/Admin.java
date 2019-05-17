package bean;

public class Admin {
    private int Admin_Id; //用户id
    private String Password; //密码

    public Admin(int ID,String Password){
        this.Admin_Id = ID;
        this.Password = Password;
    }

    //getter and setter
    public int getAdmin_Id() {
        return Admin_Id;
    }

    public void setAdmin_Id(int admin_Id) {
        Admin_Id = admin_Id;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

}
