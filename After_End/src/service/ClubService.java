package service;

import java.sql.SQLException;
import java.util.List;

import bean.*;
import dao.*;

public class ClubService extends BaseService {
    private static ClubDao cld = new ClubDao();
    private static ItemDao itd=new ItemDao();
    private static ApplyDao apd=new ApplyDao();
    private int cid;
    private int uid;
    private String culuehead = "No  社团ID  社团名     我的职位";
    private String xiangxihead = "社团ID  社团名    社团类型        社团人数     社团活动";
    
    ClubService(int cid,int uid){
    	this.cid=cid;
    	this.uid=uid;
    }
    
    //游客
    //展示所属社团
    public void showMyClub() throws SQLException {
    	List<User_Club> uc=cld.queryone(uid);
    	if(uc.size()==0) {
    		System.out.println("你还没有加入任何社团，请加入后再来");
    	}
    	
    	System.out.println(culuehead);
    	for(int i=0;i<uc.size();i++) {
    		System.out.println(i+"  " +uc.get(i).toString());
    	}
    }
     
    //详细信息
    public void showClubInfo() throws SQLException{
    	Club clb= cld.queryClubID(cid);
    	long pnum=cld.queryClubpeopleNum(cid);
    	System.out.println(clb.toString()+" 共"+pnum+"人");
    	
    }
    //加入社团的申请
    public void applyTojoin(int cid, String content) throws SQLException {
    	apd.insertJoinClub(uid, cid, content);
    	System.out.println("申请已发送，请耐心等耐");
    }
    //建社申请
    public void applyTobuildClub(String cname,String ctype,String reason,int tid) throws SQLException {
    	apd.inserClubBuild(cname, ctype, reason, uid, tid);
    	System.out.println("申请已发送，请耐心等耐");
    }
    //展示所有社团
    public void showAllClub() throws SQLException {
    	long total=cld.getTotalClub();
    	int pageSize=10;
    	int page=1;		
    	
    	long totalPage = (total - 1) / pageSize + 1;
    	while (page <= totalPage) {
    		List<Club> clbl=cld.queryAllClub(page);
            page = PrintPage(page, totalPage, xiangxihead, clbl);
            if (page == 0) return;
        }
    }
    //搜索社团
    public void showSearchClub(String name) throws SQLException {    	
    	long total=cld.queryClubFuzzyNum(name);
    	int pageSize=10;
    	int page=1;		    	
    	long totalPage = (total - 1) / pageSize + 1;
    	while (page <= totalPage) {
    		List<Club> cll=cld.queryClubFuzzy(name,page);
            page = PrintPage(page, totalPage, xiangxihead, cll);
            if (page == 0) return;
        }
    	
    }
    
    //社员部分
    //得到社员列表
    public void showMemberList() throws SQLException {
    	
    	long total=cld.queryClubpeopleNum(cid);
    	int pageSize=10;
    	int page=1;		    	
    	long totalPage = (total - 1) / pageSize + 1;
    	while (page <= totalPage) {
    		List<User> ul=cld.queryClubpeople(cid,page);
            page = PrintPage(page, totalPage, xiangxihead, ul);
            if (page == 0) return;
        }
    }
    
    //申请举办活动
    public void applyToActivity(String content) throws SQLException {
    	apd.insertActAdd(content, uid, (int)apd.getszID(cid));
    	System.out.println("申请已发送，请耐心等耐");
    }
    
    //社长部分
    //
    //查看入社申请
    public void getJoinApply() throws SQLException {
    	List<Apply> apl=apd.getJoinClub(uid);
    	for(int i=0;i<apl.size();i++) {
    		System.out.println(apl.toString());
    		apd.markread(apl.get(i).getApply_ID());
    	}
    }
    //查看活动申请
    public void getActApply() throws SQLException {
    	List<Apply> apl=apd.getActadd(uid);
    	for(int i=0;i<apl.size();i++) {
    		System.out.println(apl.toString());
    		apd.markread(apl.get(i).getApply_ID());
    	}
    }
    //增加物品
    public void addItem(int value,String name) throws SQLException {
    	itd.insertItem(cid, name, value);
    	System.out.println("添加成功");
    }
    //删除物品
    public void removeItem(int iid) throws SQLException {
    	itd.deleteItem(iid);
    	System.out.println("删除成功");
    }
    
    //查询财产数量
    public void checkItemList() throws SQLException {
    	String tag="名称               | 价格      |数量";
    	List<ItemShow> is= itd.checkclubAll(cid);
    	System.out.println(tag);
    	for(int i=0;i<is.size();i++) {
    		System.out.println(is.get(i).toString());
    	}
    	
    }

    //加人入社
    public void addMember(int usid,String work) throws SQLException {
    	cld.joinclub(work, cid, usid);
    	System.out.println("添加成功");
    }
    
    //踢出社团
    public void dropMember(int usid) throws SQLException {
    	cld.exitclub(cid, usid);
    	System.out.println("删除完毕");
    }
    //查看借用信息
    public void checkLoan() throws SQLException {
    	long total=itd.checkItemBNum(cid);
    	int pageSize=10;
    	int page=1;		    	
    	long totalPage = (total - 1) / pageSize + 1;
    	while (page <= totalPage) {
    		List<ItemLoan> ul=itd.checkItemBclubPages(cid, page);
            page = PrintPage(page, totalPage, xiangxihead, ul);
            if (page == 0) return;
        }
    }

    public void checkApplyNum() throws SQLException {
    	System.out.println("您共有"+apd.getApplyNum(uid)+"未读");
    }
    
}
