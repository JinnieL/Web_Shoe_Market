package com.javalec.dto;

public class MypageDto {

	String name;
	String password;
	String phone;
	String address;
	String email;
	
	public MypageDto() {
		// TODO Auto-generated constructor stub
	}

	public MypageDto(String name, String password, String phone, String address, String email) {
		super();
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
