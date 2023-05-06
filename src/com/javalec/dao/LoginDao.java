package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.javalec.util.ShareVar;

public class LoginDao {
	// 데이터베이스 위치, 아이디, 비밀번호 연결하기
	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;
	
	// 사용자가 로그인 할 때 입력할 데이터 초기화.
	String id;
	String pw;
	
	// Constructor
	public LoginDao() {
		// TODO Auto-generated constructor stub
	}
	
	// 사용자가 로그인 할 때 받아올 데이터(id, pw) 생성자
	public LoginDao(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}
	
	
	
	public String loginCheck() { 		// 로그인 하려는 사람이 입력한 정보가 DB에 있는지 확인하는 메소드.
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			String query = "select userName from user where userId = ? and userPassword = ? ";
			
			ps = conn_mysql.prepareStatement(query) ;
			
			ps.setString(1, id.trim()); 		// 사용자가 입력한 id가 db에 있는지 검색 조건으로 넣기
			ps.setString(2, pw.trim());			// 사용자가 입력한 pw가 db에 있는지 검색 조건으로 넣기
			
			ps.executeUpdate();
			conn_mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return id; 		// 입력한 id,pw가 모두 일치하는 데이터가 DB에 있다 -> 그 사람의 id(혹은 이름) 반환.
		
	}

	
}
