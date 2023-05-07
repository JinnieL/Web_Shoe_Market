package com.javalec.dto;

public class UserDto {

	String userid;
	String userName;
	String userPassword;
	String userAddress;
	String userPhone;
	String userEmail;
	
	
	/* Constructor */
	public UserDto() {
		// TODO Auto-generated constructor stub
	}

	/* My Page Data */
	public UserDto(String userName, String userPassword, String userAddress, String userPhone, String userEmail) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.userAddress = userAddress;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
	}









	/* getter & setter */
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	
	
	
}
