package com.javalec.dto;

public class CartDto {
	// Field
	
	int cartNO;			// 주문번호
	int cartQty;		// 수량
	int cartPrice;		// 가격
	int cartSize;		// 신발 사이즈
	String name;		// 상품명
	String filename;	// 이미지

	
	public CartDto() {
		// TODO Auto-generated constructor stub
	}



	public CartDto(int cartNO,String name, int cartSize, int cartQty, int cartPrice, String filename) {
		super();
		this.cartNO = cartNO;
		this.cartQty = cartQty;
		this.cartPrice = cartPrice;
		this.name = name;
		this.filename = filename;
		this.cartSize = cartSize;
	}



	
	
	
	
	public int getCartSize() {
		return cartSize;
	}



	public void setCartSize(int cartSize) {
		this.cartSize = cartSize;
	}



	public int getCartNO() {
		return cartNO;
	}



	public String getFilename() {
		return filename;
	}



	public void setFilename(String filename) {
		this.filename = filename;
	}



	public void setCartNO(int cartNO) {
		this.cartNO = cartNO;
	}



	public int getCartQty() {
		return cartQty;
	}



	public void setCartQty(int cartQty) {
		this.cartQty = cartQty;
	}



	public int getCartPrice() {
		return cartPrice;
	}



	public void setCartPrice(int cartPrice) {
		this.cartPrice = cartPrice;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}






	
	
	
	
	
	
	
	
}
