package config;

import java.sql.Connection;

public class Test {
	public static void main(String[] args) {
		Connection c = JDBCUtil.getConnection();
		if(c != null) {
			System.out.println("Kết nối thàng công");
		} else {
			System.out.println("Kết nối thất bại");
		}
		
		
	}
}