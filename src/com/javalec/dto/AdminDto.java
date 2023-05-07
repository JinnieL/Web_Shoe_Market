package com.javalec.dto;

import java.io.File;

public class AdminDto {
	
	// Constructor
	int brandNo;
	String brandName ;
	String productName;
	int size;
	int productCode;
	int productPrice;
	int productStock;
	String date;
	String productImageName;
	
	/* Constructor */
	
	public AdminDto() {
		// TODO Auto-generated constructor stub
	}
	
	// Dao에게 초기 tableInit() 정보 전달받아 메인에 주기위한 생성자
	public AdminDto(String brandName, String productName, int size, int productStock, int productCode) {
		super();
		this.brandName = brandName;
		this.productName = productName;
		this.size = size;
		this.productStock = productStock;
		this.productCode = productCode;
	}
	
	/* 관리자 페이지에 데이터를 가져다 줄 생성자 */
	public AdminDto(int brandNo, String brandName, String productName, int size, int productCode, int productPrice,
			int productStock, String date, String productImageName) {
		super();
		this.brandNo = brandNo;
		this.brandName = brandName;
		this.productName = productName;
		this.size = size;
		this.productCode = productCode;
		this.productPrice = productPrice;
		this.productStock = productStock;
		this.date = date;
		this.productImageName = productImageName;
	}

	
	
	
	
	/* getter & setter */
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public int getBrandNo() {
		return brandNo;
	}

	public void setBrandNo(int brandNo) {
		this.brandNo = brandNo;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getProductImageName() {
		return productImageName;
	}

	public void setProductImageName(String productImageName) {
		this.productImageName = productImageName;
	}


	
	
	

}
