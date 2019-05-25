package dao;

import bean.Evaluation_Activity;
import bean.Evaluation_Member;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

//评价的增删改查
public class EvaluationDao {

    //添加活动评价(根据活动id和)
    public void addEvaluationOfAct(int uid, int aid, String cont, int level) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "call addEvaluate_act(?,?,?,?)"; //存储过程
        queryRunner.execute(sql, uid, aid, cont, level); //调用
    }

    //添加社团人员评价(根据用户id和社团名字)
    public void addEvaluationOfMember(String mid, String clubName, String cont, int level) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "call addEvaluate_mem(?,?,?,?)"; //存储过程
        queryRunner.execute(sql, mid, clubName, cont, level);
    }

    //查询该活动的总评价条数
    public long totalEvaOfAct(int aid) {
        try {
            QueryRunner queryRunner = C3P0Util.getQueryRunner();
            String sql = "select count(*) from Evaluation_Activity where Activity_ID = ?;";
            return queryRunner.query(sql, new ScalarHandler<>(), aid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //查询活动的全部评价,时间降序
    public List<Evaluation_Activity> queryEvaluationOfAct(int aid, int currentPage, int pageSize) throws SQLException {
        int start = (currentPage - 1) * pageSize;
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select E.*, eq.User_ID, U.Name, A.Activity_Name\n" +
                "from Evaluation_Activity eq\n" +
                "         join Evaluation E on eq.Evaluation_ID = E.Evaluation_ID\n" +
                "         join Activity A on eq.Activity_ID = A.Activity_ID\n" +
                "         join User U on eq.User_ID = U.User_ID\n" +
                "where eq.Activity_ID = ?\n" +
                "order by Time desc\n" +
                "LIMIT ?,?;";
        return queryRunner.query(sql, new BeanListHandler<>(Evaluation_Activity.class), aid, start, pageSize);
    }

    //查询用户的社团评价条数
    public long totalEvaOfMember(int uid) {
        try {
            QueryRunner queryRunner = C3P0Util.getQueryRunner();
            String sql = "select count(*) from Evaluation_Member where Member_ID=?";
            return queryRunner.query(sql, new ScalarHandler<>(), uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //查询用户的所有社团评价,按页数来,时间降序
    public List<Evaluation_Member> queryEvaluationOfMember(int uid, int currentPage, int pageSize) throws SQLException {
        int start = (currentPage - 1) * pageSize;
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select E.*,em.Member_ID,U.Name,C.Club_Name\n" +
                "from Evaluation_Member em\n" +
                "         join Evaluation E on em.Evaluation_ID = E.Evaluation_ID\n" +
                "         join Club C on em.Club_ID = C.Club_ID\n" +
                "         join User U on em.Member_ID = U.User_ID\n" +
                "where em.Member_ID = ?\n" +
                "order by Time\n" +
                "LIMIT ?,?;";
        return queryRunner.query(sql, new BeanListHandler<>(Evaluation_Member.class), uid, start, pageSize);
    }
}