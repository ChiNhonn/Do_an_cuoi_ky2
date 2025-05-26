package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
	public static Connection getConnection() {
		Connection c = null;
		
		try {
			String URL = "jdbc:mysql://localhost:3306/spy_agency";
			String USER = "root";
			String password = "";
			
			c = DriverManager.getConnection(URL, USER, password);
		} catch (SQLException e) {
			System.out.println("Kết nối thất bại");
			e.printStackTrace();
		}
		
		return c;
	}
	
	public static void closeConnection(Connection c) {
		try {
			if(c != null) {
				c.close();
				System.out.println("Đã ngắt kết nối thành công");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
