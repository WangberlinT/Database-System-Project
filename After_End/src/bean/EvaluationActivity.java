package bean;

public class EvaluationActivity {
    private Evaluation evaluation; //对应的评价
    private Student student; //评价人
    private Activity activity; //被评价的活动

    EvaluationActivity(){}

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
