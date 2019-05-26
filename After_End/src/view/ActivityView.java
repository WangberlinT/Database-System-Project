package view;

import util.CustomerException;

public class ActivityView extends View {

    private void activityBrowse() {
        //todo activityBrowse
        String prompt = "----------\n" +
                "当前没有活动\n" +
                "----------\n";
        String content = "1.选择查看详细信息\n" +
                "2.上一页\n" +
                "3.下一页\n" +
                "4.搜索\n" +
                "5.过滤器\n" +
                "6.我的活动" +
                "7.返回\n>";
        int instruction = -1;
        final int EXIT = 7;
        while (true) {
            //todo 获取所有活动
            if (true)//如果没有活动
                System.out.print(prompt);
            System.out.print(content);

            try {
                instruction = Integer.parseInt(in.nextLine());

                if (instruction == EXIT)
                    break;

                switch (instruction) {
                    case 1:
                        //todo 选择查看活动的详细信息
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
                    case 6:
                        //todo 我的活动
                        break;
                    default:
                        throw new CustomerException("输入超出范围");
                }
            } catch (Exception e) {
                //todo 异常处理
            }
        }
    }
}
