package com.javalec.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.dto.AdminDto;
import com.javalec.util.ShareVar;

public class AdminDao {
	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;

	int brandNo;
	String brandName ;
	int productCode;
	String productName;
	int size;
	int productPrice;
	int productStock;
	String productInsertdate;
	String productImageName;
	FileInputStream productImage;
	// 이미지 경로도 적어야되는데
	
	// Field
	public AdminDao() {
		// TODO Auto-generated constructor stub
	}

	// 윈도우 창 오픈된 초기 테이블 데이터 생성자 - 브랜드명, 제품명, 사이즈, 재고량
	public AdminDao(String brandName, String productName, int size, int productStock) {
		super();
		this.brandName = brandName;
		this.productName = productName;
		this.size = size;
		this.productStock = productStock;
	}
	
	// 테이블 클릭할 때 받아올 데이터 생성자 - 브랜드코드,브랜드명, 제품코드,제품명, 사이즈, 가격, 입고날, 제품사진
	public AdminDao(int brandNo, String brandName, int productCode, String productName, int size, int productPrice,
			int productStock, String productInsertdate, String productImageName, FileInputStream productImage) {
		super();
		this.brandNo = brandNo;
		this.brandName = brandName;
		this.productCode = productCode;
		this.productName = productName;
		this.size = size;
		this.productPrice = productPrice;
		this.productStock = productStock;
		this.productInsertdate = productInsertdate;
		this.productImageName = productImageName;
		this.productImage = productImage;
	}

	
	
	
	
	
	// 윈도우 창 오픈 초기 테이블 데이터 table에 불러오기 - 브랜드명, 제품명, 사이즈, 재고량
	public ArrayList<AdminDto> selectList(){ 	
		ArrayList<AdminDto> dtoList = new ArrayList<AdminDto>();
		
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
				int productStock = rs.getInt(4);
	
				
				AdminDto dto = new AdminDto(brandName, productName, size, productStock);
				dtoList.add(dto);
			}

			conn_mysql.close();
						
		}catch(Exception e) {
			e.printStackTrace();
		}

		return dtoList;
	}


	// innerTable의 데이터 선택 시 제품 상세 페이지 채워주기
	
	
	
	
	
	
	
	// 조건 검색 
	public ArrayList<AdminDto> conditionList(){
		ArrayList<AdminDto> dtoList = new ArrayList<AdminDto>();
		
		String whereDefault = "select p.productCode, b.brandName, p.productName, po.size, po.productStock ";
		String whereDefault1 = "from brand b, product p, productOption po";
		String whereDefault2 = "where b.brandNo= p.brandNo and po.productCode = p.productCode and ";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(whereDefault + whereDefault1);
			
			while(rs.next()) {
				int wkSeq = rs.getInt(1);
				String wkName = rs.getString(2);
				String wkTelno = rs.getString(3);
				String wkRelation = rs.getString(4);
				
				Dto dto = new Dto(wkSeq, wkName, wkTelno, wkRelation);
				dtoList.add(dto);
			}

			conn_mysql.close();
						
		}catch(Exception e) {
			e.printStackTrace();
		}

		return dtoList;
	}


	/*
	public AdminDto tableClick() { 		// 브랜드코드,브랜드명, 제품코드,제품명, 사이즈, 가격, 입고날, 제품사진
		AdminDto dto = null;
			
		String whereDefault = "select b.brandNo, b.brandName, p.productCode, p.productName, p.productPrice, po.size, po.productStock, p.productInsertdate, p.productImageName, productImage ";
		String whereDefault1 = "from brand b, product p, productOption po";
		String whereDefault2 = "where b.brandNo= p.brandNo and po.productCode = p.productCode";
		
		try {		
			Class.forName("com.mysql.cj.jdbc.Driver"); 		
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement(); 		
			
			ResultSet rs = stmt_mysql.executeQuery(whereDefault + whereDefault1 + whereDefault2) ; 		// sql 가서 데이터 불러오기
			
			while(rs.next()) { 		// 읽을 데이터가 있으면 계속 반복해라 rs.next() : 데이터 타입이 boolean.
				int wkbrandNo = rs.getInt(1);
				String wkbrandName = rs.getString(2);
				String wkproductCode = rs.getString(3);
				String wkproductName = rs.getString(4);
				int wkPrice = rs.getInt(5);
				int wkSize = rs.getInt(6);
				int wkStock = rs.getInt(7);
				String wkproductInsertdate = rs.getString(8);
				String wkFileName = rs.getString(9);
				// 이미지 - 파일 만들기
				File file = new File("./" + wkFileName);
				FileOutputStream output = new FileOutputStream(file);
				InputStream input = rs.getBinaryStream(10);
				byte[] buffer = new byte[1024];
				while(input.read(buffer) > 0) {
					output.write(buffer);
				}
				
				AdminDto adminDto = new AdminDto(wkbrandNo, wkbrandName, wkproductCode,wkproductName, wkPrice, wkSize, wkStock, wkproductInsertdate, wkFileName );
				beanList.add(adminDto);
			}
			conn_mysql.close();					
			
		} catch (Exception e) {
			e.printStackTrace(); 		
		}
		return beanList;
	}
	
	*/
	
	
	
	
}
