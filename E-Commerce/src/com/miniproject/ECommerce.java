package com.miniproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ECommerce {
	public static void welcome() {
		System.out.println("***********************************************************");
		System.out.println("*************** ~ Welcome To E-Mart ~ *********************");
		System.out.println("***********************************************************");
	}

	private static String userRegistration() throws SQLException {

		System.out.println("\n\n\n*Plz..!  Register Before Shopping..!\n");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Your Full Name");
		String FullName = scanner.nextLine();
		System.out.println("Enter Your 10 digit Mobile No ");
		String MoNo = scanner.nextLine();
		Connection con = ConnectionClass.getConnection();
		PreparedStatement ps = con
				.prepareStatement("insert into customerinfo(customerName, mobileNo)" + " values (?,?)");
		ps.setString(1, FullName);
		ps.setString(2, MoNo);
		ps.execute();
		System.out.println("\n\n You have Registered... You can continue with shopping..!\n\n");
		return MoNo;
	}

	@SuppressWarnings("resource")
	public static void eCommerce() {
			
		try {
			String mono = userRegistration();
			Connection con = ConnectionClass.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from productinfo");
			ResultSet rs = ps.executeQuery();
			System.out.println("************     Available Product List     ************\n\n");
			System.out.printf("%-10s%-30s%10s\n\n", "Id",  "productName", "price");

			while (rs.next()) {
				System.out.println();
				System.out.printf("%-10d%-30s%10d\n", +rs.getInt(1), rs.getString(3), rs.getInt(2));
			}
			System.out.println("\n\nEnter the Product id anf no of quantiy of product you want to purchase ");
			int bill = 0;
			while (true) {

				Scanner sc = new Scanner(System.in);
				System.out.println("\n\nEnter the Product id");
				int id = sc.nextInt();
				System.out.println("Enter the Quantity of product");
				int quant = sc.nextInt();
				PreparedStatement ps2 = con.prepareStatement("select * from productinfo where productid =" + id);
				ResultSet rs2 = ps2.executeQuery();
				int totalprice = 0;
				while (rs2.next()) {
					totalprice = quant * rs2.getInt(2);	
					System.out.println("total price  of product >> " + (totalprice));
					System.out.println("\n press 1 to confirm and 0 to cancle)");
					int confirm=sc.nextInt();
					if(confirm==0) {continue;}
					if(confirm==1) { bill = bill + (totalprice); }
				}
				
				
				System.out.println("\nEnter 1 to continue Shopping\nEnter 0 to Stop Shopping");
				int temp = sc.nextInt();
				
				if (temp == 1) {
					continue;
				}
				if (temp == 0) {
					break;
				}
			}
			System.out.println("\nYOU NEED TO PAY : " + bill + "rs");
			System.out.println("\n***************!...THANK YOU...!********************");
			System.out.println("**************!. DO VISIT AGAIN .!******************");
			storeRecord(bill,mono);
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}

	}

	private static void storeRecord(int bill, String mono) throws SQLException {
		Connection con = ConnectionClass.getConnection();
		PreparedStatement ps = con
				.prepareStatement("update customerinfo set totalPurchase=" + bill + " where mobileNo = ? ");
		ps.setString(1, mono);
		ps.execute();
	}

	static void getcustomerData() throws SQLException {
		Connection con = ConnectionClass.getConnection();
		PreparedStatement ps2 = con.prepareStatement("select * from customerinfo");
		ResultSet rs = ps2.executeQuery();
		System.out.printf("%-25s%-15s%15s\n\n", "Name", "Mobile No", "total purchase");
		while (rs.next()) {
			System.out.printf("%-25s%-15s%15s\n", rs.getString(1), rs.getString(2), rs.getInt(3));
		}
	}
	static void fetchUserInfo() throws SQLException {
		Connection con = ConnectionClass.getConnection();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Mono of user to fetch its shoping history");
		String mono = sc.next();
		PreparedStatement ps = con
				.prepareStatement("select * from customerinfo where mobileNo = ? ");
		ps.setString(1, mono);
		ResultSet rs = ps.executeQuery();
		System.out.printf("%-25s%-15s%15s\n\n", "Name", "Mobile No", "total purchase");
		while (rs.next()) {
			System.out.printf("%-25s%-15s%15s\n", rs.getString(1), rs.getString(2), rs.getInt(3));
		}
	}
	public static void quantityOfProduct() throws SQLException {
		Connection con = ConnectionClass.getConnection();
		PreparedStatement ps = con
				.prepareStatement("select quantity from productinfo where productId = ? ");
		System.out.println("\nEnter ID to Check The Quantity Of Product");
		Scanner sc = new Scanner(System.in);	
		int id = sc.nextInt();
		ps.setInt(1,id );
		ResultSet rs =ps.executeQuery();
		while(rs.next()) {
			System.out.println("Quantity : "+rs.getInt(1) );
		}
	}

	public static void main(String[] args) throws SQLException {
//		welcome();		
//		eCommerce();
//		getcustomerData();// if want to fetch all constomer purchase history
//		fetchUserInfo();// if want constomer purchase history by its Mobile no
		quantityOfProduct(); // to check quantity of product by using id
	}
}
