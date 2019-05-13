package bean;

import java.util.Date;

public class Evaluation {
    private int evaluationId; //评价ID
    private String content; //内容
    private Date time; //评价时间
    private int level; //评价等级

    public Evaluation() {
    }

    //getter and setter
    public int getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(int evaluationId) {
        this.evaluationId = evaluationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
