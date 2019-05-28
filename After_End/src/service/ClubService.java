package service;

import java.sql.*;
import java.util.List;

import bean.*;

public class ClubService extends BaseService {
    private int cid;
    private int uid;
    private Club clb;
    private String myClubHead = "No  社团ID  社团名     我的职位";

    public static String clubHead;
    private String memberHead = "No   用户ID    用户名    社团ID    社团名    职位";


    public ClubService(int cid, int uid) {
        this();
        this.cid = cid;
        this.uid = uid;
    }

    ClubService() {
        formatter.setMaxChars(5);
        clubHead = formatter.format("No");
        formatter.setMaxChars(5);
        clubHead+= formatter.format("社团ID");
        formatter.setMaxChars(5);
        clubHead += formatter.format("状态");
        formatter.setMaxChars(15);
        clubHead+= formatter.format("社团名")+formatter.format("社团类型")+"社团简介";
    }

    public int setCID()
    {
       return this.cid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public ClubService(int uid) {
        this();
        this.uid = uid;
    }

    public void getcid(int cid) throws SQLException {
        this.cid = cid;
        this.clb = clubDao.queryClubPrecise(cid);
    }

    //游客
    //展示所属社团
    public void showMyClub() throws SQLException {
        List<User_Club> uc = clubDao.queryone(uid);
        if (uc.size() == 0) {
            System.out.println("你还没有加入任何社团，请加入后再来" );

        } else {
            for (int i = 0; i < uc.size(); i++) {
                System.out.println(uc.get(i).toString());
            }
            System.out.println("请输入你想查看的社团id");
        }
    }

    //详细信息
    public void showClubInfo(int clid) throws SQLException {
        Club clb = clubDao.queryClubID(clid);
        long pnum = clubDao.queryClubPeopleNum(clid);
        System.out.println(clb.toString() + " 共" + pnum + "人");

    }

    //加入社团的申请
    public void applyTojoin(int cid, String content) throws SQLException {
        applyDao.insertJoinClub(uid, cid, content);
        System.out.println("申请已发送，请耐心等耐");
    }

    //建社
    public void applyToBuildClub(String cname, String ctype, String cintro) throws SQLException {

        clubDao.addClub(cname, ctype, cintro, uid);

        System.out.println("建社完毕");
    }

    //展示所有社团
    public void showAllClub() throws SQLException {
        long total = clubDao.getTotalClub();
        if (queryNotValid(total)) return;
        int page = 1;
        long totalPage = (total - 1) / pageSize + 1;
        while (page <= totalPage) {
            List<Club> clbl = clubDao.queryAllClub(page, pageSize);
            page = PrintPage(page, totalPage, clubHead, clbl);
            if (page == 0) return;
        }
    }

    //搜索社团
    public void showSearchClub(String name) throws SQLException {
        long total = clubDao.queryClubFuzzyNum(name);
        int page = 1;
        if (queryNotValid(total)) return;
        long totalPage = (total - 1) / pageSize + 1;
        while (page <= totalPage) {
            List<Club> cll = clubDao.queryClubFuzzy(name, page, pageSize);

            page = PrintPage(page, totalPage, clubHead, cll);
            if (page == 0) return;
        }
    }

    public boolean checkLeader(int clid) throws SQLException {
        Club clb = clubDao.queryClubPrecise(clid);
        return uid == clb.getClub_Leader();
    }

    //社员部分
    //得到社员列表
    public void showMemberList() throws SQLException {
        long total = userDao.getTotalUserByClub(cid);
        int page = 1;
        if (queryNotValid(total)) return;
        long totalPage = (total - 1) / pageSize + 1;
        while (page <= totalPage) {
            List<User_Club> ul = userDao.queryUserByClub(cid, page, pageSize);
            page = PrintPage(page, totalPage, memberHead, ul);
            if (page == 0) return;
        }
    }

    //申请举办活动
    public void applyToActivity(String content) throws SQLException {
        applyDao.insertActAdd(content, uid, (int) applyDao.getszID(cid));
        System.out.println("申请已发送，请耐心等耐");
    }

    public void joinAct(int acid) throws SQLException {
        clubDao.insertAct(uid, acid);
        System.out.println("加入完毕");
    }

    //社长部分

    public void evaMember(int usid, String content, int level) throws SQLException {
        evaluationDao.addEvaluationOfMember(usid, clb.getClub_Name(), content, level);
    }

    //
    //查看入社申请
    public void getJoinApply() throws SQLException {
        List<Apply> apl = applyDao.getJoinClub(uid);
        for (int i = 0; i < apl.size(); i++) {
            System.out.println(apl.toString());

        }
    }

    public void markReadApply(int aid) throws SQLException {
        applyDao.markread(aid);
    }

    //查看活动申请
    public void getActApply() throws SQLException {
        List<Apply> apl = applyDao.getActadd(uid);
        for (int i = 0; i < apl.size(); i++) {
            System.out.println(apl.get(i).toString());

        }
    }



    //增加物品
    public void addItem(int value, String name) throws SQLException {
        itemDao.insertItem(cid, name, value);
        System.out.println("添加成功");
    }

    //删除物品
    public void removeItem(int iid) throws SQLException {
        itemDao.deleteItem(iid);
        System.out.println("删除成功");
    }

    //查询财产数量
    public void checkItemList() throws SQLException {
        String tag = "名称               | 价格      |数量";
        List<ItemShow> is = itemDao.checkclubAll(cid);
        System.out.println(tag);
        for (int i = 0; i < is.size(); i++) {
            System.out.println(is.get(i).toString());
        }

    }

    //加人入社
    public void addMember(int uid, String work) throws SQLException {
        clubDao.joinclub(work, cid, uid);
        System.out.println("添加成功");
    }

    public Apply getApply(int iid) throws SQLException {
        return applyDao.getApply(iid);

    }

    //踢出社团
    public void dropMember(int uid) throws SQLException {
        clubDao.exitclub(cid, uid);
        System.out.println("删除完毕");
    }

    //查看借用信息
    public void checkLoan() throws SQLException {
        long total = itemDao.checkItemBNum(cid);
        if (queryNotValid(total)) return;
        int page = 1;
        long totalPage = (total - 1) / pageSize + 1;
        while (page <= totalPage) {
            List<ItemLoan> ul = itemDao.checkItemBclubPages(cid, page);
            page = PrintPage(page, totalPage, "", ul);
            if (page == 0) return;
        }
    }

    public void checkItemClub() throws SQLException {
        long total = itemDao.checkItemClubNum(cid);
        if (queryNotValid(total)) return;
        int page = 1;
        long totalPage = (total - 1) / pageSize + 1;
        while (page <= totalPage) {
            List<Item> ul = itemDao.checkItemClub(cid, page);
            page = PrintPage(page, totalPage, "", ul);
            if (page == 0) return;
        }
    }

    //查看申请数量
    public long checkApplyNum() throws SQLException {
        return applyDao.getApplyNum(uid);
    }

    //借东西给某人
    public void borrowItem(int usid, String name) throws SQLException {
        itemDao.borrowItem(usid, name, cid);
        System.out.println("借用完毕");
    }

    //某人来还东西
    public void returnItem(int usid, int iid) throws SQLException {
        itemDao.returnItem(usid, iid);
        System.out.println("归还完毕");
    }

    public void insertAct(String name, Timestamp st, Timestamp et, String intro, int r) throws SQLException {
        clubDao.insertActClub(name, st, et, intro, uid, cid, r);
        System.out.println("添加成功");
    }

    //重载还东西
    public void returnItem(int usid, String name) throws SQLException {
        itemDao.returnItem(usid, name, cid);
        System.out.println("归还完毕");


    }
    public void getAct ()throws SQLException {
        long total = clubDao.queryActivityByClubIDNUM(cid);
        int page = 1;
        if (queryNotValid(total)) return;
        long totalPage = (total - 1) / pageSize + 1;
        while (page <= totalPage) {
            List<Activity> ul = clubDao.queryActivityByClubID(cid, page);
            page = PrintPage(page, totalPage, "", ul);
            if (page == 0) return;
        }
    }

        public static void main (String[]args) throws SQLException {
            UserService cls = new UserService(null);
            cls.searchUser();
        }
        public void showAnnounce ( int club) throws SQLException {
            long total = announcementDao.numberofcluban(club);
            if (queryNotValid(total)) return;
            int page = 1;
            long totalPage = (total - 1) / pageSize + 1;
            while (page <= totalPage) {
                List<Announcement> ul = announcementDao.checkAnnoClub(club, page, pageSize);
                page = PrintPage(page, totalPage, "", ul);
                if (page == 0) return;
            }

        }

    }
