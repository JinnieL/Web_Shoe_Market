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
	
	/* Constructor */
	public ProductDto() {
		// TODO Auto-generated constructor stub
	}

	/* 테이블에 데이터를 띄워줄 생성자 */
	public ProductDto(String productName, int productPrice, String productImageName) {
		super();
		this.productName = productName;
		this.productPrice = productPrice;
		this.productImageName = productImageName;
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

	

	



	
	
}	// End Class
