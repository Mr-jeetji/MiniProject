package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ConnectionTest {
	 static Connection con;
	 static PreparedStatement ps;
     
	static public Connection  getConnectionDetails() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz","root","root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
}
