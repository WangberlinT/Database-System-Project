package view;
import bean.*;
import dao.AdminDao;
import service.*;
import util.CustomerException;


public class AdminView extends View {
    private Admin admin;
    private AdminDao adminDao;
    private AdminUserManager aum;
    private AdminManager am;

    public AdminView(int ID,String password)
    {
        admin = new Admin(ID,password);
        adminDao = new AdminDao();
        aum = new AdminUserManager();
        am = new AdminManager(in);
    }

    //Admin 主菜单
    public void displayMenu()
    {
        int instruction;
        final int EXIT = 6;

        while (true) {
            System.out.printf(
                            "Welcome Admin: %d\n"
                            +"-----------------\n"
                            +"1.修改密码\n"
                            +"2.管理社团\n"
                            +"3.管理用户\n"
                            +"4.管理活动\n"
                            +"5.管理员账号管理\n"
                            +"6.退出登陆\n>"
                    , admin.getAdmin_Id());

            try {
                instruction = Integer.parseInt(in.nextLine());

                if (instruction == EXIT)
                    break;

                switch (instruction) {
                    case 1:
                        //修改密码
                        changePasswordMenu();
                        break;
                    case 2:
                        //todo 管理社团

                        break;
                    case 3:
                        //管理用户
                        aum.run();
                        break;
                    case 4:
                        //todo 管理活动
                        break;
                    case 5:
                        //管理admin
                        am.run();
                        break;

                    default:
                        throw new CustomerException("Wrong input");
                }
            } catch (Exception e) {
                System.out.println("无效输入！");
            }
        }

    }
// todo change password
    public void changePasswordMenu()
    {
        //输入原密码如果相同则可以修改
        int instruction = -1;
        String inpassword;
        final int EXIT = 2;
        while(true)
        {
            System.out.printf("---更改用户: %d 的密码---\n"
                             +"确保以下操作为本人操作\n"
                             +"1.继续修改\n"
                             +"2.退回上一栏\n>", admin.getAdmin_Id());

            try {
                instruction = Integer.parseInt(in.nextLine());

                if(instruction == 1)
                {
                    System.out.print("输入原密码\n>");
                    inpassword = in.nextLine();
                    if(inpassword.equals(admin.getPassword()))
                    {
                        System.out.print("输入新密码\n>");
                        inpassword = in.nextLine();
                        admin.setPassword(inpassword);
                        System.out.println("正在同步...");
                        adminDao.updateAdmin(admin);
                        System.out.println("更改成功!");
                    }
                    else
                        System.out.println("原密码错误!");
                }
                else if(instruction == 2)
                    break;
                else
                    throw new CustomerException("输入超出范围");

            }
            catch (Exception e)
            {
                //todo 异常处理
            }


        }
    }



}
