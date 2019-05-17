package bean;

import java.util.Date;

public class Announcement {
    private int Announcement_ID; //公告ID
    private String Content; //公告内容
    private Date Time; //发布时间
    private String Title; //公告标题
    private int Club_ID; //发布的社团
    private int Publisher; //发布的学生

    public Announcement() {
    }

    //getter and setter
    public int getAnnouncement_ID() {
        return Announcement_ID;
    }

    public void setAnnouncement_ID(int announcement_ID) {
        this.Announcement_ID = announcement_ID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        this.Content = content;
    }

    public Date getTime() {
        return Time;
    }

    public void setTime(Date time) {
        this.Time = time;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public int getClub_ID() {
        return Club_ID;
    }

    public void setClub_ID(int club_ID) {
        Club_ID = club_ID;
    }

    public int getPublisher() {
        return Publisher;
    }

    public void setPublisher(int publisher) {
        Publisher = publisher;
    }
}
