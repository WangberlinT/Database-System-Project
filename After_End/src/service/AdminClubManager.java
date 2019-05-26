package service;

import util.CustomerException;

public class AdminClubManager extends BaseService {

    public AdminClubManager()
    {

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


}
