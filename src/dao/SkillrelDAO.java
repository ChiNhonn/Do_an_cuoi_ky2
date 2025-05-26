package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.JDBCUtil;
import model.Skillrel;

public class SkillrelDAO implements DAOInterface<Skillrel>{
	
	public static SkillrelDAO getInstance() {
		return new SkillrelDAO();
	}
	@Override
	public int insert(Skillrel t) {
		int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "INSERT INTO skillrel(agent_id, skill_id) VALUES(?, ?)";
            	PreparedStatement pst = c.prepareCall(sql);
            	pst.setInt(1, t.getAgent_ID());
            	pst.setInt(2, t.getSkill_ID());
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
	}

	@Override
	public int update(Skillrel t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Skillrel t) {
		int result = 0;
	    try {
	        Connection c = JDBCUtil.getConnection();
	        String sql = "DELETE FROM skillrel WHERE agent_id = ? AND skill_id = ?";
	        PreparedStatement pst = c.prepareStatement(sql);
	        pst.setInt(1, t.getAgent_ID());
	        pst.setInt(2, t.getSkill_ID());
	        result = pst.executeUpdate(); 
	        pst.close();
	        JDBCUtil.closeConnection(c);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}

	@Override
	public ArrayList<Skillrel> selectALL() {
		ArrayList<Skillrel> list = new ArrayList<Skillrel>();
        try {
			Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM skillrel";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            		int agent_id = rs.getInt("agent_id");
            		int skill_id = rs.getInt("skill_id");
            		Skillrel skillrel = new Skillrel(agent_id, skill_id);
                list.add(skillrel);
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
	public Skillrel selectByID(Skillrel t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Skillrel> selectByCondition(String Conition) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<Skillrel> search(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean exists(Skillrel t) {
		boolean result = false;
	    try {
	        Connection c = JDBCUtil.getConnection();
	        String sql = "SELECT * FROM skillrel WHERE agent_id = ? AND skill_id = ?";
	        PreparedStatement pst = c.prepareStatement(sql);
	        pst.setInt(1, t.getAgent_ID());
	        pst.setInt(2, t.getSkill_ID());
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
            String sql = "SELECT COUNT(*) FROM skillrel";
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

}
