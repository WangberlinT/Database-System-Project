package dao;

import java.sql.*;
import java.util.*;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bean.*;
import bean.ItemLoan;
import bean.User;




class showitem{
	int num;
	int value;
	String name;
	showitem(int a,int b,String n){
		num=a;value=b;name=n;
	}
	
	public String toString() {
		return name+" "+value+" "+num;
	}
}


public class ItemDao {
	
	public void insertItem(int cid,String name,int value) throws SQLException {
		QueryRunner queryRunner = C3P0Util.getQueryRunner();
		String sql="call addItem(?,?,?);";
		Object[] param = {cid,name,value};
		queryRunner.update(sql, param);
				
	}

	public void borrowItem(int uid,int iid) throws SQLException {
		QueryRunner queryRunner = C3P0Util.getQueryRunner();
		String sql="call borrowItem(?,?);";
		Object[] param = {sql, uid,iid};
		queryRunner.update(sql, param);
	}
	
	public void borrowItem(int uid,String iid,int cid) throws SQLException {
		List<Item> rs=checkItem(cid,iid);
		if(rs.size()!=0) {
			borrowItem(uid,rs.get(0).getItemId());
		}else {
			System.out.println("no item valid now");
		}
		
	}
	

	
	/**which not loan*/
	public List<Item> checkItem(int cid) throws SQLException {
		QueryRunner queryRunner = C3P0Util.getQueryRunner();
		
		String sql="select Item_ID itemId,Item_Name itemName,Item_Value value,Loan_State itemState,Club_ID clubs"
				+ " from Item natural join Item_Belong "
				+ "where Club_ID=?  and Loan_State=0;";
			return queryRunner.query(sql, new BeanListHandler<>(Item.class),cid);
	}
	
	public List<Item> checkItem(int cid ,String name) throws SQLException {
		QueryRunner queryRunner = C3P0Util.getQueryRunner();
		
		String sql="select Item_ID itemId,Item_Name itemName,Item_Value value,Loan_State itemState,Club_ID clubs"
				+ " from Item natural join Item_Belong "
				+ "where Club_ID=? and Item_Name= ? and Loan_State=0;";
			return queryRunner.query(sql, new BeanListHandler<>(Item.class),cid,name);
	}
	
	public List<ItemLoan> checkItemBclub(int cid) throws SQLException{
		QueryRunner queryRunner = C3P0Util.getQueryRunner();
		String sql="select Item_ID,Item_Name,Item_Value,User_ID,Name,Start_Time,Phone_Number\n" + 
				"					 from Item natural join Item_Belong natural join Item_Loan natural join User\n" + 
				"					where Club_ID=? and Loan_State=1;";
		return queryRunner.query(sql,new BeanListHandler<>(ItemLoan.class),cid);
		
		
	}
	
	public List<itemshow> checkclubAll(int cid) throws SQLException {
		QueryRunner queryRunner = C3P0Util.getQueryRunner();
		String sql="select count(Item_Value and Item_Name) num, Item_Name name,Item_Value value from Item natural join Item_Belong "
				+ "where Club_ID=? group by Item_Name , Item_Value;";
		return queryRunner.query(sql,new BeanListHandler<>(itemshow.class),cid);
	}

	
	/**check item i borrow */
	public List<Item> checkBorrow(int uid,String name) throws SQLException {
		QueryRunner queryRunner = C3P0Util.getQueryRunner();
		String sql="select Item_ID itemId,Item_Name itemName,Item_Value value,Loan_State itemState,Club_ID clubs"
				+ " from Item natural join Item_Belong "
				+ "where Item_Name=? and User_ID=? and Return_Time is null;";
		return queryRunner.query(sql, new BeanListHandler<>(Item.class),name,uid);
		
	}
	
	public void huanItem(int uid,String name) throws SQLException {
		List<Item> rs=checkBorrow(uid,name);
		if(rs.size()!=0) {
			huanItem(uid,rs.get(0).getItemId());
		}else {
			System.out.println("you have no such thing to return");
		}
	}
	
	public void huanItem(int uid,int iid) throws SQLException {
		QueryRunner queryRunner = C3P0Util.getQueryRunner();
		String sql="call returnItem(?,?);";		
		Object[] param = {uid,iid};
		queryRunner.update(sql, param);
	}
	
	public void deleteItem(int iid) throws SQLException {
		QueryRunner queryRunner = C3P0Util.getQueryRunner();
		String sql=	"delete from Item_Belong where Item_ID = ?;\n";
		Object[] param = {iid};
		queryRunner.update(sql, param);
		sql="delete from Item_Loan where Item_ID = ?;";
		queryRunner.update(sql, param);
		 sql="delete from Item where Item_ID = ?;\n";
		queryRunner.update(sql, param);
	}

	public static void main(String[] args) throws SQLException
    {
    	  ItemDao itd=new ItemDao();
          itd.insertItem(2, "newbee", 100000);
       
        System.out.println("over");

    }
	
}
