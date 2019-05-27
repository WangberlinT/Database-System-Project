package bean;

import util.StringAlign;

import java.util.Date;

public class Activity {
    private int Activity_ID; //活动ID
    private String Activity_Name; //活动名称
    private String Activity_Content; //活动内容
    private Date Start_Time; //开始日期
    private Date End_Time; //结束日期
    private int Response_ID; //负责人ID
    private boolean Range; //活动公开性
    private boolean state; //活动是否过期

    public Activity(String Activity_Name, String Activity_Content, Date Start_Time, Date End_Time, int response_ID, boolean Range, boolean state) {
        this.Activity_Name = Activity_Name;
        this.Activity_Content = Activity_Content;
        this.Start_Time = Start_Time;
        this.End_Time = End_Time;
        this.Response_ID = response_ID;
        this.Range = Range;
        this.state = state;
    }

    public Activity() {
    }

    @Override
    public String toString() {
        StringAlign formatter = new StringAlign(8, StringAlign.JUST_LEFT);
        String id = formatter.format(Integer.toString(Activity_ID));
        String name = Activity_Name;
        String start = Start_Time.toString();
        String end = End_Time.toString();
        String respon = Integer.toString(Response_ID);
        String result = formatter.format(id);
        formatter.setMaxChars(15);
        result += formatter.format(name);
        formatter.setMaxChars(25);
        result += formatter.format(start);
        result += formatter.format(end);
        formatter.setMaxChars(20);
        result += formatter.format(respon);
        return result;
    }

    public void Print() {
        System.out.println("名称： " + Activity_Name);
        System.out.println("内容:  " + Activity_Content);
    }


    //getter and setter
    public int getActivity_ID() {
        return Activity_ID;
    }

    public void setActivity_ID(int activity_ID) {
        Activity_ID = activity_ID;
    }

    public String getActivity_Name() {
        return Activity_Name;
    }

    public void setActivity_Name(String activity_Name) {
        Activity_Name = activity_Name;
    }

    public Date getStart_Time() {
        return Start_Time;
    }

    public void setStart_Time(Date start_Time) {
        Start_Time = start_Time;
    }

    public Date getEnd_Time() {
        return End_Time;
    }

    public void setEnd_Time(Date end_Time) {
        End_Time = end_Time;
    }


    public int getResponse_ID() {
        return Response_ID;
    }

    public void setResponse_ID(int response_ID) {
        Response_ID = response_ID;
    }

    public boolean isState() {
        return state;
    }

    public boolean isRange() {
        return Range;
    }

    public void setRange(boolean range) {
        Range = range;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
