package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.javalec.util.ShareVar;
import com.mysql.cj.xdevapi.PreparableStatement;

public class MypageDao {

	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;

	String userId;
	String name;
	String password;
	String phone;
	String address;
	String email;
	
	
	public MypageDao() {
		// TODO Auto-generated constructor stub
	}
	
	
		}
