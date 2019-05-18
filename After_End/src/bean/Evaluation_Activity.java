package bean;

public class Evaluation_Activity extends Evaluation {
    private int User_ID; //来自用户
    private int Activity_ID;   //目标活动

    public Evaluation_Activity() {
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
}
