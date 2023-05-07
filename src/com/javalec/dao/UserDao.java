package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.javalec.dto.ProductDto;
import com.javalec.dto.UserDto;
import com.javalec.util.ShareVar;

public class UserDao {
	
	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;
	
	/* Field */
	String userid;
	String userName;
	String userPassword;
	String userAddress;
	String userPhone;
	String userEmail;
	ArrayList<UserDto> beanList;
	
	
	public UserDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	/* Update Data를 가져와 줄 생성자 */
	public UserDao(String userid, String userName, String userPassword, String userAddress, String userPhone,
			String userEmail) {
		super();
		this.userid = userid;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userAddress = userAddress;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
	}



	/* Method */
	/* 조회 */
	public ArrayList<UserDto> userInfo(String userid) {
		String query = "select userName, userPassword, userAddress, userPhone, userEmail from user where userid = " + "'" + userid + "'";
		beanList = new ArrayList<UserDto>();
		try {
			Connection con = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				String wkName = rs.getString(1);
				String wkPass = rs.getString(2);
				String wkAddress = rs.getString(3);
				String wkPhone = rs.getString(4);
				String wkEmail = rs.getString(5);
				
				UserDto userDto = new UserDto(wkName, wkPass, wkAddress, wkPhone, wkEmail);
				beanList.add(userDto);
			}
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return beanList;
		
	}
	
	/* 삭제 */
	public boolean deleteAction(String userid) {
	    PreparedStatement ps = null;
	    boolean isDeleted = false; // 삭제 여부를 나타내는 변수

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
	        Statement stmt = con.createStatement();

	        String query = "delete from user where userid = ?";

	        ps = con.prepareStatement(query);
	        ps.setString(1, userid);
	        int deleteCount = ps.executeUpdate();

	        if (deleteCount > 0) {
	            isDeleted = true; // 삭제된 데이터가 있으면 true로 설정
	        } else {
	        	isDeleted = false;
	        }
	        con.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return isDeleted;
	}

		
	/* 업데이트 */
		public boolean updateAction() {
			boolean updateResult = false;
			PreparedStatement ps = null;
			try {
				
				String query = "update user set userName = ?, userPassword = ?, userPhone = ?, userAddress = ?, userEmail = ? where userid = ?";
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt = con.createStatement();
				
				ps = con.prepareStatement(query);
				
				ps.setString(1, userName);
				ps.setString(2, userPassword);
				ps.setString(3, userPhone);
				ps.setString(4, userAddress);
				ps.setString(5, userEmail);
				ps.setString(6, userid);
				
				int updateCount = ps.executeUpdate();
				if(updateCount > 0) {
					updateResult = true;
				}
				else {
					updateResult = false;
				}
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			return updateResult;
	}	
	
	
	
	

}
