package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentInput {
	
	 static int takeStudentInput() throws SQLException {

		Connection con = ConnectionTest.getConnectionDetails();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Student ID");
		int id = scanner.nextInt();
		System.out.println("Enter First Name");
		String FirstName = scanner.next();
		System.out.println("Enter Last Name ");
		String LastName = scanner.next();

		PreparedStatement ps1 = con
				.prepareStatement("insert into student(Student_Id,First_Name,Last_Name) values (?,?,?)");
		ps1.setInt(1, id);
		ps1.setString(2, FirstName);
		ps1.setString(3, LastName);
		try {
			ps1.execute();
		} catch (Exception e) {
			System.out.println("\n You have allready Attempted this Quiz. Thank You..!");
			System.exit(0);
		}
		;
		return id;

	}
//
//	private static void checkId(int id) throws SQLException {
//		Connection con = ConnectionTest.getConnectionDetails();
//		PreparedStatement ps1 = con.prepareStatement("select Student_Id from student where Student_Id =?");
//		ps1.setInt(1, id);
//	    ResultSet rs =ps1.executeQuery();
//	     if (rs.next()) {
//	    	 System.out.println("You have allready attempted");
//	     }
//	    	
//	}
}
