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

import com.javalec.dto.ProductDto;
import com.javalec.util.ShareVar;

public class ProductDao {
	
	/* Field */
	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;
	
	int productCode;
	String productName;
	int productPrice;
	String productInsertdate;
	String productUpdatedate;
	String productDeletedate;
	String productImageName;
	FileInputStream productImage;
	int brandNo;
	
	/* Constructor */
	public ProductDao() {
		// TODO Auto-generated constructor stub
	}
	
	/* 유저 메인에서 첫 테이블 화면을 받아와 줄 생성자 */
	public ProductDao(String productName, int productPrice, FileInputStream productImage) {
		super();
		this.productName = productName;
		this.productPrice = productPrice;
		this.productImage = productImage;
	}



	/* Method */
	public ArrayList<ProductDto> selectList(){
		ArrayList<ProductDto> beanList = new ArrayList<ProductDto>();
		String query = "select productName, productPrice, productImageName, productImage from product";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String wkName = rs.getString(1);
				int wkPrice = rs.getInt(2);
				String wkFileName = rs.getString(3);
				/* File 만들기 */
				File file = new File("./" + wkFileName);
				FileOutputStream output = new FileOutputStream(file);
				InputStream input = rs.getBinaryStream(4);
				byte[] buffer = new byte[1024];
				while(input.read(buffer) > 0) {
					output.write(buffer);
				}
				ProductDto productDto = new ProductDto(wkName, wkPrice, wkFileName);
				beanList.add(productDto);
				System.out.println(beanList.size());
			}
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return beanList;
	}
}
