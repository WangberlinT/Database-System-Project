package service;

import bean.Club;
import bean.User;
import dao.ClubDao;
import util.CustomerException;

public class AdminClubManager extends BaseService {

    private ClubService clubService;
    public AdminClubManager()
    {
        clubService = new ClubService();
    }

    public void showMenu()
    {
        System.out.println("---社团管理---\n");
        System.out.print("1.新建社团\n" +
                         "2.删除社团\n" +
                         "3.查看社团\n" +
                         "4.修改社团信息\n" +
                         "5.返回\n>");
    }

    public void run()
    {
        int instruction = -1;
        final int EXIT = 5;
        while (true)
        {
            showMenu();
            try {
                instruction = Integer.parseInt(in.nextLine());

                if(instruction == EXIT)
                    break;

                switch (instruction)
                {
                    case 1:
                        //新建社团
                        createClub();
                        break;
                    case 2:
                        //删除社团
                        deleteClub();
                        break;
                    case 3:
                        //查看社团
                        checkClub();
                        break;
                    case 4:
                        //修改社团信息
                        modifyClub(null);
                        break;
                    default:
                        throw new CustomerException("输入超限");
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private void modifyClub(User user)
    {
        int instruction = -1;
        final int EXIT = 0;
        try {
            System.out.println("输入社团ID(输入0返回)\n>");
            instruction = Integer.parseInt(in.nextLine());


            if(instruction == EXIT)
                return;

            Club club = clubDao.queryClubID(instruction);
            if(club == null)
            {
                System.out.println("社团不存在");
                return;
            }

            System.out.println(clubService.clubHead);
            System.out.println("1"+club.toString());
            modifyMenu(club);
            System.out.println("正在修改");
            clubDao.updateClub(club);
            System.out.println("修改成功!");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private void modifyMenu(Club club)
    {
        int instruction;
        final int EXIT = 5;
        while (true) {
            System.out.print("1.社团名\n" +
                    "2.社团类型\n" +
                    "3.社团简介\n" +
                    "4.社团社长\n"+
                    "5.返回\n");
            System.out.print("要更改哪一项信息\n>");
            try {
                instruction = Integer.parseInt(in.nextLine());
                if (instruction == EXIT)
                    return;
                switch (instruction) {
                    case 1:
                        //更改社团名称
                        System.out.print("输入新社团名称\n>");
                        String name = in.nextLine();
                        club.setClub_Name(name);
                        break;
                    case 2:
                        //更改社团类型
                        System.out.println("输入新社团类型\n>");
                        String type = in.nextLine();
                        club.setClub_Type(type);

                        break;
                    case 3:
                        //更改社团简介
                        System.out.println("输入新社团简介\n>");
                        String intro = in.nextLine();
                        club.setClub_Intro(intro);
                        break;
                    case 4:
                        //更改社长
                        System.out.println("输入新社长用户ID\n>");
                        int id = Integer.parseInt(in.nextLine());
                        User user = userDao.queryUserByID(id);
                        if(user == null)
                            System.out.println("用户不存在!");
                        else
                            club.setClub_ID(id);
                        break;
                    default:
                        throw new CustomerException("输入超限");
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }


    private void deleteClub()
    {
        int instruction;
        final int EXIT = -1;
        System.out.print("输入要删除的社团ID(输入-1返回)\n>");
        try {
            instruction = Integer.parseInt(in.nextLine());
            if(instruction == EXIT)
                return;
            Club club = clubDao.queryClubID(instruction);
            if(club!= null)
                clubDao.deleteClub(club);
            else
                System.out.println("社团不存在");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void searchClub()
    {
        String content = "输入要查找的社团名:模糊搜索\n(输入-1返回,输入0显示所有社团)\n>";
        String instruction;
        final String EXIT = "-1";
        final String ALL = "0";
        try {
            System.out.println(content);
            instruction = in.nextLine();
            if(instruction == EXIT)
                return;
            if(instruction != ALL)
                clubService.showSearchClub(instruction);
            else
                clubService.showAllClub();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private void createClub()
    {
        int instruction = -1;
        final int EXIT = 2;
        System.out.println("请依次输入你要创建的社团名称、类型与社团简介并单击回车。");
        System.out.println("名称：");
        String name=in.nextLine();
        System.out.println("类型：");
        String type=in.nextLine();
        System.out.println("简介：");
        String intro=in.nextLine();
        System.out.println("社长ID：");
        System.out.println("确认建社吗?(1.确认 2.返回)");

        try {
            instruction = Integer.parseInt(in.nextLine());
            if(instruction == EXIT)
                return;
            int id = Integer.parseInt(in.nextLine());
            clubService.setUid(id);
            clubService.applyToBuildClub(name, type, intro);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private void checkClub()
    {
        int instruction = -1;
        final int EXIT = 3;

        while(true)
        {
            System.out.print("1.查看社团信息\n" +
                    "2.查看社团举办的所有活动\n" +
                    "3.返回\n>");
            try {
                instruction = Integer.parseInt(in.nextLine());

                if(instruction == EXIT)
                    break;

                switch (instruction)
                {
                    case 1:
                        //查看社团
                        searchClub();
                        break;
                    case 2:
                        //todo 查看社团的所有活动
                        break;
                    default:
                        throw new CustomerException("输入超限");
                }
            }
            catch (Exception e)
            {

            }
        }

    }


}
