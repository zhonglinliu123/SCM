package scm.util;

import java.sql.*;

public class DBUtil_c {

	private static String url = "jdbc:mysql://localhost:3306/scm";
	private static String user = "root";
	private static String password = "123456";
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
