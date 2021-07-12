/**
 * 
 */
package com.newlecture.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newlecture.web.entity.Notice;

/**
 * @author jiho-kim
 *
 * 신규개발
 */
public class NoticeService {
	public List<Notice> getNoticeList(){
		return getNoticeList("title", "", 1);
	}
	public List<Notice> getNoticeList(int page){
		return getNoticeList("title", "", page);
	}
	public List<Notice> getNoticeList(String field, String query, int page){

		List<Notice> list = new ArrayList<>();
		
		String sql="SELECT   *\r\n" + 
				"  FROM   (SELECT   ROWNUM AS NUM, A.*\r\n" + 
				"            FROM   (  SELECT   *\r\n" + 
				"                        FROM   NOTICE1 WHERE "+field+" LIKE ? \r\n" + 
				"                    ORDER BY   REGDATE DESC) A) Z\r\n" + 
				" WHERE   NUM BETWEEN ? AND ?";
		
		
		// 1, 11, 21, 31 -> 1+(page-1)*10
		// 10,20, 30, 40 -> page*10

		
		String url = "jdbc:oracle:thin:@10.10.0.131:1521:M2";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con= DriverManager.getConnection(url, "cli", "cli1993");
			PreparedStatement st = con.prepareStatement(sql);
			System.out.println(sql);
			st.setString(1, "%" + query + "%");
			st.setInt(2, 1+(page-1)*10);
			st.setInt(3, page*10);
			ResultSet rs= st.executeQuery();

			 while(rs.next()){ 
			 	int id = rs.getInt("ID");
			 	String title=rs.getString("TITLE");
				String writerId=rs.getString("WRITER_ID");
				Date regdate=rs.getDate("REGDATE"); 
				String hit=rs.getString("HIT");
				String files=rs.getString("FILES");
				String content=rs.getString("CONTENT"); 

				Notice notice= new Notice(
						id,
						title,
						writerId,
						regdate,
						hit,
						files,
						content
						);
				list.add(notice);
			} 
			 	rs.close();
				st.close();
				con.close();

			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}	
	
	public int getNoticeCount() {
		return getNoticeCount("title", "");
	}
	
	public int getNoticeCount(String field, String query) {
		
		int count = 0;
		
		String sql = "";

		String url = "jdbc:oracle:thin:@10.10.0.131:1521:M2";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con= DriverManager.getConnection(url, "cli", "cli1993");
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs= st.executeQuery();

			count = rs.getInt("count");
			
			 	rs.close();
				st.close();
				con.close();

			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return count;
	}
	
	public Notice getNotice(int id) {
		String sql = "select * from notice1 where id = ? ";
		return null;
	}
	
	public Notice getNextNotice(int id) {
		String sql = "SELECT   *\r\n" + 
				"  FROM   NOTICE1\r\n" + 
				" WHERE   ID = (SELECT   ID\r\n" + 
				"                 FROM   NOTICE1\r\n" + 
				"                WHERE   REGDATE > (SELECT   REGDATE\r\n" + 
				"                                     FROM   NOTICE1\r\n" + 
				"                                    WHERE   ID = 3)\r\n" + 
				"                        AND ROWNUM = 1)";
		
		return null;
	}
	
	public Notice getPrevNotice(int id) {
		String sql = "SELECT   ID\r\n" + 
				"  FROM   (  SELECT   *\r\n" + 
				"              FROM   NOTICE1\r\n" + 
				"          ORDER BY   REGDATE DESC)\r\n" + 
				" WHERE   REGDATE < (SELECT   REGDATE\r\n" + 
				"                      FROM   NOTICE1\r\n" + 
				"                     WHERE   ID = 3)\r\n" + 
				"         AND ROWNUM = 1";
		return null;
	}	
	
}
