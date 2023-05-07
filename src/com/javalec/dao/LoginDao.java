package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.javalec.util.ShareVar;

public class LoginDao {
	// 데이터베이스 위치, 아이디, 비밀번호 연결하기
	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;
	
	// 사용자가 로그인 할 때 입력할 데이터 초기화.
	String userid;
	String userPassword;
	
	// Constructor
	public LoginDao() {
		// TODO Auto-generated constructor stub
	}
	
	/* DB에 아이디가 있는지 체크해줄 생성자 */
	public LoginDao(String userid) {
		super();
		this.userid = userid;
	}





	// 사용자가 로그인 할 때 받아올 데이터(id, pw) 생성자
	public LoginDao(String userid, String userPassword) {
		super();
		this.userid = userid;
		this.userPassword = userPassword;
	}
	
	
	
	
	public int existsUserID(String userid) {
		int count = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt = con.createStatement();
			
			String query = "select count(userid) from user where userid = '" + userid + "'";
			
			
	        ResultSet rs = stmt.executeQuery(query);
	        
	        while (rs.next()) {
	            count = rs.getInt(1); // count(userid) 결과 가져오기
	        }
			
		}catch(Exception e){
			
		}
		return count;
	}
	
	


	public boolean loginCheck(String userid, String password) { 		// 로그인 하려는 사람이 입력한 정보가 DB에 있는지 확인하는 메소드.
		boolean result = false;
		int count = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt = con.createStatement();
			
			String query = "select count(userName) from user where userid = '" + userid + "'" + " and userPassword = '" + password + "'";
			
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				count = rs.getInt(1);
			}
			if(count > 0) {
				result = true;
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return result; 		// 입력한 id,pw가 모두 일치하는 데이터가 DB에 있다 -> 그 사람의 id(혹은 이름) 반환.
		
	}

	
}
