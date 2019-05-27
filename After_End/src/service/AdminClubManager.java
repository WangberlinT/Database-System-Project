package service;

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
                        //todo 新建社团
                        break;
                    case 2:
                        //todo 删除社团
                        break;
                    case 3:
                        //todo 查看社团
                        checkClub();
                        break;
                    case 4:
                        //todo 修改社团信息
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

    private void checkClub()
    {
        int instruction = -1;
        final int EXIT = 4;

        while(true)
        {
            System.out.print("1.查看所有社团\n" +
                    "2.查看社团举办的所有活动\n" +
                    "3.搜索社团" +
                    "4.返回\n>");
            try {
                instruction = Integer.parseInt(in.nextLine());

                if(instruction == EXIT)
                    break;

                switch (instruction)
                {
                    case 1:
                        //查看所有社团
                        clubService.showAllClub();
                        break;
                    case 2:
                        //todo
                        break;
                    case 3:
                        //todo 搜索社团
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
