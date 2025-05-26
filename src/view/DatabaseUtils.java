package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import config.JDBCUtil;

public class DatabaseUtils {
	public static boolean registerUser(String username, String password, String gmail, String confirm) {
	    try {
	    		Connection c = JDBCUtil.getConnection();
	        String checkSql = "SELECT username FROM account WHERE username = ?";
	        PreparedStatement checkPst = c.prepareStatement(checkSql);
	        checkPst.setString(1, username);
	        ResultSet rs = checkPst.executeQuery();
	        if (rs.next()) return false;
	        String hashedPassword = Utils.generateHash(password);
	        String sql = "INSERT INTO account (username, password, gmail, confirm) VALUES (?, ?, ?, ?)";
	        PreparedStatement pst = c.prepareStatement(sql);
	        pst.setString(1, username);
	        pst.setString(2, hashedPassword);     
	        pst.setString(3, gmail);
	        pst.setString(4, confirm);
	        pst.executeUpdate();
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	public static boolean checkLogin(String username, String password) {
	    try (Connection c = JDBCUtil.getConnection()) {
	        String sql = "SELECT password FROM account WHERE username = ?";
	        PreparedStatement pst = c.prepareStatement(sql);
	        pst.setString(1, username);
	        ResultSet rs = pst.executeQuery();
	        if (rs.next()) {
	            String storedHash = rs.getString("password");
	            String inputHash = Utils.generateHash(password);  
	            return storedHash.equals(inputHash);  
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}
}
