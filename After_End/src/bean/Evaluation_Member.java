package bean;

public class Evaluation_Member extends Evaluation {
    private int Member_ID;
    private int Club_ID;

    private String Name; //用户名
    private String Club_Name; //社团名

    public Evaluation_Member() {
    }

    @Override
    public String toString() {
        return getEvaluation_ID() + " | " + Member_ID + " | " + Name + " | " + Club_Name + " | " + getContent()
                + " | " + getLevel() + " | " + getTime().toString();
    }

    public int getMember_ID() {
        return Member_ID;
    }

    public void setMember_ID(int member_ID) {
        Member_ID = member_ID;
    }

    public int getClub_ID() {
        return Club_ID;
    }

    public void setClub_ID(int club_ID) {
        Club_ID = club_ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getClub_Name() {
        return Club_Name;
    }

    public void setClub_Name(String club_Name) {
        Club_Name = club_Name;
    }
}
