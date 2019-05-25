package service;

import bean.User;
import dao.UserDao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UserService extends BaseService {
    private static UserDao userDao = new UserDao();
    private User user;
    private String head = "No  用户ID     用户名     性别     专业     生日     住址     联系方式";

    //构造函数
    UserService(Scanner inO, User userO) {
        in = inO;
        user = userO;
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

    //查询/搜索 用户

    //默认查全部
    public void searchUser(Scanner in) throws SQLException {
        int page = 1;
        long total = userDao.getTotalUser();
        long totalPage = (total - 1) / pageSize + 1;
        if (queryNotValid(total)) return;
        while (page <= totalPage) {
            List<User> list = userDao.queryAllUser(page, pageSize);
            page = PrintPage(page, totalPage, head, list);
            if (page == 0) return;
        }
    }

    //通过用户名
    public void searchUser(String name, Scanner in) throws SQLException {
        int page = 1;
        long total = userDao.getTotalUserByName(name);
        long totalPage = (total - 1) / pageSize + 1;
        if (queryNotValid(total)) return;
        System.out.println(formatter.format(head));
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

    //消息盒子：
    //检查新信息
    public long checkNews() {
        int news = 0;
        //todo 检查数据库中有没有新的发给本对象的申请或者公告

        return news;
    }

    //检查新活动
    public boolean checkNewActivities() {
        boolean has = false;
        //todo 检查数据库中是否有新活动
//        hasNewActivities = true;//同checkNews
        return has;
    }
}
