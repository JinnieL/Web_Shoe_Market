package com.javalec.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.javalec.util.ShareVar;

public class JoinDao {


	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;
	
	String userName;
	String userid;
	String userPassword;
	String userPhone;
	String userAddress;
	String userEmail;
	
	public JoinDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	public int checkID(String insertID) {
		
		String query = "select count(userid) from user where userid = " + "'" + insertID + "'";
		
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()) {
			int wkCount = rs.getInt(1);
			return wkCount;
		}
				
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	
	
	public boolean register(String userName, String userid, String userPassword, String userPhone, String userAddress, String userEmail) {
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement(); 		// 불러올 때 많이 사용
			
			// sql 문장 적어서 
			String query = "insert into user(userName, userid, userPassword, userPhone, userAddress, userEmail) values(?, ?, ?, ?, ?, ?)";
			
			ps = conn_mysql.prepareStatement(query);

			ps.setString(1, userName);
			ps.setString(2, userid);
			ps.setString(3, userPassword);
			ps.setString(4, userPhone);
			ps.setString(5, userAddress);
			ps.setString(6, userEmail);
			ps.executeUpdate();
			conn_mysql.close();
			return true;
			
		} catch (Exception e) {
			
		}
		return false;
		
		
	}
	
	
	
	
	
	
	
	
	
}
