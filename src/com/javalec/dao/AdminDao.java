package com.javalec.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import com.javalec.dto.AdminDto;
import com.javalec.util.ShareVar;

public class AdminDao {
	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;

	String brandName ;
	String productName;
	int size;
	int stock;
	
	public AdminDao() {
		// TODO Auto-generated constructor stub
	}

	// tableInit() 생성 후 테이블에 들어갈 데이터 생성자 ((순서), 브랜드 이름, 제품명, 사이즈, 재고)
	public AdminDao(String brandName, String productName, int size, int stock) {
		super();
		this.brandName = brandName;
		this.productName = productName;
		this.size = size;
		this.stock = stock;
	}
	
	// 윈도우 창 오픈 초기 테이블 데이터 table에 불러오기 - 브랜드명, 제품명, 사이즈, 재고량
	public ArrayList<AdminDto> selectList() {
		ArrayList<AdminDto> dtoList = new ArrayList<AdminDto>();
		
		String whereDefault = "select p.productCode, b.brandName, p.productName, po.size, po.productStock";
		String whereDefault1 = " from brand b, product p, productOption po";
		String whereDefault2 = " where b.brandNo = p.brandNo and po.productCode = p.productCode";
			
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(whereDefault + whereDefault1 + whereDefault2);
			
			while(rs.next()) {
				int wkCode = rs.getInt(1);
				String brandName = rs.getString(2);
				String productName = rs.getString(3);
				int size = rs.getInt(4);
				int stock = rs.getInt(5);
	
				
				AdminDto dto = new AdminDto(brandName, productName, size, stock, wkCode);
				dtoList.add(dto);
			}

			conn_mysql.close();
						
		}catch(Exception e) {
			e.printStackTrace();
		}

		return dtoList;
	}
	
	public ArrayList<AdminDto> queryAction(int size, int productCode){
		PreparedStatement ps = null;
		ArrayList<AdminDto> beanList = new ArrayList<AdminDto>();
		String whereDefault = "select b.brandNo, b.brandName, p.productCode, p.productName, po.size, p.productPrice, po.productStock, p.productInsertdate, p.productImageName, p.productImage";
		String whereDefault1 = " from brand b, product p, productOption po" ;
		String whereDefault2 = " where b.brandNo = p.brandNo and po.productCode = p.productCode and po.size = " + size + " and p.productCode = " + productCode;
		
		try  {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(whereDefault + whereDefault1 + whereDefault2);
			
			
			while (rs.next()) {
			int wkBrandNo = rs.getInt(1);
			String wkBrandName = rs.getString(2);
			int wkProductCode = rs.getInt(3);
			String wkProductName = rs.getString(4);
			int wkSize = rs.getInt(5);
			int wkPrice = rs.getInt(6);
			int wkStock = rs.getInt(7);
			String wkDate = rs.getString(8);
			String wkImageName = rs.getString(9);
			File file = new File("./" + wkImageName);
			FileOutputStream output = new FileOutputStream(file);
			InputStream input = rs.getBinaryStream(10);
			byte[] buffer = new byte[1024];
			AdminDto adminDto = new AdminDto(wkBrandNo, wkBrandName, wkProductName, wkSize, wkProductCode, wkPrice, wkStock, wkDate, wkImageName);
			beanList.add(adminDto);
			while(input.read(buffer) > 0) {
				output.write(buffer);
			}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return beanList;
		
	}
	
	
	
	
	

}
