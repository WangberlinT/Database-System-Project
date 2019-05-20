package dao;

import bean.Announcement;
import bean.Club;
import bean.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


class sanno{
	String club;
	String title;
	String content;
	Timestamp date;
	sanno(String c,String t,String con,Timestamp d){
		club=c;content=con;title=t;date=d;
	}
	public String toString() {
    	return "From "+club+" "+title+": "+content+" ("+date.toString()+")";
    }
}

class AnnouncementDao extends BaseDao {

    List<Announcement> show_Anno(int uid) {
        List<Announcement> list = new ArrayList<>();
//            getCon();
        String sql = "select a.Announcement_ID, C.Club_Name, a.Title, a.Content, a.Time, U.Name\n" +
                "from Announcement a\n" +
                "         join Club C on a.Club_ID = C.Club_ID\n" +
                "         join Work w on C.Club_ID = w.Club_ID\n" +
                "         join User U on a.Publisher = U.User_ID\n" +
                "where w.User_ID = ?;";
//            exeQuery(sql, uid);
        //鍙幏鍙栫ぞ鍥㈠悕绉板拰鍙戝竷浜哄悕瀛�
        return list;
    }
    
    public void insertAnno(int cid,int uid,String content,String title) {
    	String sql="insert Announcement ( Content, Time, Title, Club_ID, Publisher)"
    			+ " VALUES (?,now(),?,?,?);";
    	super.exeUpdate(sql, content,title,cid,uid);
    	
    }
    
    public void deleteAnno(String title,int cid,int uid) {
    	String sql="delete from Announcement where Title=? and Club_ID=? and Publisher=?;";
    	super.exeUpdate(sql, title,cid,uid);
    }
    
    /**String pre is the  name of announcement before change.
     * String choose is the part you want to change.
     * input title to change tile
     * input content to change content */
    public void changeAnno(String pre,String choose,String after,int uid,int cid) {
    	String sql="";
    	if(choose.equals("title")) {
    		sql="update Announcement set title=? where "
    				+ "Title=? and Publisher=? and Club_ID=?;";
    		super.exeUpdate(sql,after,pre,uid,cid);
    	}else if(choose.equals("content")) {
    		sql="update Announcement set content=? where "
    				+ "Title=? and Publisher=? and Club_ID=?;";
    		super.exeUpdate(sql,after,pre,uid,cid);
    	}else {
    		System.out.println("invalid input of choose, please input title or content");
    	}
    }

    public List<sanno> getAnnounce(ResultSet rs) throws SQLException{
    	List<sanno> san=new ArrayList<sanno>();
    	while(rs.next()) {
    		san.add(new sanno(rs.getString("Club_Name"),rs.getString("Title"),rs.getString("Content"),
    				rs.getTimestamp("Time")));
    	}
    	return san;
    }
    
    public List<sanno> checkmyAnno(int uid) throws SQLException{
    	String sql="select Club_Name,Title,Content,Time from Announcement natural join Club "
    			+ "where Publisher=? order by Time desc;";
    	super.exeQuery(sql, uid);
    	return getAnnounce(super.rs);
    	
    }
    
    public List<sanno> checkAnnoMonth() throws SQLException{
    	String sql="select Announcement_ID, Content, Time, Title, Club_Name, Publisher from Announcement natural join Club "
    			+ "where Time>=now()-000001000000 order by Time desc;";
    	super.exeQuery(sql);
    	return getAnnounce(super.rs);
    }
    public List<sanno> checkClubAnno(int cid) throws SQLException{
    	String sql="select Club_Name,Title,Content,Time from Announcement natural join Club "
    			+ "where Club_ID=? order by Time desc;";
    	super.exeQuery(sql,cid);
    	return getAnnounce(super.rs);
    }
    
}
