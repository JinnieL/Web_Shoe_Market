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

import com.javalec.dto.ProductDto;
import com.javalec.dto.PurchaseDto;
import com.javalec.dto.PurchaseHistoryDto;
import com.javalec.util.ShareVar;

public class PurchaseHistoryDao {
	/* Field */
	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;
	int purchaseNo;
	int productCode;
	int productStock;
	int size;
	String userid = "donghyun";
	int purchaseQty;
	String purchaseInsertdate;
	String purchaseDeletedate;
	String productName;
	String productImageName;
	int productPrice;
	String wkImageName;
	
	
	/* Constructor */
	public PurchaseHistoryDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	/* Method */
	/* 01. 주문 내역 데이터를 검색하는 메소드 */
	public ArrayList<PurchaseHistoryDto> getPurchaseHistory(String userid){
		ArrayList<PurchaseHistoryDto> beanList = new ArrayList<PurchaseHistoryDto>();
		String query = "select p.purchaseNo, pd.productName, pd.productPrice, p.purchaseQty, p.purchaseInsertdate, pd.productImageName, pd.productImage"
				+ " from user u, purchase p, product pd"
				+ " where u.userid = p.userid and p.productCode = pd.productCode and"
				+ " u.userid = " + "'" + userid + "'";
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int wkNo = rs.getInt(1);
				String wkName = rs.getString(2);
				int wkPrice = rs.getInt(3);
				int wkQty = rs.getInt(4);
				String wkDate = rs.getString(5);
				wkImageName = rs.getString(6);
				System.out.println(wkImageName);
				File file = new File("./" + wkImageName);
				FileOutputStream output = new FileOutputStream(file);
				InputStream input = rs.getBinaryStream(7);
				byte[] buffer = new byte[1024];
				while(input.read(buffer) > 0) {
					output.write(buffer);
				}
				PurchaseHistoryDto historyDto = new PurchaseHistoryDto(wkNo, wkQty, wkDate, wkName, wkImageName, wkPrice);
				beanList.add(historyDto);
			}
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return beanList;
	}
	
	/* 02. 주문을 취소하는 메소드 */
	public boolean canclePurchase(int purchaseNo) {
		String query = "delete from purchase where purchaseNo = ?";
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt = con.createStatement();
			ps = con.prepareStatement(query);
			ps.setInt(1, purchaseNo);
			ps.executeUpdate();
			con.close();
			return true;
		} catch(Exception e)	{
			e.printStackTrace();
			return false;
		}
	}
	
	
	
}	// End Class
