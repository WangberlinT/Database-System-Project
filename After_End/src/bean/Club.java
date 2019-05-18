package bean;

public class Club {
    private int Club_ID; //社团ID
    private String Club_Name; //社团名
    private String Club_Type; //社团类型
    private String Club_Intro; //社团简介
    private int Club_Leader; //头头的ID

    public Club() {
    }

    public Club(String Club_Name, String Club_Type, String Club_Intro,int Club_Leader ){
        this.Club_Name=Club_Name;
        this.Club_Type=Club_Type;
        this.Club_Intro=Club_Intro;
        this.Club_Leader=Club_Leader;
    }

    //getter and setter
    public int getClub_ID() {
        return Club_ID;
    }

    public void setClub_ID(int club_ID) {
        Club_ID = club_ID;
    }

    public String getClub_Name() {
        return Club_Name;
    }

    public void setClub_Name(String club_Name) {
        Club_Name = club_Name;
    }

    public String getClub_Type() {
        return Club_Type;
    }

    public void setClub_Type(String club_Type) {
        Club_Type = club_Type;
    }

    public String getClub_Intro() {
        return Club_Intro;
    }

    public void setClub_Intro(String club_Intro) {
        Club_Intro = club_Intro;
    }

    public int getClub_Leader() {
        return Club_Leader;
    }

    public void setClub_Leader(int club_Leader) {
        Club_Leader = club_Leader;
    }
}
