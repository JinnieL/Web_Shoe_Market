package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.javalec.util.ShareVar;
import com.mysql.cj.xdevapi.PreparableStatement;

public class MypageDao {

	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;

	String userId;
	String name;
	String password;
	String phone;
	String address;
	String email;
	
	
	public MypageDao() {
		// TODO Auto-generated constructor stub
	}
	
	private void deleteAction() {
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			String query = "delete from userinfo where seqno = ?";
			
			ps = conn_mysql.prepareStatement(query);
			ps.setString(1, userId);
			ps.executeUpdate();
			conn_mysql.close();
			JOptionPane.showMessageDialog(null, "탈퇴되었습니다.");			
		}catch(Exception e) {
			e.printStackTrace();
			}
	}

		

		public boolean updateAction() {
			PreparedStatement ps = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();
				
				String query = "update userinfo set name = ?, password = ?, phone = ?,";
				String query1 = "address=?, email=? where seqno = ?";
				
				ps = conn_mysql.prepareStatement(query + query1);
				ps.setString(1, name.trim());
				ps.setString(2, password.trim());
				ps.setString(3, phone.trim());
				ps.setString(4, address.trim());
				ps.setString(5, email.trim());
				
				
				ps.executeUpdate();
				conn_mysql.close();
							
			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
	}	
	
		}
