package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.JDBCUtil;
import model.Skill;

public class SkillDAO implements DAOInterface<Skill>{
	
	public static SkillDAO getInstance() {
		return new SkillDAO();
	}
	@Override
	public int insert(Skill t) {
	    int result = 0;
	    try {
	        Connection c = JDBCUtil.getConnection();
	        String sql = "INSERT INTO skill(skill_id, skill_name) VALUES(?, ?)";
	        PreparedStatement pst = c.prepareStatement(sql); 

	        pst.setInt(1, t.getSkill_ID());
	        pst.setString(2, t.getSkill_name());

	        result = pst.executeUpdate();

	        pst.close();
	        JDBCUtil.closeConnection(c);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}


	@Override
	public int update(Skill t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Skill t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "DELETE FROM skill WHERE skill_id=?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getSkill_ID());
            result = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
	}

	@Override
	public ArrayList<Skill> selectALL() {
		ArrayList<Skill> list = new ArrayList<Skill>();
        try {
			Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM skill";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            		int skill_id = rs.getInt("skill_id");
            		String skill_name = rs.getString("skill_name");
            		Skill skill = new Skill(skill_id, skill_name);
                list.add(skill);
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
	public Skill selectByID(Skill t) {
		Skill resutl = null;
        try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM skill WHERE skill_id = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getSkill_ID());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
        		int skill_id = rs.getInt("skill_id");
        		String skill_name = rs.getString("skill_name");
        		resutl = new Skill(skill_id, skill_name);
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
	public ArrayList<Skill> selectByCondition(String Conition) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<Skill> search(String keyword) {
	    ArrayList<Skill> list = new ArrayList<>();
	    try {
	        Connection c = JDBCUtil.getConnection();
	        String sql = "SELECT * FROM skill WHERE CAST(skill_id AS CHAR) LIKE ? OR skill_name LIKE ?";
	        PreparedStatement pst = c.prepareStatement(sql);
	        String searchPattern = "%" + keyword + "%";
	        pst.setString(1, searchPattern);
	        pst.setString(2, searchPattern);

	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	            int skill_id = rs.getInt("skill_id");
	            String skill_name = rs.getString("skill_name");
	            Skill skill = new Skill(skill_id, skill_name);
	            list.add(skill);
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
	public boolean exists(Skill t) {
		boolean result = false;
	    try {
	        Connection c = JDBCUtil.getConnection();
	        String sql = "SELECT * FROM skill WHERE skill_id = ? OR skill_name = ?";
	        PreparedStatement pst = c.prepareStatement(sql);
	        pst.setInt(1, t.getSkill_ID());
	        pst.setString(2, t.getSkill_name());
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
            String sql = "SELECT COUNT(*) FROM skill";
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
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean isSkillIDExists(int SkillID) {
	    boolean exists = false;
	    try {
	        Connection c = JDBCUtil.getConnection();
	        String sql = "SELECT 1 FROM skill WHERE skill_id = ?";
	        PreparedStatement pst = c.prepareStatement(sql);
	        pst.setInt(1, SkillID);
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
