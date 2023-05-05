package com.javalec.dao;

import java.sql.Timestamp;

import com.javalec.util.ShareVar;

public class PurchaseDao {

	//  ShareVar DB
	
	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;
	
	
	
	// 변수 
	
	int purchaseNo;
	String userid;
	int productCode;
	int size;
	Timestamp purchaseInsertdate;
	Timestamp prtchaseDeletedate;
	int purchaseQty;
	
	
	public PurchaseDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
	
	
	
	
}// End
