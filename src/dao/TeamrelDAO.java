package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.JDBCUtil;
import model.Languagerel;
import model.Team;
import model.Teamrel;

public class TeamrelDAO implements DAOInterface<Teamrel>{

	public static TeamrelDAO getInstance() {
		return new TeamrelDAO();
	}
	@Override
	public int insert(Teamrel t) {
		int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "INSERT INTO teamrel(agent_id, team_id) VALUES(?, ?)";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getAgent_ID());
            pst.setInt(2, t.getTeam_ID());
            result = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
	}

	@Override
	public int update(Teamrel t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Teamrel t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "DELETE FROM teamrel WHERE agent_id = ? AND team_id = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getAgent_ID());
            pst.setInt(2, t.getTeam_ID());
            result = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
	}

	@Override
	public ArrayList<Teamrel> selectALL() {
		ArrayList<Teamrel> list = new ArrayList<Teamrel>();
        try {
			Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM teamrel";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            		int agent_id = rs.getInt("agent_id");
            		int team_id = rs.getInt("team_id");
            		Teamrel teamrel = new Teamrel(agent_id, team_id);
                list.add(teamrel);
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
	public Teamrel selectByID(Teamrel t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Teamrel> selectByCondition(String Conition) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<Teamrel> search(String keyword) {
	    ArrayList<Teamrel> list = new ArrayList<>();
	    try {
	        Connection c = JDBCUtil.getConnection();
	        String sql = "SELECT * FROM teamrel WHERE CONCAT(agent_id, '') LIKE ? OR CONCAT(team_id, '') LIKE ?";
	        PreparedStatement pst = c.prepareStatement(sql);
	        String likeKeyword = "%" + keyword + "%";
	        pst.setString(1, likeKeyword);
	        pst.setString(2, likeKeyword);
	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	            int agent_id = rs.getInt("agent_id");
	            int team_id = rs.getInt("team_id");
	            Teamrel rel = new Teamrel(agent_id, team_id);
	            list.add(rel);
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
	public boolean exists(Teamrel t) {
		boolean result = false;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM teamrel WHERE agent_id = ? AND team_id = ?";
	        PreparedStatement pst = c.prepareStatement(sql);
	        pst.setInt(1, t.getAgent_ID());
	        pst.setInt(2, t.getTeam_ID());
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
