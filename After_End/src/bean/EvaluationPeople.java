package bean;

public class EvaluationPeople {
    private Evaluation evaluation; //对应评价
    private Student student; //被评价的学生
    private Club club; //来自社团

    public EvaluationPeople(){}

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

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
