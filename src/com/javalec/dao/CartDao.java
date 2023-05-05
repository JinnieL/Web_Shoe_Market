package com.javalec.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.dto.CartDto;
import com.javalec.util.ShareVar;

public class CartDao {

	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;
	
	
	int cartNO;
	int cartQty;
	int cartPirce;
	String name;
	String fileName;
	FileInputStream file;
	String userid;
	int productCode;
	
	
	public CartDao() {
		// TODO Auto-generated constructor stub
	}
	
	

	
	public CartDao(int cartNO, int cartQty) {
		super();
		this.cartNO = cartNO;
		this.cartQty = cartQty;
	}
	

	public CartDao(int cartNO) {
		super();
		this.cartNO = cartNO;
	}



	// 테이블에 데이터
	public ArrayList<CartDto> selectList(){
		ArrayList<CartDto> beanList = new ArrayList<CartDto>();	//데이터를 쌓을 장소
		
		String query = "select c.cartNo, p.productName, p.productPrice, c.cartQty, p.productImageName, p.productImage from cart c, user u, product p";
		String query1 = " where c.userid = u.userid and c.productCode = p.productCode";
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");											
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);	
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(query + query1);	// 데이터 베이스(데이터 한줄이 들어가있다)
			
			while(rs.next()) {							// rs 에서 읽어올 것이 없을 경우까지
				int wkSeq = rs.getInt(1);				// 한줄에 데이터 하나씩 빼주는 작업
				String wkName = rs.getString(2);
				int wqPirce = rs.getInt(3);
				int wkQty = rs.getInt(4);
				String wkFilename = rs.getString(5);

				File file = new File("./" + wkFilename);
				
				FileOutputStream output = new FileOutputStream(file);
				InputStream input = rs.getBinaryStream(6);
				byte[] buffer = new byte[1024];
				
				while(input.read(buffer) > 0) {
					output.write(buffer);
				}
				CartDto dto = new CartDto(wkSeq, wkName, wkQty, wqPirce, wkFilename); // db에 가져온 데이터를 한줄에 넣기위해
				beanList.add(dto);
			}
			conn_mysql.close();
		}catch (Exception e) {	// 에러 걸리면
			e.printStackTrace();
		}
		
		return beanList;	
		
	}	

	// 취소
	public boolean deleteAction() {
		PreparedStatement ps = null;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			String query = "delete from cart where cartNo = ?";
			
			ps = conn_mysql.prepareStatement(query);
			ps.setInt(1, cartNO);
			
			
			ps.executeUpdate();
			conn_mysql.close();
						
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}
	
	
	// 장바구니 비우기
	public boolean alldeleteAction() {
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			String query = "delete from cart";
			
			ps = conn_mysql.prepareStatement(query);
			
			ps.executeUpdate();
			conn_mysql.close();
						
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	
	// 수량 업데이트
	public boolean tableUpdate() {
		PreparedStatement ps = null;
		System.out.println(cartNO);
		System.out.println(cartQty);
		try {
			// DB 연결!!! 선언자
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			String query = "update cart set cartQty = ?";
			String query1 = " where cartNo = ?";
		
			ps = conn_mysql.prepareStatement(query + query1); 

			ps.setInt(1, cartQty);
			ps.setInt(2, cartNO);
			
			ps.executeUpdate();
			conn_mysql.close();
			return true;
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	

	
	
	
	
	
	
	
	
	
	
}// end
