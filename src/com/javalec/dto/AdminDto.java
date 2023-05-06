package com.javalec.dto;

import java.io.FileInputStream;

public class AdminDto {

	String brandName ;
	String productName;
	int size;
	int productStock;
	int brandNo;
	int productCode;
	int productPrice;
	
	String productInsertdate;
	String productUpdatedate;
	String productDeletedate;
	String productImageName;
	FileInputStream productImage;
	// 이미지 경로도 적어야되는데
	
	public AdminDto() {
		// TODO Auto-generated constructor stub
	}

	// 윈도우 창 오픈된 초기 테이블 데이터 생성자
	public AdminDto(String brandName, String productName, int size, int productStock) {
		super();
		this.brandName = brandName;
		this.productName = productName;
		this.size = size;
		this.productStock = productStock;
	}

	
	
	// Method
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

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}
	
	
	
	
	

	
	
}
