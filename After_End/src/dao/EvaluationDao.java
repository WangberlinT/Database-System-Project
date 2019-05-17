package dao;

import java.sql.SQLException;

//评价的增删改查
public class EvaluationDao {

    //添加活动评价
    void addEvalution_act(int toA, int frm, String cont, int level) {
        String sql = "call addEvaluate_act(?,?,?,?)"; //存储过程
    }

    //添加社团人员评价
    void addEvalution_mem(String memName, String clubName, String connt, int level) {
        String sql = "call addEvaluate_mem(?,?,?,?)"; //存储过程
    }
}
