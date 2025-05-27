package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.JDBCUtil;
import model.Affiliation;

public class AffiliationDAO implements DAOInterface<Affiliation>{
	
	public static AffiliationDAO getInstance() {
		return new AffiliationDAO();
	}
	
	@Override
	public int insert(Affiliation t) {
		int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "INSERT INTO affiliation(affiliation_id, affiliation_name, description) VALUES(?, ?, ?)";
            	PreparedStatement pst = c.prepareCall(sql);
            	pst.setInt(1, t.getAffiliation_id());
            	pst.setString(2, t.getAffiliation_name());
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
	public int update(Affiliation t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "UPDATE affiliation SET affiliation_name = ?, description = ? WHERE affiliation_id = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getAffiliation_name());
			pst.setString(2, t.getDescription());
			pst.setInt(3, t.getAffiliation_id());
			result = pst.executeUpdate();
			pst.close();
            JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(Affiliation t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "DELETE FROM affiliation WHERE affiliation_id=?";   
			PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getAffiliation_id());
            result = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
	}

	@Override
	public ArrayList<Affiliation> selectALL() {
		ArrayList<Affiliation> list = new ArrayList<Affiliation>();
        try {
			Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM affiliation";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            		int affiliation_id = rs.getInt("affiliation_id");
            		String affiliation_name = rs.getString("affiliation_name");
            		String description = rs.getString("description");
            		Affiliation affiliation = new Affiliation(affiliation_id, affiliation_name, description);
                list.add(affiliation);
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
	public Affiliation selectByID(Affiliation t) {
		Affiliation resutl = null;
        try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM affiliation WHERE affiliation_id = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getAffiliation_id());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
        		int affiliation_id = rs.getInt("affiliation_id");
        		String affiliation_name = rs.getString("affiliation_name");
        		String description = rs.getString("description");
        		resutl = new Affiliation(affiliation_id, affiliation_name, description);
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
	public ArrayList<Affiliation> selectByCondition(String Conition) {
		ArrayList<Affiliation> list = new ArrayList<Affiliation>();
        try {
			Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM affiliation WHERE " + Conition;
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            		int affiliation_id = rs.getInt("affiliation_id");
            		String affiliation_name = rs.getString("affiliation_name");
            		String description = rs.getString("description");
            		Affiliation affiliation = new Affiliation(affiliation_id, affiliation_name, description);
                list.add(affiliation);
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
	public ArrayList<Affiliation> search(String keyword) {
	    ArrayList<Affiliation> list = new ArrayList<>();
	    try {
	        Connection c = JDBCUtil.getConnection();
	        String sql = "SELECT * FROM affiliation " +
	                     "WHERE CAST(affiliation_id AS CHAR) LIKE ? " +
	                     "OR affiliation_name LIKE ? " +
	                     "OR description LIKE ?";
	        PreparedStatement pst = c.prepareStatement(sql);
	        String likeKeyword = "%" + keyword + "%";
	        pst.setString(1, likeKeyword);
	        pst.setString(2, likeKeyword);
	        pst.setString(3, likeKeyword);

	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	            int affiliation_id = rs.getInt("affiliation_id");
	            String affiliation_name = rs.getString("affiliation_name");
	            String description = rs.getString("description");
	            Affiliation affiliation = new Affiliation(affiliation_id, affiliation_name, description);
	            list.add(affiliation);
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
	public boolean exists(Affiliation t) {
		boolean result = false;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM affiliation WHERE (affiliation_id = ?) OR (affiliation_name = ?)";
	        PreparedStatement pst = c.prepareStatement(sql);
	        pst.setInt(1, t.getAffiliation_id());
	        pst.setString(2, t.getAffiliation_name());
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
            String sql = "SELECT COUNT(*) FROM affiliation";
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
	        String sql = "SELECT " + field1 + ", " + field2 + ", COUNT(*) FROM affiliation GROUP BY " + field1 + ", " + field2;
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
	public boolean isAffiliationIDExists(int affiliationID) {
	    boolean exists = false;
	    try {
	        Connection c = JDBCUtil.getConnection();
	        String sql = "SELECT 1 FROM affiliation WHERE affiliation_id = ?";
	        PreparedStatement pst = c.prepareStatement(sql);
	        pst.setInt(1, affiliationID);
	        ResultSet rs = pst.executeQuery();
	        exists = rs.next(); 
	        rs.close();
	        pst.close();
	        JDBCUtil.closeConnection(c);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return exists;
	}

	
}
