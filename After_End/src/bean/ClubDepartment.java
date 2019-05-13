package bean;

public class ClubDepartment {
    private int departmentId; //子部门ID
    private Club club; //父社团
    private String intro; //部门介绍

    public ClubDepartment(){}

    //getter and setter
    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
