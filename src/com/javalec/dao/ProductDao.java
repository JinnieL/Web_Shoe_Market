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

import javax.swing.ImageIcon;

import com.javalec.dto.ProductDto;
import com.javalec.util.ShareVar;

public class ProductDao {
	
	/* Field */
	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;
	
	String userid = "donghyun";
	int cartQty;
	int productStock;
	int productCode;
	int size;
	String productName;
	int productPrice;
	String productInsertdate;
	String productUpdatedate;
	String productDeletedate;
	String productImageName;
	FileInputStream productImage;
	int brandNo;
	
	/* 생성한 파일을 다른 메소드에서도 쓰기 위해 전역변수로 설정 */
	String wkFileName;
	String conName;
	String conData;
	
	
	/* Constructor */
	public ProductDao() {
		// TODO Auto-generated constructor stub
	}
	
	/* 장바구니에 추가할 데이터를 받아줄 생성자 */
	public ProductDao(String userid, int cartQty, int productCode, int size) {
		super();
		this.userid = userid;
		this.cartQty = cartQty;
		this.productCode = productCode;
		this.size = size;
	}
	
	/* like 조건문의 데이터를 받아와줄 생성자 */
	public ProductDao(String conName, String conData) {
		super();
		this.conName = conName;
		this.conData = conData;
	}
	
	
	
	/* productDetail을 위해 검색 할 productCode와 사진 이름을 가져와주는 생성자 */
//	public ProductDao(int productCode, String productImageName) {
//		super();
//		this.productCode = productCode;
//		this.productImageName = productImageName;
//	}
//	

	/* 유저 메인에서 첫 테이블 화면을 받아와 줄 생성자 */
//	public ProductDao(int productCode, String productName, int productPrice, FileInputStream productImage) {
//		super();
//		this.productCode = productCode;
//		this.productName = productName;
//		this.productPrice = productPrice;
//		this.productImage = productImage;
//	}

	/* Method */
	/* 01. 전체 데이터 검색하는 메소드 */
	public ArrayList<ProductDto> selectList(){
		ArrayList<ProductDto> beanList = new ArrayList<ProductDto>();
		String query = "select productCode, productName, productPrice, productImageName, productImage from product";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int wkCode = rs.getInt(1);
				String wkName = rs.getString(2);
				int wkPrice = rs.getInt(3);
				wkFileName = rs.getString(4);
				/* File 만들기 */
				File file = new File("./" + wkFileName);
				FileOutputStream output = new FileOutputStream(file);
				InputStream input = rs.getBinaryStream(5);
				byte[] buffer = new byte[1024];
				while(input.read(buffer) > 0) {
					output.write(buffer);
				}
				ProductDto productDto = new ProductDto(wkCode, wkName, wkPrice, wkFileName);
				beanList.add(productDto);
			}
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return beanList;
	}
	
	/* 02. 선택한 상품만 찾아내서 보여주는 메소드 */
	public ArrayList<ProductDto> productDetail(int productCode) {
		ArrayList<ProductDto> beanList = new ArrayList<ProductDto>();
		String query = "select p.productCode, p.productName, p.productPrice, po.size, b.brandName"
				+ " from product p, productOption po, brand b"
				+ " where p.productCode = po.productCode and p.brandNo = b.brandNo and p.productCode = ";
		
		try {
			Connection con = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query + productCode);
			
			while(rs.next()){
			int wkCode = rs.getInt(1);
			String wkName = rs.getString(2);
			int wkPrice = rs.getInt(3);
			int wkSize = rs.getInt(4);
			String wkBrandName = rs.getString(5);
			ProductDto productDto = new ProductDto(wkCode, wkName, wkPrice, wkBrandName, wkSize);
			beanList.add(productDto);
			}
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return beanList;
	}
	
	/* 03. 선택한 상품을 장바구니에 담는 메소드 */
	public boolean addToCart() {
		String query = "insert into cart (userid, productCode, cartQty, size) values(?, ?, ?, ?)";
		PreparedStatement ps = null;
		System.out.println(userid + cartQty + productCode + size);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt = con.createStatement();
			ps = con.prepareStatement(query);
			ps.setString(1, userid);
			ps.setInt(2, productCode);
			ps.setInt(3, cartQty);
			ps.setInt(4, size);
			ps.executeUpdate();
			con.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/* 04. 사용자가 입력한 데이터를 검색해주는 메소드 */
	public ArrayList<ProductDto> conditionList(){
		ArrayList<ProductDto> beanList = new ArrayList<ProductDto>();
		String query = "select productCode, productName, productPrice, productImageName, productImage from product p, brand b where " + conName + " like " + "'%" + conData + "%'" + " and p.brandNo = b.brandNo";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				int wkCode = rs.getInt(1);
				String wkName = rs.getString(2);
				int wkPrice = rs.getInt(3);
				wkFileName = rs.getString(4);
				/* File 만들기 */
				File file = new File("./" + wkFileName);
				FileOutputStream output = new FileOutputStream(file);
				InputStream input = rs.getBinaryStream(5);
				byte[] buffer = new byte[1024];
				while(input.read(buffer) > 0) {
					output.write(buffer);
				}
				ProductDto productDto = new ProductDto(wkCode, wkName, wkPrice, wkFileName);
				beanList.add(productDto);
			}
			con.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return beanList;
	}
	
	/* 05. 사용자가 장바구니에 담기, 주문을 할 때 수량을 체크해주는 메소드 */
	public ArrayList<ProductDto> checkQty(int productCode, int size) {
		ArrayList<ProductDto> beanList = new ArrayList<ProductDto>();
		String query = "select p.productCode, po.size, po.productStock from product p, productOption po"
				+ " where p.productCode = po.productCode and"
				+ " p.productCode = " + productCode + " and po.size = " + size;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				int wkCode = rs.getInt(1);
				int wkSize = rs.getInt(2);
				int wkStock = rs.getInt(3);
				
				ProductDto productDto = new ProductDto(wkCode, wkSize, wkStock);
				beanList.add(productDto);
			}
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return beanList;
	}
	
}	// End Class
