package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.JDBCUtil;
import model.Affiliation;
import model.Language;

public class LanguageDAO implements DAOInterface<Language> {
	
	public static LanguageDAO getInstance() {
		return new LanguageDAO();
	}
	
	@Override
	public int insert(Language t) {
	    int result = 0;
	    try {
	        Connection c = JDBCUtil.getConnection();
	        String sql = "INSERT INTO language(language_id, language_name) VALUES(?, ?)";
	        PreparedStatement pst = c.prepareStatement(sql);
	        pst.setInt(1, t.getLanguage_ID());
	        pst.setString(2, t.getLanguage_name());
	        
	        result = pst.executeUpdate();  
	        
	        pst.close();
	        JDBCUtil.closeConnection(c);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}


	@Override
	public int update(Language t) {
	    int result = 0;
	    try {
	        Connection c = JDBCUtil.getConnection();
	        String sql = "UPDATE language SET language_name = ? WHERE language_id = ?";
	        PreparedStatement pst = c.prepareStatement(sql);
	        
	        pst.setString(1, t.getLanguage_name());
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
	public int delete(Language t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "DELETE FROM language WHERE language_id=?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getLanguage_ID());
            result = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

	}

	@Override
	public ArrayList<Language> selectALL() {
		ArrayList<Language> list = new ArrayList<Language>();
        try {
			Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM language";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            		int language_id = rs.getInt("language_id");
            		String language_name = rs.getString("language_name");
            		Language language = new Language(language_id,language_name);
                list.add(language);
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
	public Language selectByID(Language t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Language> selectByCondition(String Conition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Language> search(String keyword) {
	    ArrayList<Language> list = new ArrayList<>();
	    try {
	        Connection c = JDBCUtil.getConnection();
	        String sql = "SELECT * FROM language WHERE CAST(language_id AS CHAR) LIKE ? OR language_name LIKE ?";
	        PreparedStatement pst = c.prepareStatement(sql);
	        String likeKeyword = "%" + keyword + "%";
	        pst.setString(1, likeKeyword);
	        pst.setString(2, likeKeyword);

	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	            int language_id = rs.getInt("language_id");
	            String language_name = rs.getString("language_name");
	            Language language = new Language(language_id, language_name);
	            list.add(language);
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
	public boolean exists(Language t) {
		boolean result = false;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM language WHERE (language_id = ?) OR (language_name = ?)";
	        PreparedStatement pst = c.prepareStatement(sql);
	        pst.setInt(1, t.getLanguage_ID());
	        pst.setString(2, t.getLanguage_name());
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
	    ArrayList<Object[]> list = new ArrayList<>();
	    try {
	        Connection c = JDBCUtil.getConnection();
	        String sql = "SELECT " + field1 + ", " + field2 + ", COUNT(*) FROM language GROUP BY " + field1 + ", " + field2;
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
	
	public boolean isLanguageIDExists(int languageID) {
	    boolean exists = false;
	    try {
	        Connection c = JDBCUtil.getConnection();
	        String sql = "SELECT 1 FROM language WHERE language_id = ?";
	        PreparedStatement pst = c.prepareStatement(sql);
	        pst.setInt(1, languageID);
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
