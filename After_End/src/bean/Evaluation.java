package bean;

import java.sql.Timestamp;

public class Evaluation {
    private int Evaluation_ID; //评价ID
    private String Content; //内容
    private Timestamp Time; //评价时间
    private int Level; //评价等级

    public Evaluation() {
    }


    //getter and setter
    public int getEvaluation_ID() {
        return Evaluation_ID;
    }

    public void setEvaluation_ID(int evaluation_ID) {
        this.Evaluation_ID = evaluation_ID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        this.Content = content;
    }

    public Timestamp getTime() {
        return Time;
    }

    public void setTime(Timestamp time) {
        this.Time = time;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        this.Level = level;
    }
}
