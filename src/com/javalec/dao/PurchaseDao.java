package com.javalec.dao;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
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
	String userid;
	String purchaseInsertdate;
	String prtchaseDeletedate;
	String name;
	String fileName;
	FileInputStream file;
	
	
	public PurchaseDao() {
		// TODO Auto-generated constructor stub
	}


	public PurchaseDao(int purchaseNo) {
		super();
		this.purchaseNo = purchaseNo;
	}
	
	
	
	// 테이블 데이터
	public ArrayList<PurchaseDto> selectList(){
		ArrayList<PurchaseDto> dtoList = new ArrayList<PurchaseDto>();
		
		String query = "select pc.purchaseNo, p.productCode, pc.size, pc.purchaseQty, u.userid, pc.purchaseInsertdate from user u, product p, purchase pc";
		String query1 = " where pc.userid = u.userid and pc.productCode = p.productCode";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(query + query1);
			
			while (rs.next()) {
				int wkSeq = rs.getInt(1);
				int wkCode = rs.getInt(2);
				int wkSize = rs.getInt(3);
				int wkQty = rs.getInt(4);
				String wkId = rs.getString(5);
				Date wkPurchaseInsertdate = rs.getDate(6);
				
				String wkFilename = rs.getString(7);
				
				File file = new File("./" + wkFilename);
				
				FileOutputStream output = new FileOutputStream(file);
				InputStream input =rs.getBinaryStream(8);
				
				byte[] buffer = new byte[1024];
				
				while(input.read() > 0) {
					output.write(buffer);
				}
				PurchaseDto dto = new PurchaseDto(wkSeq, wkCode, wkSize, wkQty, wkId, wkPurchaseInsertdate);
				dtoList.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}

	
	
	
	
	
	
}// End
