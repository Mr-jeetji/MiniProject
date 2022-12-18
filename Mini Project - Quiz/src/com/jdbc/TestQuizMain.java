package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TestQuizMain {

	public static void startTest() throws SQLException {
		int studentId = StudentInput.takeStudentInput();
		TestQuizMain.test_on_java(studentId);
	}

	private static void test_on_java(int id) {

		System.out.println("\n You have registered ...You Can Attempt Quiz Now...\n");
		try {
			Connection con = ConnectionTest.getConnectionDetails();
			PreparedStatement ps = con.prepareStatement("select* from test_on_java order by rand()");
			ResultSet rs = ps.executeQuery();
			int score = 0;
			int i = 1;
			while (rs.next()) {

				System.out.print("Q" + i + ".");i++;
				System.out.println(rs.getString(2) + "\r");
				System.out.println(rs.getString(3));
				System.out.println(rs.getString(4));
				System.out.println(rs.getString(5));
				System.out.println(rs.getString(6));
				System.out.println("\n Enter Option no ");
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(System.in);
				try {
					int choice = sc.nextInt();
					if (choice == rs.getInt(7)) {
						score++;
					} 
				} catch (Exception e) {
					System.out.println("\n!...Enter option no between 1 to 4 only \n");
					System.out.println(
							"\n-----------------------------------------------------------------------------------------\n");
					continue;
				}
				System.out.println(
						"\n-----------------------------------------------------------------------------------------\n");
				
			}
			System.out.println("\nThank You..!\n\n");
			showScore(score);
			storeScore(id, score);
			con.close();
			ps.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private static void storeScore(int id, int score) throws SQLException {

		Connection con = ConnectionTest.getConnectionDetails();
		PreparedStatement ps = con.prepareStatement("update student Set Score =? where Student_Id = ?");
		ps.setInt(1, score);
		ps.setInt(2, id);
		ps.execute();
		
	}

	private static void showScore(int score) {

		System.out.println("You have got " + score + " out of 10");

		if (score <= 10 && score > 8) {
			System.out.println("\n*** Congratulations..! You passed with grade A ! ***\n");
		} else if (score <= 8 && score >= 6) {
			System.out.println("\n*** Congratulations..! You passed with grade B !***\n");
		} else if (score == 5) {
			System.out.println("\n*** Congratulations..! You passed with grade C !***\n");
		} else {
			System.out.println("\n Sorry, You Have Failed...! got Grade D \n");
		}

	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Instruction.instruction();		
		TestQuizMain.startTest();
		GetStudentScoreData.getStudentList();
		GetStudentScoreData.getScoreById();

	}
}