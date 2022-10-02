package com.test.mini;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin {
	

		static Connection connection =null;
		static PreparedStatement preparedStatement = null;

		public static void admin(String id, String pass) throws ClassNotFoundException {

			final String admin_id = "admin";
			final String admin_password = "admin123";

			if (id.equals(admin_id) && pass.equals(admin_password)) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://localhost:3306/e-commerce";
					String user = "root";
					String password = "Pratu@98";
					connection = DriverManager.getConnection(url, user, password);

					Scanner scanner = new Scanner(System.in);
					System.out.println("🙏....Welcome Admin....🙏");
					do {

						System.out.print("\n\n▶️To check the products quantity enter 'Q'"
								+ "\n▶️To display the entire registered user list enter 'R'"
								+ "\n▶️To check the particular user history enter 'H'" + "\nEnter Input : ");
						/// To check the products quantity
						String s = scanner.next();
						s = s.toLowerCase();
						if (s.equals("q")) {
							if (s.startsWith("q")) {
								preparedStatement = connection
										.prepareStatement("SELECT Product_Id, Name, Cost, Quantity FROM product");
								ResultSet resultSet = preparedStatement.executeQuery();
								while (resultSet.next()) {
									System.out.println("\t" + "Product Id : " + resultSet.getString(1) + "\t"
											+ "  Product Name : " + resultSet.getString(2) + "\t\t\t\t" + " | Cost : "
											+ resultSet.getDouble(3) + "\t\t" + " | Quantity : " + resultSet.getInt(4));

								}
							}
						}

						// display the entire registered user list
						else if (s.equals("r")) {
							if (s.startsWith("r")) {
								preparedStatement = connection.prepareStatement("SELECT Customer_Id, C_Name FROM customer");
								ResultSet resultSet = preparedStatement.executeQuery();
								while (resultSet.next()) {
									System.out.println("User Id : " + resultSet.getString(1) + " | User Name : "
											+ resultSet.getString(2));
								}
							}
						}

						// To check the particular user history
						else if (s.equals("h")) {
							if (s.startsWith("h"))

							{
								System.out.println("▶️Enter customer id:");
								String customerId = scanner.next();

								preparedStatement = connection
										.prepareStatement("SELECT product_id,Quantity_wished FROM Cart_item WHERE"
												+ " (purchased='Y' and Cart_id in (select Cart_id FROM customer WHERE Customer_id=?))");
								preparedStatement.setString(1, customerId);

								ResultSet resultSet = preparedStatement.executeQuery();
								while (resultSet.next()) {
									System.out.println("Product Id : " + resultSet.getString(1) + " | Quantity wished : "
											+ resultSet.getString(2));
								}
							}
						} else {
							System.out.println("❌...Invalid Input...Please try again..you have to enter 'q' or 'r' or 'h'");
						}

						System.out.println("\n▶️▶️Do you want to continue (Y/N) : ");
						String str = scanner.next();
						str = str.toLowerCase();

						if (str.startsWith("y")) {

						} else if (str.startsWith("n")) {

							System.out.println("🙏...Thank you!!...🙏");
							System.out.println("         😀          ");
							break;

						} else {
							System.out.println("❌....Invalid input....❌");

						}
					} while (true);

				} catch ( ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						connection.close();
						preparedStatement.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				System.out.println("❌...Invalid Id and password ...❌ ");

			}
		}

	}


