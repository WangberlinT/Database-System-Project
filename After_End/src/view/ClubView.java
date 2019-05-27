package view;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import bean.*;
import dao.ClubDao;
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
            System.out.println("1.查看社员名单\n2.申请举办活动\n3.查看活动");
            
            if(shezhang) {
            	System.out.println("4.社内人员管理\n5.社团财产管理\n6.社团申请处理");
            	System.out.println("(您有"+cls.checkApplyNum()+"条申请未处理)");
            }
            System.out.println("输入-1退出");
            int zs=in.nextInt();
            if(zs==1) {
            	cls.showMemberList();;
            }else if(zs==2) {
            	System.out.println("请输入对活动的描述");
            	String content=in.next();
            	cls.applyToActivity(content);
            }else if(zs==3) {
            	activitypart(club);
            }else
            	if(shezhang) {
            
            	if(zs==4) {
            		humanmanage();
            	}else if(zs==5) {
            		itemmanage();
            	}else if(zs==6) {
            		applymanage();
            	}
            }else if(zs==-1) {
            	break;
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
    		System.out.println("1.查询道具列表\n2.查询借用状况\n3.道具借出\n4.道具归还\n5.退出");
    		int ins=in.nextInt();
    		if(ins==1) {
    			cls.checkItemList();
    		}else if(ins==2) {
    			cls.checkLoan();
    		}else if(ins==5) {
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
    			System.out.println("选择要评价的社员姓名：");
    			cls.showMemberList();
    			String name=in.next();
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
					System.out.println("请输入活动开始时间(yyyy-mm-ss hh:mm:ss)：");
					String sd = in.next();
					act.setStart_Time(sdf.parse(sd));
					System.out.println("请输入活动结束时间(yyyy-mm-ss hh:mm:ss)：");
					sd = in.next();
					act.setEnd_Time(sdf.parse(sd));
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

    public static void main(String[] args) throws SQLException, ParseException {
    	ClubView clv=new ClubView(11711613);
			clv.start();
    }
}
