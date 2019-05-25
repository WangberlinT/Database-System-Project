package bean;

public class Evaluation_Activity extends Evaluation {
    private int User_ID; //来自用户
    private int Activity_ID;   //目标活动

    private String Activity_Name; //活动名称
    private String Name; //用户名


    public Evaluation_Activity() {
    }

    @Override
    public String toString() {
        return getEvaluation_ID() + " | " + Activity_ID + Activity_Name + " | " + "  "
                + User_ID + " | " + Name + " | "
                + getContent() + " | " + getLevel() + " | " + getTime().toString();
    }

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int user_ID) {
        this.User_ID = user_ID;
    }

    public int getActivity_ID() {
        return Activity_ID;
    }

    public void setActivity_ID(int activity_ID) {
        this.Activity_ID = activity_ID;
    }

    public String getActivity_Name() {
        return Activity_Name;
    }

    public void setActivity_Name(String activity_Name) {
        Activity_Name = activity_Name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
