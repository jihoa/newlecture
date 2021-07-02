/**
 * 
 */
package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jiho-kim
 *
 * 신규개발
 */

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		String url = "jdbc:oracle:thin:@10.10.0.131:1521:M2";
		String sql = "SELECT * FROM NOTICE1 where id = ? ";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
			Connection con = DriverManager.getConnection(url, "cli", "cli1993");
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			
			rs.next();
			
			
			String title = rs.getString("TITLE");
			

			request.setAttribute("title", title);
			
			rs.close();
			st.close();
			con.close();						
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		//redirect
		//forward 전이방식.
		request.getRequestDispatcher("/notice/detail.jsp").forward(request, response);
		
	}

}
