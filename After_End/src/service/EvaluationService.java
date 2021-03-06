package service;

import bean.Evaluation_Activity;
import bean.Evaluation_Member;

import java.sql.SQLException;
import java.util.List;

public class EvaluationService extends BaseService {

    //添加活动的评价
    public void addEvaluationOfAct(int uid, int aid, String cont, int level) throws SQLException {
        evaluationDao.addEvaluationOfAct(uid, aid, cont, level);
    }

    //添加社团人员评价(根据用户id和社团名字)
    public void addEvalutionOfMember(int mid, String clubName, String cont, int level) throws SQLException {
        evaluationDao.addEvaluationOfMember(mid, clubName, cont, level);
    }

    //查询活动的全部评价,时间降序
    public void queryEvaluationOfAct(int aid) throws SQLException {
        long total = evaluationDao.totalEvaOfAct(aid);
        if (queryNotValid(total)) return;
        int page = 1;
        long totalPage = (total - 1) / pageSize + 1;
        String head = "评价ID  活动     评价人        评价内容       评价等级   评价时间";
        while (page <= totalPage) {
            List<Evaluation_Activity> list = evaluationDao.queryEvaluationOfAct(aid, page, pageSize);
            page = PrintPage(page, totalPage, head, list);
            if (page == 0) return;
        }
    }

    //查询用户的所有社团评价,时间降序
    public void queryEvaluationOfMember(int uid) throws SQLException {
        long total = evaluationDao.totalEvaOfMember(uid);
        if (queryNotValid(total)) return;
        int page = 1;
        long totalPage = (total - 1) / pageSize + 1;
        String head = "No 评价ID  用户ID     用户名     来自社团        评价内容       评价等级   评价时间";
        while (page <= totalPage) {
            List<Evaluation_Member> list = evaluationDao.queryEvaluationOfMember(uid, page, pageSize);
            page = PrintPage(page, totalPage, head, list);
            if (page == 0) return;
        }
    }
}
