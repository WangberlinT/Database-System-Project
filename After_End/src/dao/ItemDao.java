package dao;

import java.sql.*;
import java.util.*;

import bean.Item;




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

//璧勪骇淇℃伅鐨勫鍒犳敼鏌�
public class ItemDao extends BaseDao {
	
	public void insertItem(int cid,String name,int value) {
		String sql="call addItem(?,?,?);";
		super.exeUpdate(sql, cid,name,value);
				
	}

	public void borrowItem(int uid,int iid) {
		String sql="call borrowItem(?,?);";
		super.exeUpdate(sql, uid,iid);
	}
	
	public void borrowItem(int uid,String iid,int cid) throws SQLException {
		ResultSet rs=checkItem(iid,cid,0);
		if(rs.next()) {
			borrowItem(uid,rs.getInt("Item_ID"));
		}else {
			System.out.println("no item valid now");
		}
		
	}
	

	private ResultSet iexeQuery(String sql, Object... objects) throws SQLException {
      
            ps = connection.prepareStatement(sql);
            // 濡傛灉浼犲叆鐨勫弬鏁颁笉涓虹┖锛岄亶鍘嗭紝娣诲姞鍙傛暟
            if (objects != null && objects.length > 0) {
                for (int i = 0; i < objects.length; i++) {
                    ps.setObject(i + 1, objects[i]);
                }
            }
            return ps.executeQuery();
       
		
    }
	
	/*loan=0 means valid ,loan=1 means borrowed*/
	public ResultSet checkItem(String name,int cid,int loan) throws SQLException {
		
		String sql="select Item_ID,Item_Name,Item_Value,Loan_State,Club_ID"
				+ " from Item natural join Item_Belong "
				+ "where Club_ID=? and Item_Name='"+name+"' and Loan_State=?;";
		return  rs=iexeQuery(sql,cid,loan);
		
	}
	
	
	
	public List<showitem> checkclubAll(int cid) throws SQLException {
		String sql="select count(Item_Value and Item_Name) cnt, Item_Name name,Item_Value val from Item natural join Item_Belong "
				+ "where Club_ID=? group by Item_Name , Item_Value;";
		ResultSet rs= iexeQuery(sql, cid);
		List<showitem> sit=new LinkedList<showitem>();
		while(rs.next()) {
			showitem it=new showitem(rs.getInt("cnt"),rs.getInt("val"),rs.getString("name"));
			sit.add(it);
		}
		return sit;
	}
	
	public ResultSet checkBorrow(int uid,String name) throws SQLException {
		String sql="select Item_ID\n" + 
				"from Item_Loan natural join Item where "
				+ "Item_Name=? and User_ID=? and Return_Time is null;";
		return iexeQuery(sql, name,uid);
	}
	
	public void huanItem(int uid,String name) throws SQLException {
		ResultSet rs=checkBorrow(uid,name);
		if(rs.next()) {
			huanItem(uid,rs.getInt("Item_ID"));
		}else {
			System.out.println("you have no such thing to return");
		}
	}
	
	public void huanItem(int uid,int iid) {
		String sql="call returnItem(?,?);";
		super.exeUpdate(sql, uid,iid);
	}
	
	public void deleteItem(int iid) {
		
		String sql=	"delete from Item_Belong where Item_ID = ?;\n";
				
		super.exeUpdate(sql,iid);
		sql="delete from Item_Loan where Item_ID = ?;";
		super.exeUpdate(sql, iid);
		 sql="delete from Item where Item_ID = ?;\n";
		super.exeUpdate(sql,iid);
	}

	
	
}
