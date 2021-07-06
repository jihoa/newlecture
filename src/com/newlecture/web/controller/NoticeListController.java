package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Notice> list = new ArrayList<>();
		
		String url = "jdbc:oracle:thin:@10.10.0.131:1521:M2";
		String sql = "SELECT * FROM NOTICE1";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con= DriverManager.getConnection(url, "cli", "cli1993");
			Statement st = con.createStatement();
			ResultSet rs= st.executeQuery(sql);

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
		request.setAttribute("list", list);
		
		request
		.getRequestDispatcher("/WEB-INF/view/notice/list.jsp")
		.forward(request, response);
		
	}
}
