package bean;

public class User_Club {
    private int User_ID;
    private int Club_ID;
    private String Work_Name;
    private String Club_Name;

    public User_Club() {
    }

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }

    public int getClub_ID() {
        return Club_ID;
    }

    public void setClub_ID(int club_ID) {
        Club_ID = club_ID;
    }

    public String getWork_Name() {
        return Work_Name;
    }

    public void Work_Name(String work_Name) {
        Work_Name = work_Name;
    }
    
    public String toString() {
    	return "在"+Club_ID+":"+Club_Name+"为"+Work_Name;
    }

    public void setWork_Name(String work_Name) {
        Work_Name = work_Name;
    }

    public String getClub_Name() {
        return Club_Name;
    }

    public void setClub_Name(String club_Name) {
        Club_Name = club_Name;
    }
}