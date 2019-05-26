package view;

import bean.Club;
import util.CustomerException;

public class ClubView extends View {

    public void run()
    {

    }

    public void myClub() {
        int instruction = -1;//第一级instruction
        int instruction_2 = -1;//第二级instruction
        final int EXIT = 4;
        int size = 0;
        while (true) {
            //todo 序号+展示所有加入的社团
            //例如：<1>篮球社 List<Club> clubs = user.getClub();
//
//            int size = clubs.size();
            if (size == 0)
                System.out.printf("-------------\n" +
                        "没有加入任何社团\n" +
                        "-------------\n");
//            for(int i = 1;i <= size;i ++)
//            {
//                System.out.printf("<%d>%s\n",i,clubs.get(i-1).getClub_Name());
//            }
            //设置中可以修改社团的排序方式(按时间排序还是按人数排序)
            System.out.printf("1.查看社团信息*\n" +
                    "2.创建社团*\n" +
                    "3.资产借用*\n" +
                    "4.返回\n" + ">");
            try {
                instruction = Integer.parseInt(in.nextLine());

                if (instruction == EXIT)
                    break;
                switch (instruction) {
                    case 1:
                        if (size == 0) {
                            System.out.println("没有可以查看的社团");
                            break;
                        }
                        System.out.printf("选择要查看的社团序号\n>");
                        instruction_2 = Integer.parseInt(in.nextLine());
                        if (instruction_2 > size || instruction_2 < 1)
                            throw new CustomerException("输入超出范围");
                        //正确输入
                        //todo 展示社团信息
//                        clubInfo(clubs.get(instruction_2));
                        break;
                    case 2:
                        //todo 创建社团
                        createClub();
                        break;
                    case 3:
                        //todo 资产借用
                        loanItem();
                        break;
                    default:
                        throw new CustomerException("输入超出范围");
                }
            } catch (Exception e) {
                //todo 异常处理
            }
        }
    }

        public void clubBrowse()
        {
            //todo clubBrowse
            String prompt = "----------\n" +
                    "当前没有社团\n" +
                    "----------\n";
            String content = "1.选择查看详细信息\n" +
                    "2.上一页\n" +
                    "3.下一页\n" +
                    "4.搜索\n" +
                    "5.过滤器\n" +
                    "6.返回\n>";
            int instruction = -1;
            final int EXIT = 6;
            while (true)
            {
                //todo 获取所有社团
                if(true)//如果没有活动
                    System.out.print(prompt);
                System.out.print(content);

                try {
                    instruction = Integer.parseInt(in.nextLine());

                    if(instruction == EXIT)
                        break;

                    switch (instruction)
                    {
                        case 1:
                            //todo 选择查看社团的详细信息
                            break;
                        case 2:
                            //todo 上一页
                            break;
                        case 3:
                            //todo 下一页
                            break;
                        case 4:
                            //todo 模糊搜索
                            break;
                        case 5:
                            //todo 过滤器
                            break;
                        default:
                            throw new CustomerException("输入超出范围");
                    }
                }
                catch (Exception e)
                {
                    //todo 异常处理
                }
            }

        }

    public void createClub()
    {
        final String[] promt = {"输入社团名称(输入0终止)\n>","输入社团类型(输入0终止)\n>","输入社团简介(输入0终止)\n>"};
        String[] name_type_intro = new String[3];
        String instruction;
//        System.out.println("正在创建社团申请...");
//        System.out.print("输入社团名称\n>");
//        //todo 合法性检查
//        String club_name = in.nextLine();
//        System.out.print("输入社团类型\n>");
//        String club_type = in.nextLine();
//        System.out.print("输入社团简介\n>");
//        String club_intro = in.nextLine();
        for(int i = 0;i < promt.length;i ++)
        {
            System.out.print(promt[i]);
            instruction = in.nextLine();
            if(instruction.equals("0")) {
                System.out.println("申请终止!");
                return;
            }
        }
        //todo 向管理员发送创建社团申请
        System.out.println("申请已发送！审核通过后社团将被创建");
    }

    public void loanItem()
    {

    }

    public void clubInfo(Club club)
    {

    }
}
