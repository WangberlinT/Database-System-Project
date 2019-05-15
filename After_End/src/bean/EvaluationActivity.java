package bean;

public class EvaluationActivity {
    private Evaluation evaluation; //对应的评价
    private User user; //评价人
    private Activity activity; //被评价的活动

    EvaluationActivity(){}

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
