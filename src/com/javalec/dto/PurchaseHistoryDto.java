package com.javalec.dto;

public class PurchaseHistoryDto {
	
	/* Field */
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
	
	
	/* Constructor */
	public PurchaseHistoryDto() {
		// TODO Auto-generated constructor stub
	}
	
	/* 창 열릴 때 주문내역 데이터를 받아와 줄 생성자 */
	public PurchaseHistoryDto(int purchaseNo, int purchaseQty, String purchaseInsertdate, String productName,
			String productImageName, int productPrice) {
		super();
		this.purchaseNo = purchaseNo;
		this.purchaseQty = purchaseQty;
		this.purchaseInsertdate = purchaseInsertdate;
		this.productName = productName;
		this.productImageName = productImageName;
		this.productPrice = productPrice;
	}

	/* getter & setter */
	public int getPurchaseNo() {
		return purchaseNo;
	}

	public void setPurchaseNo(int purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getPurchaseQty() {
		return purchaseQty;
	}

	public void setPurchaseQty(int purchaseQty) {
		this.purchaseQty = purchaseQty;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImageName() {
		return productImageName;
	}

	public void setProductImageName(String productImageName) {
		this.productImageName = productImageName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}



}
