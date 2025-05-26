package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.JDBCUtil;
import model.Languagerel;

public class LanguagerelDAO implements DAOInterface<Languagerel>{

	public static LanguagerelDAO getInstance() {
		return new LanguagerelDAO();
	}
	
	@Override
	public int insert(Languagerel t) {
		int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "INSERT INTO languagerel(agent_id, language_id) VALUES(?, ?)";
            	PreparedStatement pst = c.prepareCall(sql);
            	pst.setInt(1, t.getAgent_ID());
            	pst.setInt(2, t.getLanguage_ID());
            	result = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
	}

	@Override
	public int update(Languagerel t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Languagerel t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "DELETE FROM languagerel WHERE agent_id = ? AND language_id = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getAgent_ID());
            pst.setInt(2, t.getLanguage_ID());
            result = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
	}

	@Override
	public ArrayList<Languagerel> selectALL() {
		ArrayList<Languagerel> list = new ArrayList<Languagerel>();
        try {
			Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM languagerel";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            		int agent_id = rs.getInt("agent_id");
            		int language_id = rs.getInt("language_id");
            		Languagerel languagerel = new Languagerel(agent_id, language_id);
                list.add(languagerel);
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
	public Languagerel selectByID(Languagerel t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Languagerel> selectByCondition(String Conition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Languagerel> search(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(Languagerel t) {
		boolean result = false;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM languagerel WHERE (agent_id = ?) OR (language_id = ?)";
	        PreparedStatement pst = c.prepareStatement(sql);
	        pst.setInt(1, t.getAgent_ID());
	        pst.setInt(2, t.getLanguage_ID());
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Object[]> countGroupByTwoFields(String field1, String field2) {
		// TODO Auto-generated method stub
		return null;
	}

}
