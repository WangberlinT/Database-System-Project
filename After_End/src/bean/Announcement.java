package bean;

import java.sql.Timestamp;

public class Announcement {
    private int Announcement_ID; //公告ID
    private String Content; //公告简介
    private Timestamp Time; //发布时间
    private String Title; //公告标题
    private int Club_ID; //社团id
    private int Publisher; //发布人id
    private String club;//社团名字

    public Announcement() {
    }

    public String toString() {
        return "From " + club + " " + Title + ": " + Content + " (" + Time.toString() + ")";
    }


    //getter and setter
    public int getAnnouncement_ID() {
        return Announcement_ID;
    }


    public void setAnnouncement_ID(int announcement_ID) {
        this.Announcement_ID = announcement_ID;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public void setTime(Timestamp time) {
        Time = time;
    }

    public Timestamp getTime() {
        return Time;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        this.Content = content;
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
