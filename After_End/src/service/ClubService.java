package service;

import java.sql.SQLException;
import java.util.List;

import bean.*;

public class ClubService extends BaseService {
    private int cid;
    private int uid;
    private Club clb;
    private String myClubHead = "No  社团ID  社团名     我的职位";
    private String clubHead = "No   社团ID社团名    社团类型  社团简介 ";

    public ClubService(int cid, int uid) {
        this.cid = cid;
        this.uid = uid;
    }
    ClubService() {    }

    public void setUid(int uid){
        this.uid=uid;
    }

    public ClubService(int uid) {  this.uid = uid;  }
    public void getcid(int cid) throws SQLException {this.cid = cid;
    this.clb= clubDao.queryClubPrecise(cid);
    }
    //游客
    //展示所属社团
    public void showMyClub() throws SQLException {
        List<User_Club> uc = clubDao.queryone(uid);
        if (uc.size() == 0) {
            System.out.println("你还没有加入任何社团，请加入后再来"+uc.size());

        }else {
            for (int i = 0; i < uc.size(); i++) {
                System.out.println(uc.get(i).toString());
            }
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
    public void applyTobuildClub(String cname, String ctype, String cintro) throws SQLException {
        
    	clubDao.addClub(cname,ctype,cintro,uid);
        
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

            page = PrintPage(page, totalPage,clubHead , cll);
            if (page == 0) return;
        }
    }

    public boolean checksz(int clid) throws SQLException {
    	Club clb=clubDao.queryClubPrecise(clid);
    	return uid==clb.getClub_Leader();
    }
    
    //社员部分
    //得到社员列表
    public void showMemberList() throws SQLException {
        long total = clubDao.queryClubPeopleNum(cid);
        int page = 1;
        long totalPage = (total - 1) / pageSize + 1;
        while (page <= totalPage) {
            List<User> ul = clubDao.queryClubPeople(cid, page, pageSize);
            page = PrintPage(page, totalPage, "", ul);
            if (page == 0) return;
        }
    }

    //申请举办活动
    public void applyToActivity(String content) throws SQLException {
        applyDao.insertActAdd(content, uid, (int) applyDao.getszID(cid));
        System.out.println("申请已发送，请耐心等耐");
    }

    public void showAllAct() {
    	
    }
    
    //社长部分
    
    public void evaMember(int usid,String content,int level) throws SQLException {
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

    public void markreadApply(int aid) throws SQLException {
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

    public void checkItemClub() throws SQLException{
        long total = itemDao.checkItemClubNum(cid);
        if (queryNotValid(total)) return;
        int page = 1;
        long totalPage = (total - 1) / pageSize + 1;
        while (page <= totalPage) {
            List<Item> ul = itemDao.checkItemClub(cid,page);
            page = PrintPage(page, totalPage, "", ul);
            if (page == 0) return;
        }
    }

    //查看申请数量
    public long checkApplyNum() throws SQLException {
       return applyDao.getApplyNum(uid);
    }

    //借东西给某人
    public void borrowItem(int usid,String name) throws SQLException {
    	itemDao.borrowItem(usid, name, cid);
    	System.out.println("借用完毕");
    }
    //某人来还东西
    public void returnItem(int usid,int iid) throws SQLException {
    	itemDao.huanItem(usid, iid);
    	System.out.println("归还完毕");
    }
   

    public void returnItem(int usid,String name) throws SQLException {
    	itemDao.huanItem(usid, name, cid);
    	System.out.println("归还完毕");
    }
    public static void main(String[] args) throws SQLException {
    	UserService cls=new UserService(null);
    	cls.searchUser();
    }
    public void showannounce(int club) throws SQLException{
        long total = announcementDao.numberofcluban(club);
        int page = 1;
        long totalPage = (total - 1) / pageSize + 1;
        while (page <= totalPage) {
            List<Announcement> ul = announcementDao.checkAnnoClub(club,page, pageSize);
            page = PrintPage(page, totalPage, "", ul);
            if (page == 0) return;
        }

    }
    
}
