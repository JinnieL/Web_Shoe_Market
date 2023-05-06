package com.javalec.dao;

import com.javalec.util.ShareVar;

public class JoinDao {


	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;
	
	String name;
	String password;
	String phone;
	String address;
	String email;
}
