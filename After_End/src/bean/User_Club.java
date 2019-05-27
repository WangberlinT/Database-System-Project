package bean;

public class User_Club extends User {
    private int Club_ID;
    private String Work_Name;
    private String Club_Name;

    public User_Club() {
    }

    public String toString() {
        return getUser_ID() + "  " + getName() + "  " + Club_ID + " :" + Club_Name + " " + Work_Name;
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