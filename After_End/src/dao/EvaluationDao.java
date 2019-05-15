package dao;

import java.sql.SQLException;

//评价的增删改查
public class EvaluationDao extends BaseDao {

    //添加活动评价
    void addEvalution_act(int toA, int frm, String cont, int level) {
        try {
            getCon();
            String sql = "call addEvaluate_act(?,?,?,?)"; //存储过程
            exeProcedure(sql, toA, frm, cont, level);
        } catch (SQLException e) {
            System.out.println("添加评价失败");
        } finally {
            closeAll();
        }
    }
    //添加社团人员评价
    void addEvalution_mem(String memName, String clubName, String connt, int level) {
        try {
            getCon();
            String sql = "call addEvaluate_mem(?,?,?,?)"; //存储过程
            exeProcedure(sql, memName, clubName, connt, level);
        } catch (SQLException e) {
            System.out.println("添加人员评价失败");
        } finally {
            closeAll();
        }
    }
}
