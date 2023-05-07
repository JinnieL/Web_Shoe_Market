package com.javalec.dto;

public class Dto_Admin {
	
	// Constructor
	String brandName ;
	String productName;
	int size;
	int stock;
	
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Dto_Admin() {
		// TODO Auto-generated constructor stub
	}

	// Dao에게 초기 tableInit() 정보 전달받아 메인에 주기위한 생성자
	public Dto_Admin(String brandName, String productName, int size, int stock) {
		super();
		this.brandName = brandName;
		this.productName = productName;
		this.size = size;
		this.stock = stock;
	}
	
	

}
