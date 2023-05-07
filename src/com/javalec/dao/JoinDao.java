package com.javalec.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.javalec.util.ShareVar;

public class JoinDao {


	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;
	
	String name;
	String password;
	String phone;
	String address;
	String email;
	
	public JoinDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	public int checkID(String insertID) {
		
		String query = "select count(userid) from user where userid = " + insertID;
		
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		int wkCount = rs.getInt(1);
		return wkCount;
				
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	
	
	
	
	
	
	
	
}
