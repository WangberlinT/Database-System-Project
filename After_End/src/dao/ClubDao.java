package dao;

import java.sql.SQLException;
import java.util.List;

import bean.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;


//社团信息的增删改查
public class ClubDao {

    //查询所有社团
    public List<Club> queryAllClub() throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select * from Club";
        return queryRunner.query(sql, new BeanListHandler<>(Club.class));

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
        String sql= "delete from Club where Club_ID=?";
        Object[] param = {club.getClub_ID()};
        queryRunner.update(sql, param);
    }

    //更新社团信息
    public void updateClub(Club club) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "update Club set Club_Name=?, Club_Type=?, Club_Intro=?, Club_Leader=?  where Club_ID=?" ;
        Object[] param = {club.getClub_Name(), club.getClub_Type(), club.getClub_Intro(), club.getClub_Leader(),club.getClub_ID()};
        queryRunner.update(sql, param);
    }

    //通过社团名字查询社团
    public Club queryClub(String name) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select * from Club where Club_Name=?";
        return queryRunner.query(sql, new BeanHandler<>(Club.class), name);
    }
}