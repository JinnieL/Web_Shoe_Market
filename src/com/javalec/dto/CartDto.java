package com.javalec.dto;

public class CartDto {
	// Field
	
	int cartNO;			// 주문번호
	int cartQty;		// 수량
	int cartPrice;		// 가격
	String name;		// 상품명
	String filename;	// 이미지


	
	public CartDto() {
		// TODO Auto-generated constructor stub
	}



	public CartDto(int cartNO, String name, int cartQty, int cartPrice, String filename) {
		super();
		this.cartNO = cartNO;
		this.cartQty = cartQty;
		this.cartPrice = cartPrice;
		this.name = name;
	}

	public CartDto(int cartNO, int cartQty, int cartPrice, String name, String filename) {
		super();
		this.cartNO = cartNO;
		this.cartQty = cartQty;
		this.cartPrice = cartPrice;
		this.name = name;
		this.filename = filename;
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
