package bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Activity {
    private int Activity_ID; //活动ID
    private String Activity_Name; //活动名称
    private Date Start_Time; //开始日期
    private Date End_Time; //结束日期
    private int Response_ID; //负责人ID
    private boolean Range; //活动公开性
    private boolean state; //活动是否过期

    public Activity() {
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
