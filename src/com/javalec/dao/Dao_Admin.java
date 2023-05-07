package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import com.javalec.dto.Dto_Admin;
import com.javalec.util.ShareVar;

public class Dao_Admin {
	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;

	String brandName ;
	String productName;
	int size;
	int stock;
	
	public Dao_Admin() {
		// TODO Auto-generated constructor stub
	}

	// tableInit() 생성 후 테이블에 들어갈 데이터 생성자 ((순서), 브랜드 이름, 제품명, 사이즈, 재고)
	public Dao_Admin(String brandName, String productName, int size, int stock) {
		super();
		this.brandName = brandName;
		this.productName = productName;
		this.size = size;
		this.stock = stock;
	}
	
	// 윈도우 창 오픈 초기 테이블 데이터 table에 불러오기 - 브랜드명, 제품명, 사이즈, 재고량
	public ArrayList<Dto_Admin> selectList() {
		ArrayList<Dto_Admin> dtoList = new ArrayList<Dto_Admin>();
		
		String whereDefault = "select b.brandName, p.productName, po.size, po.productStock ";
		String whereDefault1 = "from brand b, product p, productOption po";
		String whereDefault2 = "where b.brandNo= p.brandNo and po.productCode = p.productCode";
			
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(whereDefault + whereDefault1 + whereDefault2);
			
			while(rs.next()) {
				String brandName = rs.getString(1);
				String productName = rs.getString(2);
				int size = rs.getInt(3);
				int stock = rs.getInt(4);
	
				
				Dto_Admin dto = new Dto_Admin(brandName, productName, size, stock);
				dtoList.add(dto);
			}

			conn_mysql.close();
						
		}catch(Exception e) {
			e.printStackTrace();
		}

		return dtoList;
		
	}
	

}
