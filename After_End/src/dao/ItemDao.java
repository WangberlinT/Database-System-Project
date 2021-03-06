package dao;

import java.sql.*;
import java.util.*;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import bean.*;
import bean.ItemLoan;

public class ItemDao {

    // 向指定社团插入道具
    public void insertItem(int cid, String name, int value) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "call addItem(?,?,?);";
        Object[] param = {cid, name, value};
        queryRunner.update(sql, param);
    }

    /**
     * 用户借物品
     */
    public void borrowItem(int uid, int iid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "call borrowItem(?,?);";
        Object[] param = {uid, iid};
        queryRunner.update(sql, param);
        System.out.println("借用成功");
    }

    /**
     * 用户解物品
     */
    public void borrowItem(int uid, String iid, int cid) throws SQLException {
        List<Item> rs = checkItem(cid, iid);
        if (rs.size() != 0) {
            borrowItem(uid, rs.get(0).getItem_ID());

        } else {
            System.out.println("现在没有所需物品，请下次再来");
        }
    }

    /**
     * 查询一个社团未被借用的item
     * which not loan
     */
    public List<Item> checknoBorrowItem(int cid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();

        String sql = "select Item_ID itemId,Item_Name itemName,Item_Value value,Loan_State itemState,Club_ID clubs"
                + " from Item natural join Item_Belong "
                + "where Club_ID=?  and Loan_State=0;";
        return queryRunner.query(sql, new BeanListHandler<>(Item.class), cid);
    }

    /**
     * 查询一个社团未被借用的item
     * which not loan
     */
    public List<Item> checkItem(int cid, String name) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select Item_ID itemId,Item_Name itemName,Item_Value value,Loan_State itemState,Club_ID clubs"
                + " from Item natural join Item_Belong "
                + "where Club_ID=? and Item_Name= ? and Loan_State=0;";
        return queryRunner.query(sql, new BeanListHandler<>(Item.class), cid, name);
    }

    /**
     * 查询一个社团被借用的item
     */
    public List<ItemLoan> checkItemBclub(int cid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select Item_ID,Item_Name,Item_Value,User_ID,Name,Start_Time,Phone_Number\n" +
                "					 from Item natural join Item_Belong natural join Item_Loan natural join User\n" +
                "					where Club_ID=? and Loan_State=1;";
        return queryRunner.query(sql, new BeanListHandler<>(ItemLoan.class), cid);


    }

    public List<Item> checkItemClub(int cid, int currentPage) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        int start = (currentPage - 1) * 10;
        String sql = "select Item_ID,Item_Name,Item_Value\n" +
                "					 from Item natural join Item_Belong \n" +
                "					where Club_ID=? LIMIT ?,?;";
        return queryRunner.query(sql, new BeanListHandler<>(Item.class), cid, start, 10);
    }

    public long checkItemClubNum(int cid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select COUNT(*)\n" +
                "					 from Item natural join Item_Belong \n" +
                "					where Club_ID=? ;";
        return queryRunner.query(sql, new ScalarHandler<>(), cid);
    }

    public List<ItemLoan> checkItemBclubPages(int cid, int page) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        int start = (page - 1) * 10;
        String sql = "select Item_ID,Item_Name,Item_Value,User_ID,Name,Start_Time,Phone_Number\n" +
                "					 from Item natural join Item_Belong natural join Item_Loan natural join User\n" +
                "					where Club_ID=? and Loan_State=1 LIMIT ?,?;";
        return queryRunner.query(sql, new BeanListHandler<>(ItemLoan.class), cid, start, 10);
    }

    public long checkItemBNum(int cid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select COUNT(*)" +
                "from Item natural join Item_Belong\n" +
                "where Club_ID=? and Loan_State=1;";
        return queryRunner.query(sql, new ScalarHandler<>(), cid);
    }

    /**
     * 简洁查看社团内所有物品（只显示数量）
     */
    public List<ItemShow> checkclubAll(int cid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select count(Item_Value and Item_Name) num, Item_Name name,Item_Value value from Item natural join Item_Belong "
                + "where Club_ID=? group by Item_Name , Item_Value;";
        return queryRunner.query(sql, new BeanListHandler<>(ItemShow.class), cid);
    }


    /**
     * 查看用户借了什么
     */
    public List<Item> checkBorrow(int uid, String name, int cid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "select Item_ID itemId,Item_Name itemName,Item_Value value,Loan_State itemState,Club_ID clubs"
                + " from Item natural join Item_Belong natural join Item_Loan "
                + "where Item_Name=? and User_ID=? and Return_Time is null and Club_ID=?;";
        return queryRunner.query(sql, new BeanListHandler<>(Item.class), name, uid, cid);
    }

    /**
     * 还道具
     */
    public void returnItem(int uid, String name, int cid) throws SQLException {
        List<Item> rs = checkBorrow(uid, name, cid);
        if (rs.size() != 0) {
            returnItem(uid, rs.get(0).getItem_ID());
        } else {
            System.out.println("you have no such thing to return");
        }
    }

    /**
     * 还道具
     */
    public void returnItem(int uid, int iid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "call returnItem(?,?);";
        Object[] param = {uid, iid};
        queryRunner.update(sql, param);
    }

    /**
     * 删除道具
     */
    public void deleteItem(int iid) throws SQLException {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        String sql = "delete from Item where Item_ID = ?;\n";
        queryRunner.update(sql, iid);
    }
}
