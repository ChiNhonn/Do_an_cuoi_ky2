package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.JDBCUtil;
import model.Securityclearance;

public class SecurityclearanceDAO implements DAOInterface<Securityclearance>{

	public static SecurityclearanceDAO getInstance() {
		return new SecurityclearanceDAO();
	}
	
	@Override
	public int insert(Securityclearance t) {
		int result = 0;
		try {
            Connection c = JDBCUtil.getConnection();
            String sql = "INSERT INTO securityclearance(sc_id, sc_level, description) VALUES(?, ?, ?)";
            PreparedStatement pst = c.prepareCall(sql);
            pst.setInt(1, t.getSc_ID());
            pst.setString(2, t.getSc_level());
            pst.setString(3, t.getDescription());
            result = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
	}

	@Override
	public int update(Securityclearance t) {
	    int result = 0;
	    try {
	        Connection c = JDBCUtil.getConnection();
	        String sql = "UPDATE securityclearance SET sc_level = ?, description = ? WHERE sc_id = ?";
	        PreparedStatement pst = c.prepareStatement(sql);
	        pst.setString(1, t.getSc_level());
	        pst.setString(2, t.getDescription());
	        pst.setInt(3, t.getSc_ID());

	        result = pst.executeUpdate(); // ← cần thêm dòng này để thực thi cập nhật
	        pst.close();
	        JDBCUtil.closeConnection(c);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}


	@Override
	public int delete(Securityclearance t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "DELETE FROM securityclearance WHERE sc_id=?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getSc_ID());
            result = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
	}

	@Override
	public ArrayList<Securityclearance> selectALL() {
		ArrayList<Securityclearance> list = new ArrayList<Securityclearance>();
        try {
			Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM securityclearance";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            		int sc_id = rs.getInt("sc_id");
            		String sc_level = rs.getString("sc_level");
            		String description = rs.getString("description");
            		Securityclearance sc = new Securityclearance(sc_id, sc_level, description);
                list.add(sc);
            }
            rs.close();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}

	@Override
	public Securityclearance selectByID(Securityclearance t) {
		Securityclearance resutl = null;
        try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM securityclearance WHERE sc_id = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getSc_ID());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
        		int sc_id = rs.getInt("sc_id");
        		String sc_level = rs.getString("sc_level");
        		String description = rs.getString("description");
        		resutl = new Securityclearance(sc_id, sc_level, description);
         }
         rs.close();
         pst.close();
         JDBCUtil.closeConnection(c);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return resutl;
	}

	@Override
	public ArrayList<Securityclearance> selectByCondition(String Conition) {
		ArrayList<Securityclearance> list = new ArrayList<Securityclearance>();
        try {
			Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM securityclearance WHERE " + Conition;
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
	        		int sc_id = rs.getInt("sc_id");
	        		String sc_level = rs.getString("sc_level");
	        		String description = rs.getString("description");
	        		Securityclearance sc= new Securityclearance(sc_id, sc_level, description);
                list.add(sc);
            }
            rs.close();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}

	@Override
	public ArrayList<Securityclearance> search(String keyword) {
		ArrayList<Securityclearance> list = new ArrayList<>();
	    try {
			Connection c = JDBCUtil.getConnection();
	        String sql = "SELECT * FROM securityclearance WHERE sc_level LIKE ? OR description LIKE ?";
	        PreparedStatement pst = c.prepareStatement(sql);
	        String likeKeyword = "%" + keyword + "%";
	        pst.setString(1, likeKeyword);
	        pst.setString(2, likeKeyword);
	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	        		int sc_id = rs.getInt("sc_id");
	            String sc_level = rs.getString("sc_level");
	            String description = rs.getString("description");
	            Securityclearance sc = new Securityclearance(sc_id, sc_level, description);
	            list.add(sc);
        }
	        rs.close();
	        pst.close();
            JDBCUtil.closeConnection(c);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	 return list;
	}

	@Override
	public boolean exists(Securityclearance t) {
	    boolean result = false;
	    try {
	        Connection c = JDBCUtil.getConnection();
	        String sql = "SELECT * FROM securityclearance WHERE sc_id = ?";
	        PreparedStatement pst = c.prepareStatement(sql);
	        pst.setInt(1, t.getSc_ID());
	        ResultSet rs = pst.executeQuery();
	        if (rs.next()) {
	            result = true;
	        }
	        rs.close();
	        pst.close();
	        JDBCUtil.closeConnection(c);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}

	@Override
	public int count() {
		int result = 0;
        try {
			Connection c = JDBCUtil.getConnection();
            String sql = "SELECT COUNT(*) FROM securityclearance";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
        		result = rs.getInt(1);
        }
            rs.close();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
	}

	@Override
	public ArrayList<Object[]> countGroupByTwoFields(String field1, String field2) {
	    ArrayList<Object[]> list = new ArrayList<>();
	    try {
	        Connection c = JDBCUtil.getConnection();
	        String sql = "SELECT " + field1 + ", " + field2 + ", COUNT(*) FROM securityclearance GROUP BY " + field1 + ", " + field2;
	        PreparedStatement pst = c.prepareStatement(sql);
	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	            String val1 = rs.getString(1);
	            String val2 = rs.getString(2);
	            int count = rs.getInt(3);
	            list.add(new Object[]{val1, val2, count});
	        }
	        rs.close();
	        pst.close();
	        JDBCUtil.closeConnection(c);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}

}
