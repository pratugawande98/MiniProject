package com.test.mini;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IdVerification {
	public static Boolean idVerification(String s) {
		Connection connection = DBConnector.getConnection();
		boolean g = false;

		try {
			String query = " select Customer_id from customer where Customer_id = ? ";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, s);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				g = s.contentEquals(rs.getString(1));
				System.out.println(g);
				return g;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return g;

	}

	public static Boolean PassVerification(String s) {
		Connection connection = DBConnector.getConnection();
		boolean p = false;
		try {
			String query = " select c_pass from customer where c_pass = ? ";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, s);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				p = s.contentEquals(rs.getString(1));
				System.out.println(p);
				return p;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;

	}

}


