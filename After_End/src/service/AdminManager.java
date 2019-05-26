package service;

import bean.Admin;
import util.CustomerException;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AdminManager extends BaseService {
    //adminDao extends from BaseService

    static private String title = "No  管理员ID  密码";

    public AdminManager(Scanner in)
    {
        this.in = in;
    }

    private void showMenu()
    {
        String menucontent = "---管理员管理---\n" +
                             "1.新增管理员\n" +
                             "2.删除管理员\n" +
                             "3.查询所有管理员信息\n" +
                             "4.查询指定账户管理员信息\n" +
                             "5.返回\n>";
        System.out.print(menucontent);
    }

    public void run()
    {
        int instruction = -1;
        final int EXIT = 5;
        while(true)
        {
            showMenu();
            try {
                instruction = Integer.parseInt(in.nextLine());

                if(instruction == EXIT)
                    break;

                switch (instruction)
                {
                    case 1:
                        //新增管理员
                        addAdmin();
                        break;
                    case 2:
                        //todo 删除管理员

                        break;
                    case 3:
                        //查询所有管理员信息
                        searchAdmin();
                        break;
                    case 4:
                        //查询指定管理员信息
                        checkAdminByID();
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
    //新增管理员
    private void addAdmin()
    {
        System.out.print("输入新建管理员ID\n>");
        try {
            int id = Integer.parseInt(in.nextLine());
            //检查是否有重复
            if(adminDao.queryAdminByID(id) != null)
            {
                System.out.print("管理员账户已存在！\n");
                return;
            }
            System.out.print("输入新建管理员密码\n>");
            String password = in.nextLine();
            System.out.println("正在创建...");
            adminDao.insertAdmin(new Admin(id,password));
            System.out.println("创建成功!");
        }
        catch (Exception e)
        {
            System.out.println("数据库连接失败");
        }
    }
    //查询指定管理员信息
    private void checkAdminByID()
    {
        System.out.print("输入要查询的管理员ID\n>");
        try {
            int id = Integer.parseInt(in.nextLine());
            searchAdmin(id);
        }
        catch (Exception e)
        {
            System.out.println("查询失败！");
        }
    }

    //-------------------查询Admin---------------------
    //以id查询admin
    private void searchAdmin(int id) throws SQLException
    {

        Admin admin = adminDao.queryAdminByID(id);
        if(admin != null)
        {
            String info ="1"+"  "+ admin.getAdmin_Id()+"  "+admin.getPassword();
            System.out.println(title);
            System.out.println(info);
            System.out.print("按任意键返回\n>");
            in.nextLine();
        }
        else
        {
            System.out.println("没有查询到结果");
        }

    }
    //查询所有admin
    private void searchAdmin() throws SQLException
    {
        long total = adminDao.getTotalAdmin();
        if(queryNotValid(total)) return;
        int page = 1;
        long totalPage = (total - 1) / pageSize + 1;
        while (page <= totalPage) {
            List<Admin> list = adminDao.queryAllAdmin(page, pageSize);
            page = PrintPage(page, totalPage, title, list);
            if (page == 0) return;
        }
    }
}
