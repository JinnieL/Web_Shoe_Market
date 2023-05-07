package com.javalec.dto;

import java.io.FileInputStream;
import java.sql.Date;

public class PurchaseDto {

	//  Field
	
	int purchasseNO;			// 주문번호
	int purchasseQty;		// 수량
	int purchassePrice;		// 가격
	int purchasseSize;		// 신발 사이즈
	String name;		// 상품명
	String filename;	// 이미지
	String userid;		// userid
	int productCode;	// 상품 코드
	String purchaseInsertdate;	// 구매일자
	String purchaseDeletedate;	// 환불
	
	
	// Constructor
	public PurchaseDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	

	public PurchaseDto(String userid, int productCode, int purchasseSize, int purchasseQty) {
		super();
		this.purchasseQty = purchasseQty;
		this.purchasseSize = purchasseSize;
		this.userid = userid;
		this.productCode = productCode;
	}





	public PurchaseDto(int purchasseNO, String userid, int productCode, int purchasseSize,  
			String purchaseInsertdate, String purchaseDeletedate ,int purchasseQty ) {
		super();
		this.purchasseNO = purchasseNO;
		this.purchasseQty = purchasseQty;
		this.purchasseSize = purchasseSize;
		this.userid = userid;
		this.productCode = productCode;
		this.purchaseInsertdate = purchaseInsertdate;
		this.purchaseDeletedate = purchaseDeletedate;
	}









	public PurchaseDto(int purchasseNO, String name, int purchasseSize, int purchasseQty, int purchassePrice, 
			String filename) {
		super();
		this.purchasseNO = purchasseNO;
		this.purchasseQty = purchasseQty;
		this.purchassePrice = purchassePrice;
		this.purchasseSize = purchasseSize;
		this.name = name;
		this.filename = filename;
	}


	
	
	
	
	
	

	public String getUserid() {
		return userid;
	}




	public void setUserid(String userid) {
		this.userid = userid;
	}




	public int getProductCode() {
		return productCode;
	}




	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}




	public String getPurchaseInsertdate() {
		return purchaseInsertdate;
	}




	public void setPurchaseInsertdate(String purchaseInsertdate) {
		this.purchaseInsertdate = purchaseInsertdate;
	}




	public String getPurchaseDeletedate() {
		return purchaseDeletedate;
	}




	public void setPurchaseDeletedate(String purchaseDeletedate) {
		this.purchaseDeletedate = purchaseDeletedate;
	}




	public int getPurchasseNO() {
		return purchasseNO;
	}



	public void setPurchasseNO(int purchasseNO) {
		this.purchasseNO = purchasseNO;
	}



	public int getPurchasseQty() {
		return purchasseQty;
	}



	public void setPurchasseQty(int purchasseQty) {
		this.purchasseQty = purchasseQty;
	}



	public int getPurchassePrice() {
		return purchassePrice;
	}



	public void setPurchassePrice(int purchassePrice) {
		this.purchassePrice = purchassePrice;
	}



	public int getPurchasseSize() {
		return purchasseSize;
	}



	public void setPurchasseSize(int purchasseSize) {
		this.purchasseSize = purchasseSize;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getFilename() {
		return filename;
	}



	public void setFilename(String filename) {
		this.filename = filename;
	}



















	





























	


	
	
	
	
	
	
	
	
	
	
}// End
