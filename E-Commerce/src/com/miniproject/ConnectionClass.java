package com.miniproject;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
	
	static Connection con ;
	public static  Connection getConnection() {
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/e-commerse", "root", "root");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

}
