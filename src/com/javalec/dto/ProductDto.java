package com.javalec.dto;

import java.io.FileInputStream;

public class ProductDto {
	
	/* Field */
	int productCode;
	String productName;
	int productPrice;
	String productInsertdate;
	String productUpdatedate;
	String productDeletedate;
	String productImageName;
	int brandNo;
	String brandName;
	int size;
	
	/* Constructor */
	public ProductDto() {
		// TODO Auto-generated constructor stub
	}

	/* 테이블에 데이터를 띄워줄 생성자 */
	public ProductDto(int productCode, String productName, int productPrice, String productImageName) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productImageName = productImageName;
	}
	
	/* 상품 상세 페이지에 띄울 데이터를 가져와줄 생성자*/
	public ProductDto(int productCode, String productName, int productPrice, String brandName, int size) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productPrice = productPrice;
		this.brandName = brandName;
		this.size = size;
	}

	/* getter & setter */
	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductInsertdate() {
		return productInsertdate;
	}

	public void setProductInsertdate(String productInsertdate) {
		this.productInsertdate = productInsertdate;
	}

	public String getProductUpdatedate() {
		return productUpdatedate;
	}

	public void setProductUpdatedate(String productUpdatedate) {
		this.productUpdatedate = productUpdatedate;
	}

	public String getProductDeletedate() {
		return productDeletedate;
	}

	public void setProductDeletedate(String productDeletedate) {
		this.productDeletedate = productDeletedate;
	}

	public String getProductImageName() {
		return productImageName;
	}

	public void setProductImageName(String productImageName) {
		this.productImageName = productImageName;
	}

	public int getBrandNo() {
		return brandNo;
	}

	public void setBrandNo(int brandNo) {
		this.brandNo = brandNo;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}



	

	



	
	
}	// End Class
