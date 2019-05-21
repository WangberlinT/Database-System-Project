package dao;

import java.sql.SQLException;
import java.util.List;

import bean.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;


//社团信息的增删改查
public class ClubDao {
    private int pageSize = 10; //每页10条数据
    private long totalClub = getTotalClub();
    private long totalpage = getTotalPage();

    //获取总用户数量
    public long getTotalClub() {
        try {
            QueryRunner queryRunner = C3P0Util.getQueryRunner();
            String sql = "SELECT COUNT(*) from Club";
            return queryRunner.query(sql, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //总页数
    public long getTotalPage() {
        return (totalClub - 1) / pageSize + 1;
    }

    //查询所有社团
    public List<Club> queryAllClub(int currentPage) throws SQLException {
        int start = (currentPage - 1) * pageSize;
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select * from Club LIMIT ?,?";
        return queryRunner.query(sql, new BeanListHandler<>(Club.class), start, pageSize);
    }

    //注册社团
    public void addClub(Club club) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "insert into Club(Club_ID, Club_Name, Club_Type, Club_Intro, Club_Leader)" +
                "VALUES (?, ?, ?, ?, ?);";
        Object[] param = {club.getClub_ID(), club.getClub_Name(), club.getClub_Type(), club.getClub_Intro(), club.getClub_Leader()};
        queryRunner.update(sql, param);
    }

    //删除社团(有问题 没写完）
    public void deleteClub(Club club) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "delete from Club where Club_ID=?";
        Object[] param = {club.getClub_ID()};
        queryRunner.update(sql, param);
    }

    //更新社团信息
    public void updateClub(Club club) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "update Club set Club_Name=?, Club_Type=?, Club_Intro=?, Club_Leader=?  where Club_ID=?";
        Object[] param = {club.getClub_Name(), club.getClub_Type(), club.getClub_Intro(), club.getClub_Leader(), club.getClub_ID()};
        queryRunner.update(sql, param);
    }

    //通过社团名字查询社团
    public Club queryClub(String name) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select * from Club where Club_Name=?";
        return queryRunner.query(sql, new BeanHandler<>(Club.class), name);
    }
}