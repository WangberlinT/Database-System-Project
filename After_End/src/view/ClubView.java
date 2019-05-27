package view;

import java.sql.*;
import java.time.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import bean.*;
import dao.*;
import service.*;
import util.CustomerException;

public class ClubView extends View {

    
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private int uid;
    private boolean shezhang;
    private EvaluationService evs=new EvaluationService();
    private ActivityService acs=new ActivityService();
    ClubView(int uid){
    	this.uid=uid;
    }
    private ClubService cls=new ClubService(uid);
    
    public void start() throws SQLException, ParseException {
    	cls.setUid(uid);
    	while(true) {
    		System.out.println("请选择要做的事:");
    		System.out.println("1.搜索社团\n2.创建社团\n3.查看我加入的社团\n4.退出");
    		int ins=in.nextInt();
    		if(ins==4) {
    			break;
    		}else if(ins==1) {
    			System.out.println("请输入要搜索的社团名称");
    			String name=in.next();
    			while(true) {
    			searchClub(name);
    			System.out.println("请选择一个社团id查看：\n输入-1退出");
    			int clid=in.nextInt();
    			if(clid==-1) {
    				break;
    			}
    			cls.showClubInfo(clid);
    			System.out.println("是否要加入社团？\n1.是\n2.否");
    			int pd=in.nextInt();
    			if(pd==1) {
    				System.out.println("请输入入社理由");
    				cls.applyTojoin(clid, in.next());
    			}
    			}
    		}else if(ins==2) {
    			createclub();
    		}else if(ins==3) {
    			myClub();
    		}
    	}
    }
    

    public void myClub() throws SQLException, ParseException {
        while (true) {
        	cls.showMyClub();
        	System.out.println("请选择你要查看的社团\n输入-1退出");
        	int club=in.nextInt();
        	if(club==-1) {
        		break;
        	}
        	cls.getcid(club);
        	shezhang=cls.checksz(club);
        	while(true) {
            System.out.println("1.查看社员名单\n2.申请举办活动\n3.查看活动\n4.查看公告");
            
            if(shezhang) {
            	System.out.println("5.社内人员管理\n6.社团财产管理\n7.社团申请处理\n8.发布公告");
            	System.out.println("(您有"+cls.checkApplyNum()+"条申请未处理)");
            }
            System.out.println("输入-1退出");
            int zs=in.nextInt();
            if(zs==1) {
            	cls.showMemberList();;
            }else if(zs==-1) {
				break;
			}else if(zs==2) {
            	System.out.println("请输入对活动的描述");
            	String content=in.next();
            	cls.applyToActivity(content);
            }else if(zs==3) {
            	activitypart(club);
            }else if(zs==4){
				cls.showannounce(club);
			}
            else if(shezhang) {
            	if(zs==5) {
            		humanmanage();
            	}else if(zs==6) {
            		itemmanage();
            	}else if(zs==7) {
            		applymanage();
            	}else if(zs==8){
					addan(club);
				}
            }
            }
        	}
                
        }


    public void createclub() throws SQLException {
    	System.out.println("请依次输入你要创建的社团名称、类型与社团简介。");
    	System.out.println("名称：");
    	String name=in.next();
    	System.out.println("类型：");
    	String type=in.next();
    	System.out.println("简介：");
    	String intro=in.next();
    	cls.applyTobuildClub(name, type, intro);
    }
    
       
    public void searchClub(String name) throws SQLException {
    	cls.showSearchClub(name);
    }

    
    
    
    //加入活动todo
    public void activitypart(int club) throws SQLException {
    	while(true) {
            	acs.showActivityYearByClub(club);
            	System.out.println("选择一个你感兴趣的活动并加入:\n输入-1退出");
            	int acid=in.nextInt();
            	if(acid==-1) {
            		break;
            	}else {
            		
            	}
            	}
    }
    

    public void itemmanage() throws SQLException {
    	while(true) {
    		System.out.println("1.查询道具列表\n2.查询借用状况\n3.道具借出\n4.道具归还\n5.增加道具\n6.删除道具\n7.退出");
    		int ins=in.nextInt();
    		if(ins==1) {
    			cls.checkItemList();
    		}else if(ins==2) {
    			cls.checkLoan();
    		}else if(ins==7) {
    			break;
    		}else if(ins==3) {
    			System.out.println("请输入借用者id：");
    			int usid=in.nextInt();
    			System.out.println("请输入被借物品名称：");
    			String name=in.next();
    			cls.borrowItem(usid, name);
    		}else if(ins==4) {
    			System.out.println("请输入归还者id：");
    			int usid=in.nextInt();
    			System.out.println("请输入归还物品名称：");
    			String iid=in.next();
    			cls.returnItem(usid, iid);
    		}else if(ins==5){
    			System.out.println("请输入物品名称：");
    			String iname=in.next();
    			System.out.println("请输入物品价格：");
    			int value=in.nextInt();
    			cls.addItem(value,iname);
			}else if(ins==6){
    			cls.checkItemClub();
    			System.out.println("请输入要删除的道具id,-1退出");
    			int iid=in.nextInt();
    			cls.removeItem(iid);
			}
    	}
    		
    }
    
