package bean;

import java.util.Date;

public class Announcement {
    private int announcementId; //公告ID
    private String content; //公告内容
    private Date time; //发布时间
    private String title; //公告标题
    private Club club; //发布的社团
    private User publiser; //发布的学生

    public Announcement(){}

    //getter and setter
    public int getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(int announcementId) {
        this.announcementId = announcementId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public User getPubliser() {
        return publiser;
    }

    public void setPubliser(User publiser) {
        this.publiser = publiser;
    }
}
