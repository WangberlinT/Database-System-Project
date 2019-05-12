package bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Activity {
    private int ActivityID; //活动ID
    private String ActivityName; //活动名称
    private Date StartTime; //开始日期
    private Date EndTime; //结束日期
    private int ApproverID; //审批人ID
    private int ResponseID; //负责人ID
    private int isPublic; //活动公开性
    private Set<Student> stus = new HashSet<>(); //参加成员

    public Set<Student> getStus() {
        return stus;
    }

    public void setStus(Set<Student> stus) {
        this.stus = stus;
    }

    public Activity() {
    }

    //getter and setter
    public int getActivityID() {
        return ActivityID;
    }

    public void setActivityID(int activityID) {
        ActivityID = activityID;
    }

    public String getActivityName() {
        return ActivityName;
    }

    public void setActivityName(String activityName) {
        ActivityName = activityName;
    }

    public Date getStartTime() {
        return StartTime;
    }

    public void setStartTime(Date startTime) {
        StartTime = startTime;
    }

    public Date getEndTime() {
        return EndTime;
    }

    public void setEndTime(Date endTime) {
        EndTime = endTime;
    }

    public int getApproverID() {
        return ApproverID;
    }

    public void setApproverID(int approverID) {
        ApproverID = approverID;
    }

    public int getResponseID() {
        return ResponseID;
    }

    public void setResponseID(int responseID) {
        ResponseID = responseID;
    }

    public int getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(int isPublic) {
        this.isPublic = isPublic;
    }
}
