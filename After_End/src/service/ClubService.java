package service;

import java.sql.SQLException;
import java.util.List;

import bean.*;
import dao.*;

public class ClubService extends BaseService {
    private int cid;
    private int uid;
    private String myClubHead = "No  社团ID  社团名     我的职位";
    private String clubHead = "社团ID  社团名    社团类型        社团人数     社团活动";

    ClubService(int cid, int uid) {
        this.cid = cid;
        this.uid = uid;
    }

    //游客
    //展示所属社团
    public void showMyClub() throws SQLException {
        List<User_Club> uc = clubDao.queryone(uid);
        if (uc.size() == 0) {
            System.out.println("你还没有加入任何社团，请加入后再来");
        }

        System.out.println(myClubHead);
        for (int i = 0; i < uc.size(); i++) {
            System.out.println(i + "  " + uc.get(i).toString());
        }
    }

    //详细信息
    public void showClubInfo() throws SQLException {
        Club clb = clubDao.queryClubID(cid);
        long pnum = clubDao.queryClubpeopleNum(cid);
        System.out.println(clb.toString() + " 共" + pnum + "人");

    }

    //加入社团的申请
    public void applyTojoin(int cid, String content) throws SQLException {
        applyDao.insertJoinClub(uid, cid, content);
        System.out.println("申请已发送，请耐心等耐");
    }

    //建社申请
    public void applyTobuildClub(String cname, String ctype, String reason, int tid) throws SQLException {
        applyDao.inserClubBuild(cname, ctype, reason, uid, tid);
        System.out.println("申请已发送，请耐心等耐");
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

    //社员部分
    //得到社员列表
    public void showMemberList() throws SQLException {
        long total = clubDao.queryClubpeopleNum(cid);
        int page = 1;
        long totalPage = (total - 1) / pageSize + 1;
        while (page <= totalPage) {
            List<User> ul = clubDao.queryClubpeople(cid, page, pageSize);
            page = PrintPage(page, totalPage, clubHead, ul);
            if (page == 0) return;
        }
    }

    //申请举办活动
    public void applyToActivity(String content) throws SQLException {
        applyDao.insertActAdd(content, uid, (int) applyDao.getszID(cid));
        System.out.println("申请已发送，请耐心等耐");
    }

    //社长部分
    //
    //查看入社申请
    public void getJoinApply() throws SQLException {
        List<Apply> apl = applyDao.getJoinClub(uid);
        for (int i = 0; i < apl.size(); i++) {
            System.out.println(apl.toString());
            applyDao.markread(apl.get(i).getApply_ID());
        }
    }

    //查看活动申请
    public void getActApply() throws SQLException {
        List<Apply> apl = applyDao.getActadd(uid);
        for (int i = 0; i < apl.size(); i++) {
            System.out.println(apl.toString());
            applyDao.markread(apl.get(i).getApply_ID());
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
            page = PrintPage(page, totalPage, clubHead, ul);
            if (page == 0) return;
        }
    }

    public void checkApplyNum() throws SQLException {
        System.out.println("您共有" + applyDao.getApplyNum(uid) + "未读");
    }

}
