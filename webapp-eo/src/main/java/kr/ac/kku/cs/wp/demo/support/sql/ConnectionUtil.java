package kr.ac.kku.cs.wp.demo.support.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	private static final String URL = "jdbc:mysql://localhost:3306/cswp_202020950?serverTimezone=UTC";
	private static final String USER= "fishandrew";
	private static final String PASSWORD = "202020950";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException{
		Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
		connection.setAutoCommit(false);
		return connection;
	}
}

