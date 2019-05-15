package bean;

public class Admin {
    private int Id; //用户id
    private String Password; //密码

    public Admin(int ID,String Password){
        this.Id = ID;
        this.Password = Password;
    }

    //getter and setter
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

}
