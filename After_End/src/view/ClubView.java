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
    private EvaluationService evs = new EvaluationService();
    private ActivityService acs = new ActivityService();

    ClubView(int uid) {
        this.uid = uid;
    }

    private ClubService cls = new ClubService(uid);

    public void start() throws SQLException {
        cls.setUid(uid);
        final int EXIT = 4;
        while (true) {
            System.out.println("请选择要做的事:");
            System.out.println("1.搜索社团\n2.创建社团\n3.查看我加入的社团\n4.退出");
            int ins = InputInt(in, EXIT);
            if (ins == 4) {
                break;
            } else if (ins == 1) {

                System.out.println("请输入要搜索的社团名称(直接输入回车来浏览全部社团)：");
                String name = in.nextLine();
                while (true) {
                    searchClub(name);
                    System.out.println("请选择一个社团id查看：\n输入-1退出");
                    int cid = InputInt(in);
                    if (cid == -1) {
                        break;
                    }
                    cls.showClubInfo(cid);
                    System.out.println("是否要加入社团？\n1.是\n2.否");
                    int pd = InputInt(in, 2);
                    if (pd == 1) {
                        System.out.println("请输入入社理由");
                        cls.applyTojoin(cid, in.nextLine());
                    }
                }
            } else if (ins == 2) {
                createClub();
            } else if (ins == 3) {
                myClub();
            }
        }
    }


    public void myClub() throws SQLException {
        boolean isLeader;
        while (true) {
            cls.showMyClub();
            System.out.println("输入-1退出");
            int club = InputInt(in);
            if (club == -1) {
                break;
            }
            cls.getcid(club);
            isLeader = cls.checkLeader(club);
            final int EXIT = 9;
            while (true) {
                System.out.println("1.查看社员名单\n2.申请举办活动\n3.查看活动\n4.查看公告");
                if (isLeader) {
                    System.out.println("5.社内人员管理\n6.社团财产管理\n7.社团申请处理\n8.发布公告");
                    System.out.println("(您有" + cls.checkApplyNum() + "条申请未处理)");
                }
                System.out.println("输入9退出");
                int zs = InputInt(in, EXIT);
                if (zs == 1) {
                    cls.showMemberList();
                } else if (zs == 9) {
                    break;
                } else if (zs == 2) {
                    System.out.println("请输入对活动的描述");
                    String content = in.nextLine();
                    cls.applyToActivity(content);
                } else if (zs == 3) {
                    activityPart(club);
                } else if (zs == 4) {
                    cls.showAnnounce(club);
                } else if (isLeader) {
                    if (zs == 5) {
                        humanManage();
                    } else if (zs == 6) {
                        itemManage();
                    } else if (zs == 7) {
                        applyManage();
                    } else if (zs == 8) {
                        addAnnouncement(club);
                    }
                }
            }
        }
    }

    public void createClub() throws SQLException {
        System.out.println("请依次输入你要创建的社团名称、类型与社团简介。");
        System.out.println("名称：");
        String name = in.nextLine();
        System.out.println("类型：");
        String type = in.nextLine();
        System.out.println("简介：");
        String intro = in.nextLine();
        cls.applyToBuildClub(name, type, intro);
    }

    public void searchClub(String name) throws SQLException {
        cls.showSearchClub(name);
    }

    //加入活动todo
    public void activityPart(int club) throws SQLException {
    	while(true) {
            	cls.getAct();
            	System.out.println("选择一个你感兴趣的活动并加入:\n输入-1退出");
            	int acid=InputInt(in);
            	if(acid==-1) {
            		break;
            	}else {
					cls.joinAct(acid);
            	}
            	}
    }




    public void itemManage() throws SQLException {
        final int EXIT = 7;
        while (true) {
            System.out.println("1.查询道具列表\n2.查询借用状况\n3.道具借出\n4.道具归还\n5.增加道具\n6.删除道具\n7.退出");
            int ins = InputInt(in, EXIT);
            if (ins == 1) {
                cls.checkItemList();
            } else if (ins == 2) {
                cls.checkLoan();
            } else if (ins == 7) {
                break;
            } else if (ins == 3) {
                System.out.println("请输入借用者id：");
                int usid = InputInt(in);
                cls.checkItemClub();
                System.out.println("请输入被借物品ID：");
                int iid = InputInt(in);
                cls.borrowItem(usid, iid);
            } else if (ins == 4) {
                System.out.println("请输入归还者id：");
                int uid = InputInt(in);
                System.out.println("请输入归还物品名称：");
                String iid = in.nextLine();
                cls.returnItem(uid, iid);
            } else if (ins == 5) {
                System.out.println("请输入物品名称：");
                String itemName = in.nextLine();
                System.out.println("请输入物品价格：");
                int value = InputInt(in);
                cls.addItem(value, itemName);
            } else if (ins == 6) {
                cls.checkItemClub();
                System.out.println("请输入要删除的道具id,-1退出");
                int iid = InputInt(in);
                cls.removeItem(iid);
            }
        }
    }

    public void humanManage() throws SQLException {
        final int EXIT = 3;
        while (true) {
            System.out.println("选择要进行的操作：\n1.删除社员\n2.评价社员\n3.退出");
            int ins = InputInt(in, EXIT);
            if (ins == 1) {
                System.out.println("选择要删除的社员ID：");
                cls.showMemberList();
                int id = InputInt(in);
                cls.dropMember(id);
            } else if (ins == 3) {
                break;
            } else if (ins == 2) {
                cls.showMemberList();
                System.out.println("选择要评价的社员id：");
                int name = InputInt(in);
                System.out.println("输入对该社员的评价：");
                String content = in.nextLine();
                System.out.println("输入该社员的评分级别：  (0-10)");
                int level = InputInt(in, 10);
                cls.evaMember(name, content, level);
                System.out.println("评价成功");
            }
        }
    }

    public void applyManage() throws SQLException {
        final int EXIT = 3;
        System.out.println("请选择要处理的申请:");
        System.out.println("1.入社申请\n2.活动申请\n3.退出");
        int ins = InputInt(in, EXIT);
        if (ins == 1) {
            while (true) {
                cls.getJoinApply();
                System.out.println("选择您要处理的申请或输入:\n输入-1退出");
                int app = InputInt(in);
                if (app == -1) {
                    break;
                }
                Apply apply = cls.getApply(app);
                evs.queryEvaluationOfMember(apply.getApply_From());
                System.out.println("是否将他/她加入社团？\n如果是请输入他的工作名称，如果不是请输入不。");
                String work = in.nextLine();
                if (!work.equals("不")) {
                    cls.addMember(apply.getApply_From(), work);
                }
                cls.markReadApply(app);
            }
        } else if (ins == 2) {
            while (true) {
                cls.getActApply();
                System.out.println("选择您要处理的申请或输入-1退出:");
                int app = InputInt(in);
                if (app == -1) {
                    break;
                }
                System.out.println("是否举办该活动？\n如果是请输入是，如果不是请输入不。");
                String work = in.nextLine();
                if (!work.equals("不")) {
                    Activity act = new Activity();
                    System.out.println("请输入活动名称：");
                    act.setActivity_Name(in.nextLine());
                    act.setState(true);
                    act.setResponse_ID(uid);

                    System.out.println("请输入活动开始时间\n" + "年：");
                    int year = InputInt(in);
                    System.out.println("月：");
                    int month = InputInt(in);
                    System.out.println("日：");
                    int day = InputInt(in);
                    System.out.println("时：");
                    int hour = InputInt(in);
                    System.out.println("分：");
                    int min = InputInt(in);
                    int second = 0;
                    Timestamp start = Timestamp.valueOf(LocalDateTime.of(year, month, day, hour, min, second));
                    act.setStart_Time(start);
                    System.out.println("请输入活动结束时间\n" + "年：");
                    year = InputInt(in);
                    System.out.println("月：");
                    month = InputInt(in);
                    System.out.println("日：");
                    day = InputInt(in);
                    System.out.println("时：");
                    hour = InputInt(in);
                    System.out.println("分：");
                    min = InputInt(in);
                    start = Timestamp.valueOf(LocalDateTime.of(year, month, day, hour, min, second));
                    act.setEnd_Time(start);
                    System.out.println("请输入活动是否仅限社内参加（1代表仅限社内参加，0代表全校参加）：");
                    int a = InputInt(in);
                    act.setRange(a != 1);
                    acs.createActivity(act,cls.setCID());
                    System.out.println("添加完成");
                }
                cls.markReadApply(app);
            }
        }
    }

    private void addAnnouncement(int club) throws SQLException {
        System.out.println("请输入想要发布的公告标题:");
        String bt = in.nextLine();
        System.out.println("请输入想要发布的公告内容:");
        String nr = in.nextLine();
        AnnouncementDao a = new AnnouncementDao();
        a.addAnnouncement(club, uid, nr, bt);
    }
    public static void  main(String[] args) throws SQLException{
        ClubView clv=new ClubView(11711613);
        clv.start();
    }
}
