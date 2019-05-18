package bean;

public class Evaluation_Member extends Evaluation {
    private int Member_ID;
    private int Club_ID;

    public Evaluation_Member() {
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
}
