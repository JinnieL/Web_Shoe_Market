package com.javalec.dao;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Statement;

import com.javalec.dto.CartDto;
import com.javalec.dto.PurchaseDto;
import com.javalec.util.ShareVar;


public class PurchaseDao {

	//  ShareVar DB
	
	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;
	
	
	
	// 변수 
	
	int purchaseNo;
	int productCode;
	int size;
	int purchaseQty;
	String userid = "doughyun";
	String purchaseInsertdate;
	String prtchaseDeletedate;
	String name;
	String fileName;
	FileInputStream file;
	String wkImageName;
	
	
	public PurchaseDao() {
		// TODO Auto-generated constructor stub
	}


	public PurchaseDao(int purchaseNo) {
		super();
		this.purchaseNo = purchaseNo;
	}
	
	
	
	// 테이블에 데이터
	public ArrayList<PurchaseDto> selectList(){
		ArrayList<PurchaseDto> beanList = new ArrayList<PurchaseDto>();	//데이터를 쌓을 장소
		
		String query = "select c.cartNo, p.productName, p.productPrice, c.size, c.cartQty, p.productImageName, p.productImage from cart c, user u, product p";
		String query1 = " where c.userid = u.userid and c.productCode = p.productCode";
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");											
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);	
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(query + query1);	// 데이터 베이스(데이터 한줄이 들어가있다)
			
			while(rs.next()) {							// rs 에서 읽어올 것이 없을 경우까지
				int wkSeq = rs.getInt(1);				// 한줄에 데이터 하나씩 빼주는 작업
				String wkName = rs.getString(2);
				int wkPirce = rs.getInt(3);
				int wkSize = rs.getInt(4);
				int wkQty = rs.getInt(5);
				
				String wkFilename = rs.getString(6);

				File file = new File("./" + wkFilename);
				
				FileOutputStream output = new FileOutputStream(file);
				InputStream input = rs.getBinaryStream(7);
				byte[] buffer = new byte[1024];
				
				while(input.read(buffer) > 0) {
					output.write(buffer);
				}
				PurchaseDto dto = new PurchaseDto(wkSeq, wkName, wkSize, wkQty, wkPirce, wkFilename); // db에 가져온 데이터를 한줄에 넣기위해
				beanList.add(dto);
			}
			conn_mysql.close();
		}catch (Exception e) {	// 에러 걸리면
			e.printStackTrace();
		}
		
		return beanList;	
	}
	
	// 테이블 데이터 취소
	
	public boolean deleteAction() {
		PreparedStatement ps = null;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			String query = "delete from cart where cartNo = ?";
			
			ps = conn_mysql.prepareStatement(query);
			ps.setInt(1, purchaseNo);
			
			
			ps.executeUpdate();
			conn_mysql.close();
						
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}
	
	
	// 구매 데이터 입력
	public boolean addToPurchase() {
		PreparedStatement ps = null ;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt = con.createStatement();
			
			String query = "select userid, productCode, size, cartQty from cart ";
			String insert = "insert into purchase (userid, productCode, size, purchaseQty)";
			String insert1 = " values( ?, ?, ?, ?)" ;
			System.out.println(">>>>>>>>>>>>");
			ResultSet rs = stmt.executeQuery(query);
			ps = con.prepareStatement(insert + insert1); 
			
			while(rs.next()) {
				ps.setString(1, rs.getString(1));
				ps.setInt(2, rs.getInt(2));
				ps.setInt(3, rs.getInt(3));
				ps.setInt(4, rs.getInt(4));
				ps.executeUpdate();
			}
			
			con.close();
			return true;
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}
	// 구매 수량 재고 빼기
	
	public boolean updateQty() {
		PreparedStatement ps = null;
		int purchaseQty = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
		

			String qty = "select c.productCode, c.size, c.cartQty, p.productStock from cart c, productOption p where c.size = p.size" ;
														
			ResultSet rs = stmt_mysql.executeQuery(qty);
	
			String query = "update productOption set productStock = ?";
			String query1 = " where size = ? and productCode = ?";
			
			
			ps = conn_mysql.prepareStatement(query + query1); 
			
			while(rs.next()) {
				int wkCode = rs.getInt(1);
				int wkSize = rs.getInt(2);
				int wkCqty = rs.getInt(3);
				int wkPqty = rs.getInt(4);
				purchaseQty = (wkPqty - wkCqty);
				
				ps.setInt(1, purchaseQty);
				ps.setInt(2, wkSize);
				ps.setInt(3, wkCode);
				ps.executeUpdate();
			}
			
			conn_mysql.close();
			return true;
			

			
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		
	}
	

	
	// 구매 후 장바구니 비우기
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
	
	
	
	
	

	
	
	
	
	
	
	
	
	
}// End
