package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetStudentScoreData {

	static void getStudentList() throws ClassNotFoundException, SQLException {

		Connection con = ConnectionTest.getConnectionDetails();
		PreparedStatement ps3 = con.prepareStatement(" select* from student");
		ResultSet rs = ps3.executeQuery();
		System.out.printf("%-10s%-25s%15s\n\n", "Id", "Student_Name", "Score");
		while (rs.next()) {
			System.out.printf("%-10d%-25s%15d\n", rs.getInt(1), rs.getString(2) + " " + rs.getString(3), rs.getInt(4));
		}
		con.close();
		ps3.close();
	}

	static void getScoreById() throws SQLException {

		Connection con = ConnectionTest.getConnectionDetails();
		System.out.println("\nEnter Your Id");
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		PreparedStatement ps3 = con.prepareStatement(" select* from student where Student_Id=" + id);
		ResultSet rs = ps3.executeQuery();
		System.out.printf("\n%-10s%-25s%15s\n", "Id", "Student_Name", "Score");
		while (rs.next()) {
			System.out.printf("%-10d%-25s%15d\n", rs.getInt(1), rs.getString(2) + " " + rs.getString(3), rs.getInt(4));
		}
		con.close();
	}

}
