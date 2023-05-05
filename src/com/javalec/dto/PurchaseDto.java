package com.javalec.dto;

import java.sql.Timestamp;

public class PurchaseDto {

	//  Field
	
	int purchaseNo;
	String userid;
	int productCode;
	int size;
	Timestamp purchaseInsertdate;
	Timestamp prtchaseDeletedate;
	int purchaseQty;
	
	// Constructor
	public PurchaseDto() {
		// TODO Auto-generated constructor stub
	}

	public PurchaseDto(int purchaseNo, String userid, int productCode, int size, Timestamp purchaseInsertdate,
			Timestamp prtchaseDeletedate, int purchaseQty) {
		super();
		this.purchaseNo = purchaseNo;
		this.userid = userid;
		this.productCode = productCode;
		this.size = size;
		this.purchaseInsertdate = purchaseInsertdate;
		this.prtchaseDeletedate = prtchaseDeletedate;
		this.purchaseQty = purchaseQty;
	}

	
	
	// Method
	
	public int getPurchaseNo() {
		return purchaseNo;
	}

	public void setPurchaseNo(int purchaseNo) {
		this.purchaseNo = purchaseNo;
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Timestamp getPurchaseInsertdate() {
		return purchaseInsertdate;
	}

	public void setPurchaseInsertdate(Timestamp purchaseInsertdate) {
		this.purchaseInsertdate = purchaseInsertdate;
	}

	public Timestamp getPrtchaseDeletedate() {
		return prtchaseDeletedate;
	}

	public void setPrtchaseDeletedate(Timestamp prtchaseDeletedate) {
		this.prtchaseDeletedate = prtchaseDeletedate;
	}

	public int getPurchaseQty() {
		return purchaseQty;
	}

	public void setPurchaseQty(int purchaseQty) {
		this.purchaseQty = purchaseQty;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}// End
