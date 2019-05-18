package dao;

import java.sql.SQLException;
import bean.*;
import org.apache.commons.dbutils.QueryRunner;


//社团信息的增删改查
public class ClubDao {
    public void addclub(Club club) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDatasource());
        String sql = "insert into Club(Club_ID, Club_Name, Club_Type, Club_Intro, Club_Leader)" +
                "VALUES (?, ?, ?, ?, ?);";
        Object[] param = {club.getClub_ID(), club.getClub_Name(), club.getClub_Type(), club.getClub_Intro(), club.getClub_Leader()};
        queryRunner.update(sql, param);
    }


    public static void main(String[] args) throws SQLException {
        ClubDao c1 = new ClubDao();
        Club a = new Club("篮球社", "体育", "社长第一名", 11711613);
        c1.addclub(a);
    }
}