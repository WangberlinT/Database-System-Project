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
    private int pageSize = 10;  //每页10条数据

    //添加活动评价(根据活动id和)
    public void addEvalutionOfAct(int uid, int aid, String cont, int level) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "call addEvaluate_act(?,?,?,?)"; //存储过程
        queryRunner.execute(sql, uid, aid, cont, level); //调用
    }

    //添加社团人员评价(根据用户id和社团名字)
    public void addEvalutionOfMember(String mid, String clubName, String cont, int level) throws SQLException {
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

    //获取活动评价总页数
    public long totalPageOfAct(int aid) {
        return (totalEvaOfAct(aid) - 1) / pageSize + 1;
    }

    //查询活动的全部评价
    public List<Evaluation_Activity> queryEvaluationOfAct(int aid, int currentPage) throws SQLException {
        int start = (currentPage - 1) * pageSize;
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select *\n" +
                "from Evaluation_Activity eq\n" +
                "         join Evaluation E on eq.Evaluation_ID = E.Evaluation_ID\n" +
                "where Activity_ID=? LIMIT ?,?;";
        return queryRunner.query(sql, new BeanListHandler<>(Evaluation_Activity.class), aid, start, pageSize);
    }

    //重载查询,默认查询第一页 （感觉可有可无）
    public List<Evaluation_Activity> queryEvaluationOfAct(int aid) throws SQLException {
        return queryEvaluationOfAct(aid, 1);
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

    //获取活动评价总页数
    public long totalPageOfMember(int uid) {
        return (totalEvaOfMember(uid) - 1) / pageSize + 1;
    }

    //查询用户的所有社团评价,按页数来
    public List<Evaluation_Member> queryEvaluationOfMember(int uid, int currentPage) throws SQLException {
        int start = (currentPage - 1) * pageSize;
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select *\n" +
                "from Evaluation_Member em\n" +
                "         join Evaluation E on em.Evaluation_ID = E.Evaluation_ID\n" +
                "where em.Member_ID = ? LIMIT ?,?;";
        return queryRunner.query(sql, new BeanListHandler<>(Evaluation_Member.class), uid, start, pageSize);
    }

    //重载查询,默认查询第一页 （感觉可有可无）
    public List<Evaluation_Member> queryEvaluationOfMember(int uid) throws SQLException {
        return queryEvaluationOfMember(uid, 1);
    }
}