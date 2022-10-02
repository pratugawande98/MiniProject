package com.test.mini;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class MainPage {
	public static void main(String[] args) throws SQLIntegrityConstraintViolationException, ClassNotFoundException  {
		Scanner sc = new Scanner(System.in);

		System.out.println("***************🙏 Welcome to E-Commerce 🙏*****************");

		do {
			System.out.println("You need to Login first/Register for new User");

			System.out.println("\n▶️Enter 'A' for admin \n ▶️'C' for customer ");

			// for admin login
			String userStatus = sc.next();
			userStatus = userStatus.toLowerCase();
			if (userStatus.equals("a")) {
				if (userStatus.startsWith("a")) {
					new Admin();
					System.out.println("▶️Enter id:");
					String id = sc.next();
					System.out.println("▶️Enter Password:");
					String password = sc.next();
					Admin.admin(id, password);
				}
				break;
			}
			// for customer login
			else if (userStatus.equals("c")) {
				if (userStatus.startsWith("c")) {

					System.out.println("▶️Enter L for Login or Enter R for registration");

					String userStatus1 = sc.next();
					userStatus1 = userStatus1.toLowerCase();
					if (userStatus1.equals("l")) {
						if (userStatus1.startsWith("l")) {
							new CustomerDetails();
							CustomerDetails.loginDetails();
						}
					} else if (userStatus1.equals("r")) {
						if (userStatus1.startsWith("r")) {
							System.out.println("▶️Fill Following Fields:");
							new CustomerDetails();
							CustomerDetails.registrationDetails();
							try {
								ProductPage.productDetails();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						} else {
							System.out.println("❌..Invalid input .. Please try again .. you have to choose 'l' or 'r'.");
						}
					}
				}
				break;
			} else {
				System.out.println("❌..Invalid Input format!Please try Again..You have to choose 'a' or 'c'.");

			}
		} while (true);
	}
}


