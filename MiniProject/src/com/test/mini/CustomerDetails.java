package com.test.mini;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerDetails {
	static String Customer_id11 = "";
	static String Cart_Id = "";

	public static void loginDetails() {
		Connection con = null;
		PreparedStatement pst1 = null;
		int i = 0;

		Scanner sc = new Scanner(System.in);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/e-commerce";
			String username = "root";
			String passward = "Pratu@98";
			con = DriverManager.getConnection(url, username, passward);

			System.out.println("▶️Enter id: 3 characters & 3 digits only");
			Customer_id11 = sc.next();
			Boolean b = IdVerification.idVerification(Customer_id11);
			if (b == true) {
				System.out.println("▶️Enter Password: 6 characters/Digits only");
				String c_pass = sc.next();
				Boolean p = IdVerification.PassVerification(c_pass);
				if (p == true) {
					ProductPage.productDetails();
				} else if (p == false) {
					System.out.println("Your id is not registered...Please register first!");
					System.out.println("▶️Enter L for Login OR \n ▶️Enter R for registration");

					String userStatus1 = sc.next();
					userStatus1 = userStatus1.toLowerCase();
					if (userStatus1.startsWith("l")) {
						CustomerDetails cd = new CustomerDetails();
						cd.loginDetails();
					} else if (userStatus1.startsWith("r")) {
						System.out.println("▶️Fill Following Fields:");
						CustomerDetails cd = new CustomerDetails();
						cd.registrationDetails();

					}
				} else {
					System.out.println("Invalid Input format!Please try Again");
				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void registrationDetails() {
		Connection con = null;
		PreparedStatement pst = null;
		int i = 0;
		try {
			Scanner sc = new Scanner(System.in);
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/e-commerce";
			String username = "root";
			String passward = "Pratu@98";
			con = DriverManager.getConnection(url, username, passward);

			System.out.println("▶️Enter id: 3 characters OR 3 digits only");
			Customer_id11 = sc.next();
			boolean j = IdVerification.idVerification(Customer_id11);
			if (j == false) {

				System.out.println("▶️Enter Password: 6 characters OR Digits only");
				String c_pass = sc.next();
				System.out.println("▶️Enter name:");
				String name = sc.next();
				System.out.println("▶️Enter Address max in 20 characters:");
				String Address = sc.next();
				System.out.println("▶️Enter 6 digit Pincode:");
				int Pincode = sc.nextInt();
				System.out.println("▶️Enter Your Phone Number without country code-(+91):");
				long PhoneNumber = sc.nextLong();
				char[] ch = Customer_id11.toCharArray();

				String Id = "Insert into Cart values(?)";
				PreparedStatement cartId = con.prepareStatement(Id);

				Cart_Id = Customer_id11.replace(Customer_id11.charAt(0), 'N');
				cartId.setString(1, Cart_Id);
				cartId.execute();
				String query = "Insert into customer values(?,?,?,?,?,?,?)";
				pst = con.prepareStatement(query);
				pst.setString(1, Customer_id11);
				pst.setString(2, c_pass);
				pst.setString(3, name);
				pst.setString(4, Address);
				pst.setInt(5, Pincode);
				pst.setLong(6, PhoneNumber);

				pst.setString(7, Cart_Id);
				pst.execute();
				System.out.println("✳️Registration Successfull...Enjoy Shopping✳️");
				ProductPage.productDetails();
			} else {
				System.out.println("✳️Your id is already exist...Please Login! or Register With new id!✳️");
				System.out.println("▶️Enter L for Login or Enter R for registration");
			

				String userStatus1 = sc.next();
				userStatus1 = userStatus1.toLowerCase();
				if (userStatus1.startsWith("l")) {
					CustomerDetails cd = new CustomerDetails();
					cd.loginDetails();
					
				} else if (userStatus1.startsWith("r")) {
					System.out.println("▶️Fill Following Fields:");
					CustomerDetails cd = new CustomerDetails();
					cd.registrationDetails();

				} else {
					
					System.out.println("❌..Invalid Input format!Please try Again..❌");
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				con.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}



