package bean;

public class Admin {
    private int Admin_ID; //用户id
    private String Password; //密码

    public Admin(){}

    public Admin(int ID,String Password){
        this.Admin_ID = ID;
        this.Password = Password;
    }

    @Override
    public String toString()
    {
        return this.Admin_ID + "  " + this.Password;
    }

    //getter and setter
    public int getAdmin_Id() {
        return Admin_ID;
    }

    public void setAdmin_Id(int admin_Id) {
        Admin_ID = admin_Id;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

}
