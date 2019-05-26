package service;

import bean.User;
import util.CustomerException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserService extends BaseService {
    private User user;
    private String head = "No  用户ID     用户名     性别     专业     生日     住址     联系方式";

    //构造函数
    public UserService(User userO) {
        user = userO;
    }

    UserService() {
    }

    //设置user
    public void setUser(User user) {
        this.user = user;
    }

    //修改个人信息：
    //换名字
    private void nameModify(String newName) {
        if (newName.length() <= 20)
            user.setName(newName);
        else
            System.out.println("更改失败！输入字符串长度大于20！");
    }

    //换性别
    private void sexModify(String newSex) {
        if (newSex.equals(User.GENDER[0]) || newSex.equals(User.GENDER[1]))
            user.setSex(newSex);
        else
            System.out.println("无效的性别设置");
    }

    //修改生日
    private void bornModify(String newBorn) {
        //todo 检查日期格式
        Date date = fu.stringToDate(newBorn);
        user.setBorn(date);
        System.out.println("生日修改成功!");
    }

    //修改专业
    private void majorModify(String newMajor) {
        //todo 检查输入合法
        user.setMajor(newMajor);
        System.out.println("专业修改成功!");
    }

    //修改地址
    private void addressModify(String newAddress) {
        user.setAddress(newAddress);
        System.out.println("地址修改成功!");
    }

    //修改电话
    private void phoneNumberModify(String newPhone) {
        user.setPhone_Number(newPhone);
        System.out.println("电话号码修改成功!");
    }

    //更新用户信息，基于上述修改
    public void updateInfo() throws SQLException {
        userDao.updateUser(user);
    }

    //--------------管理员查询User--------------------------------------------------
    //查询所有
    public void searchUserAdmin() throws SQLException {
        String title = "No  用户ID     密码     用户名     性别     专业     生日     住址     联系方式";
        int page = 1;
        long total = userDao.getTotalUser();
        long totalPage = (total - 1) / pageSize + 1;
        if (queryNotValid(total)) return;
        while (page <= totalPage) {
            List<User> list = userDao.queryAllUser(page, pageSize);
            //将User 所有信息显示出来,用String list存
            List<String> stringList = new ArrayList<>();
            for (User temp : list) {
                String info = temp.getUser_ID() + "  " + temp.getPassword() + "  " + temp.getName() + "  " + temp.getSex() + "  " + temp.getBorn() + "  " + temp.getMajor() + "  " + temp.getAddress() + "  " + temp.getPhone_Number();
                stringList.add(info);
            }
            page = PrintPage(page, totalPage, title, stringList);
            if (page == 0) return;
        }
    }

    //以id查询
    public void searchUserAdmin(int uid) throws SQLException {
        String title = "No  用户ID     密码     用户名     性别     专业     生日     住址     联系方式";
        User temp = userDao.queryUserByID(uid);
        if (temp != null) {
            String info = temp.getUser_ID() + "  " + temp.getPassword() + "  " + temp.getName() + "  " + temp.getSex() + "  " + temp.getBorn() + "  " + temp.getMajor() + "  " + temp.getAddress() + "  " + temp.getPhone_Number();
            System.out.println(formatter.format(title));
            System.out.println(formatter.format(info));
        } else {
            System.out.println("没有查询到结果");
        }
    }

    //以姓名查询
    public void searchUserAdmin(String name) throws SQLException {
        String title = "No  用户ID     密码     用户名     性别     专业     生日     住址     联系方式";
        long total = userDao.getTotalUserByName(name);
        if (queryNotValid(total)) return;
        int page = 1;
        long totalPage = (total - 1) / pageSize + 1;
        while (page <= totalPage) {
            List<User> list = userDao.queryUserByName(name, page, pageSize);
            List<String> stringList = new ArrayList<>();
            for (User temp : list) {
                String info = temp.getUser_ID() + "  " + temp.getPassword() + "  " + temp.getName() + "  " + temp.getSex() + "  " + temp.getBorn() + "  " + temp.getMajor() + "  " + temp.getAddress() + "  " + temp.getPhone_Number();
                stringList.add(info);
            }
            page = PrintPage(page, totalPage, title, stringList);
            if (page == 0) return;
        }
    }

    //-----------------------------------------------------------------------------

    //查询/搜索 用户

    //默认查全部
    public void searchUser() throws SQLException {
        long total = userDao.getTotalUser();
        if (queryNotValid(total)) return;
        int page = 1;
        long totalPage = (total - 1) / pageSize + 1;
        while (page <= totalPage) {
            List<User> list = userDao.queryAllUser(page, pageSize);
            page = PrintPage(page, totalPage, head, list);
            if (page == 0) return;
        }
    }

    //通过用户名
    public void searchUser(String name) throws SQLException {
        long total = userDao.getTotalUserByName(name);
        if (queryNotValid(total)) return;
        int page = 1;
        long totalPage = (total - 1) / pageSize + 1;
        while (page <= totalPage) {
            List<User> list = userDao.queryUserByName(name, page, pageSize);
            page = PrintPage(page, totalPage, head, list);
            if (page == 0) return;
        }
    }

    //通过id(重载)
    public void serachUser(int uid) throws SQLException {
        User u = userDao.queryUserByID(uid);
        if (u != null) {
            System.out.println(formatter.format(head));
            System.out.println(formatter.format(u.toString()));
        } else {
            System.out.println("没有查询到结果");
        }
    }

    //------消息盒子--------\\

    //检查数据库中有没有新的发给本对象的申请或者公告
    public long checkNews(int uid) {
        try {
            return announcementDao.unReadAnnouncement(uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //检查数据库中7天内新活动数量
    public long checkNewActivities() {
        return activityDao.totalActivityForAllweek();
    }

    //---------修改信息-----------

    //修改信息(管理员也可以使用)
    public void modifyInformation() {
        int instruction = -1;
        final int EXIT = 0;
        while (true) {
            System.out.print("要修改哪一项信息?(输入0返回上页)\n>");
            try {
                instruction = Integer.parseInt(in.nextLine());

                if (instruction == EXIT) {
                    System.out.println("正在同步...");
                    userDao.updateUser(user);
                    System.out.println("同步成功!");
                    return;
                }
                switch (instruction) {
                    case 1:
                        System.out.print("输入新的姓名>");
                        String newName = in.nextLine();
                        nameModify(newName);
                        break;
                    case 2:
                        System.out.print("输入新的性别\n>");
                        String newSex = in.nextLine();
                        sexModify(newSex);
                        break;
                    case 3:
                        System.out.print("输入新的生日格式:年-月-日\n例:1990-05-08\n>");
                        String newDate = in.nextLine();
                        bornModify(newDate);
                        break;
                    case 4:
                        System.out.print("输入新的专业\n>");
                        String newMajor = in.nextLine();
                        majorModify(newMajor);
                        break;
                    case 5:
                        System.out.print("输入新的地址\n>");
                        String address = in.nextLine();
                        addressModify(address);
                        break;
                    case 6:
                        System.out.print("输入新的电话号码\n>");
                        String newPhone = in.nextLine();
                        phoneNumberModify(newPhone);
                        break;
                    default:
                        throw new CustomerException("Invalid input");
                }
            } catch (Exception e) {
                if (e instanceof SQLException) {
                    System.out.println("数据库连接异常！修改失败");
                    //todo 恢复数据
                } else
                    System.out.println("无效输入！");

            }
        }
    }

}