    public void humanmanage() throws SQLException {
    	while(true) {
    		
    		System.out.println("选择要进行的操作：\n1.删除社员\n2.评价社员\n3.退出");
    		int ins=in.nextInt();
    		if(ins==1) {
    			System.out.println("选择要删除的社员ID：");
    			cls.showMemberList();
    			int id=in.nextInt();
    			cls.dropMember(id);
    		}else if(ins==3) {
    			break;
    		}else if(ins==2) {
    			cls.showMemberList();
    			System.out.println("选择要评价的社员id：");

    			int name=in.nextInt();
    			System.out.println("输入对该社员的评价：");
    			String content=in.next();
    			System.out.println("输入该社员的评分级别：");
    			int level=in.nextInt();
    			cls.evaMember(name, content, level);
    			System.out.println("评价成功");
    		}
    	}
    	
    	
    }
    
    public void applymanage() throws SQLException, ParseException {
    	System.out.println("请选择要处理的申请:");
    	System.out.println("1.入社申请\n2.活动申请\n3.退出");
    	int ins=in.nextInt();
    	if(ins==1) {
    		while(true) {
    		cls.getJoinApply();
    		System.out.println("选择您要处理的申请或输入:\n输入-1退出");
    		int app=in.nextInt();
    		if(app==-1) {
    			break;
    		}
    		Apply apply=cls.getApply(app);
    		evs.queryEvaluationOfMember(apply.getApply_From());
    		System.out.println("是否将他/她加入社团？\n如果是请输入他的工作名称，如果不是请输入不。");
    		String work=in.next();
    		if(!work.equals("不")) {
    			cls.addMember(apply.getApply_From(), work);
    		}
    		cls.markreadApply(app);    		
    		
    		}
    	}else if(ins==2) {
			while (true) {
				cls.getActApply();
				System.out.println("选择您要处理的申请或输入-1退出:");
				int app = in.nextInt();
				if (app == -1) {
					break;
				}
				System.out.println("是否举办该活动？\n如果是请输入是，如果不是请输入不。");
				String work = in.next();
				if (!work.equals("不")) {
					Activity act = new Activity();
					System.out.println("请输入活动名称：");
					act.setActivity_Name(in.next());
					act.setState(true);
					act.setResponse_ID(uid);

					System.out.println("请输入活动开始时间\n"+"年：");
					int year = in.nextInt();
					System.out.println("月：");
					int month = in.nextInt();
					System.out.println("日：");
					int day = in.nextInt();
					System.out.println("时：");
					int hour = in.nextInt();
					System.out.println("分：");
					int min = in.nextInt();
					int second = 0;
					Timestamp start = Timestamp.valueOf(LocalDateTime.of(year, month, day, hour, min, second));
					act.setStart_Time(start);
					System.out.println("请输入活动结束时间\n"+"年：");
					year = in.nextInt();
					System.out.println("月：");
					 month = in.nextInt();
					System.out.println("日：");
					day = in.nextInt();
					System.out.println("时：");
					hour = in.nextInt();
					System.out.println("分：");
					min = in.nextInt();
					start = Timestamp.valueOf(LocalDateTime.of(year, month, day, hour, min, second));
					act.setEnd_Time(start);
					System.out.println("请输入活动是否仅限社内参加（1代表仅限社内参加，0代表全校参加）：");
					int a = in.nextInt();
					act.setRange(a == 1 ? false : true);

					acs.createActivity(act);
					System.out.println("添加完成");
				}
				cls.markreadApply(app);
			}
		}
    }

    private void addan(int club)throws SQLException{
		System.out.println("请输入想要发布的公告标题:");
		String bt=in.nextLine();
    	System.out.println("请输入想要发布的公告内容:");
    	String nr=in.nextLine();
    	AnnouncementDao a= new AnnouncementDao();
    	a.addAnnouncement(club,uid,nr,bt);
	}

    public static void main(String[] args) throws SQLException, ParseException {
    	ClubView clv=new ClubView(11711613);
			clv.start();
    }
}
