package bean;

import java.util.HashSet;
import java.util.Set;

public class Club {
    private int ClubID; //社团ID
    private String ClubName; //社团名
    private String ClubType; //社团类型
    private String ClubIntro; //社团简介
    private int LeaderID; //头头的ID
    private Set<User> stus = new HashSet<>(); //社团成员
    private Set<Activity> acts = new HashSet<>(); //社团活动
    private Set<Item> items = new HashSet<>(); //社团资产

    public Club() {
    }

    //getter and setter
    public Set<User> getStus() {
        return stus;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public void setStus(Set<User> stus) {
        this.stus = stus;
    }

    public Set<Activity> getActs() {
        return acts;
    }

    public void setActs(Set<Activity> acts) {
        this.acts = acts;
    }

    public int getClubID() {
        return ClubID;
    }

    public void setClubID(int clubID) {
        ClubID = clubID;
    }

    public String getClubName() {
        return ClubName;
    }

    public void setClubName(String clubName) {
        ClubName = clubName;
    }

    public String getClubType() {
        return ClubType;
    }

    public void setClubType(String clubType) {
        ClubType = clubType;
    }

    public String getClubIntro() {
        return ClubIntro;
    }

    public void setClubIntro(String clubIntro) {
        ClubIntro = clubIntro;
    }

    public int getLeaderID() {
        return LeaderID;
    }

    public void setLeaderID(int leaderID) {
        LeaderID = leaderID;
    }
}
