package com.test.mini;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ProductPage {
	static String z = "";

	public static void productDetails() throws SQLException {
		Connection connection = DBConnector.getConnection();

		Scanner scanner = new Scanner(System.in);
		do {
			System.out.print("▶️To check the products enter 'P'" + "\n▶️To Add product to wishlist enter 'S'"
					+ "\n▶️To view cart Details 'C'" + "\nEnter Input : ");

			String s = scanner.next();
			s = s.toLowerCase();
			if (s.startsWith("p")) {
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product");
				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					System.out.println("\t" + "  Product Id : " + resultSet.getString(1) + "\t" + "  Product Name : "
							+ resultSet.getString(2) + "\t\t" + "Colour:" + resultSet.getString(3) + "\t\t"
							+ "Specification:" + resultSet.getString(4) + "\t\t" + " | Cost : " + resultSet.getDouble(5)
							+ "\t\t" + " | Quantity : " + resultSet.getInt(6));
				}
			} else if (s.startsWith("s")) { // add to cart
				do {

					String Q1 = "Insert into Cart_item  values(?, ? ,?,'Y')";
					String cart_id = CustomerDetails.Cart_Id;

					String Q2 = "select cart_id from customer where customer_id=?";
					PreparedStatement preparedStatementnew = connection.prepareStatement(Q2);
					preparedStatementnew.setString(1, CustomerDetails.Customer_id11);
					ResultSet rs = preparedStatementnew.executeQuery();

					while (rs.next()) {
						z = rs.getString(1);
					}

					PreparedStatement preparedStatement = connection.prepareStatement(Q1);
					System.out.println("▶️Enter product id which you want to add : ");
					String x = scanner.next();
					System.out.println("▶️Enter Quantity in numbers : ");
					int a = scanner.nextInt();

					preparedStatement.setInt(1, a);
					preparedStatement.setString(2, z);
					preparedStatement.setString(3, x);

					preparedStatement.execute();

					System.out.println("▶️Enter 'Y' to Continue shopping or 'N' to Checkout : ");
					String ss = scanner.next();
					ss.toLowerCase();
					if (ss.startsWith("n")) {
						break;
					}
				} while (true);

			} else if (s.startsWith("c")) {

				String Q3 = "select customer_id from customer where customer_id=?";
				PreparedStatement preparedStatementn = connection.prepareStatement(Q3);
				preparedStatementn.setString(1, CustomerDetails.Customer_id11);
				ResultSet rs = preparedStatementn.executeQuery();

				while (rs.next()) {
					z = rs.getString(1);

				}

				PreparedStatement preparedStatement1 = connection.prepareStatement(
						"      select name,Quantity_wished,cost, (quantity_wished * cost) total_payable from product p join cart_item c on p.Product_id=c.Product_id where cart_id in (select Cart_id from customer where customer_id=?)");
				preparedStatement1.setString(1, z);
				ResultSet resultSet = preparedStatement1.executeQuery();

				while (resultSet.next()) {
					System.out.println("\t" + "  Product Name : " + resultSet.getString(1) + "\t\t" + "Quantity:"
							+ resultSet.getInt(2) + "\t\t" + "cost:" + resultSet.getDouble(3) + "\t\t"
							+ "  Product total : " + resultSet.getDouble(4));

				}

				PreparedStatement preparedStatementr = connection.prepareStatement(
						"     select  sum(quantity_wished * cost/2) Total_payable from product p join cart_item c on p.product_id=c.product_id where c.product_id in (select product_id from cart_item where cart_id in(select Cart_id from customer where customer_id=?) and purchased='Y');");
				preparedStatementr.setString(1, z);
				ResultSet resultSet4 = preparedStatementr.executeQuery();

				while (resultSet4.next()) {
					System.out.println(" \n *💰 Total Payable Amount : " + resultSet4.getDouble(1));

				}

				System.out.println("▶️Enter 'Y' to Continue shopping or 'N' to Checkout : ");
				String ss = scanner.next();
				ss.toLowerCase();
				if (ss.startsWith("n")) {
					System.out.println("🙏..Thank You for shopping!!..🙏");
					System.out.println("              😀                ");
					break;
				}
			}
		} while (true);

	}
}