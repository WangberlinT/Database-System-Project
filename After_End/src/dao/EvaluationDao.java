package dao;

import bean.Evaluation_Activity;
import bean.Evaluation_Member;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

//评价的增删改查
public class EvaluationDao {

    //添加活动评价
    void addEvalutionOfAct(int uid, int aid, String cont, int level) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "call addEvaluate_act(?,?,?,?)"; //存储过程
        queryRunner.execute(sql, uid, aid, cont, level); //调用
    }

    //添加社团人员评价(根据用户id和社团名字)
    void addEvalutionOfMember(String mid, String clubName, String cont, int level) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "call addEvaluate_mem(?,?,?,?)"; //存储过程
        queryRunner.execute(sql, mid, clubName, cont, level);
    }

    //查询活动的全部评价
    List<Evaluation_Activity> queryEvaluationOfAct(int aid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select *\n" +
                "from Evaluation_Activity eq\n" +
                "         join Evaluation E on eq.Evaluation_ID = E.Evaluation_ID\n" +
                "where Activity_ID=?;";
        return queryRunner.query(sql, new BeanListHandler<>(Evaluation_Activity.class), aid);
    }

    //查询用户的所有社团评价
    List<Evaluation_Member> queryEvaluationOfMember(int uid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select *\n" +
                "from Evaluation_Member em\n" +
                "         join Evaluation E on em.Evaluation_ID = E.Evaluation_ID\n" +
                "where em.Member_ID = ?;";
        return queryRunner.query(sql, new BeanListHandler<>(Evaluation_Member.class), uid);
    }
}
