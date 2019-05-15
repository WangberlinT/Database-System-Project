package bean;

public class EvaluationPeople {
    private Evaluation evaluation; //对应评价
    private User user; //被评价的学生
    private Club club; //来自社团

    public EvaluationPeople(){}

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

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
