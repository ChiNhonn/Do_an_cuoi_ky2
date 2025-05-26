package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.JDBCUtil;
import model.Affiliation;
import model.Team;

public class TeamDAO implements DAOInterface<Team>{

	public static TeamDAO getInstance() {
		return new TeamDAO();
	}
	@Override
	public int insert(Team t) {
		int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "INSERT INTO team (team_id, team_name, meeting_frequency) VALUES(?, ?, ?)";
            	PreparedStatement pst = c.prepareCall(sql);
            	pst.setInt(1, t.getTeam_ID());
            	pst.setString(2, t.getTeam_name());
            	pst.setString(3, t.getMeeting_frequency());
            	result = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
	}

	@Override
	public int update(Team t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "UPDATE team SET team_name = ?, meeting_frequency = ? WHERE team_id = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getTeam_name());
			pst.setString(2, t.getMeeting_frequency());
			pst.setInt(3, t.getTeam_ID());
			result = pst.executeUpdate();
			pst.close();
            JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(Team t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "DELETE FROM team WHERE team_id=?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getTeam_ID());
            result = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
	}

	@Override
	public ArrayList<Team> selectALL() {
		ArrayList<Team> list = new ArrayList<Team>();
        try {
			Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM team";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            		int team_id = rs.getInt("team_id");
            		String team_name = rs.getString("team_name");
            		String meeting_frequency = rs.getString("meeting_frequency");
            		Team team = new Team(team_id, team_name, meeting_frequency);
                list.add(team);
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
	public Team selectByID(Team t) {
		Team resutl = null;
        try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM team WHERE team_id = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getTeam_ID());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
	            	int team_id = rs.getInt("team_id");
	        		String team_name = rs.getString("team_name");
	        		String meeting_frequency = rs.getString("meeting_frequency");
	        		resutl = new Team(team_id, team_name, meeting_frequency);
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
	public ArrayList<Team> selectByCondition(String Conition) {
		ArrayList<Team> list = new ArrayList<Team>();
        try {
			Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM team WHERE " + Conition;
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
	            	int team_id = rs.getInt("team_id");
	        		String team_name = rs.getString("team_name");
	        		String meeting_frequency = rs.getString("meeting_frequency");
	        		Team team = new Team(team_id, team_name, meeting_frequency);
	            list.add(team);
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
	public ArrayList<Team> search(String keyword) {
	    ArrayList<Team> list = new ArrayList<>();
	    try {
	        Connection c = JDBCUtil.getConnection();
	        String sql = "SELECT * FROM team WHERE CAST(team_id AS CHAR) LIKE ? OR team_name LIKE ? OR meeting_frequency LIKE ?";
	        PreparedStatement pst = c.prepareStatement(sql);
	        String likeKeyword = "%" + keyword + "%";
	        pst.setString(1, likeKeyword);
	        pst.setString(2, likeKeyword);
	        pst.setString(3, likeKeyword);
	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	            int team_id = rs.getInt("team_id");
	            String team_name = rs.getString("team_name");
	            String meeting_frequency = rs.getString("meeting_frequency");
	            Team team = new Team(team_id, team_name, meeting_frequency);
	            list.add(team);
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
	public boolean exists(Team t) {
		boolean result = false;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM team WHERE (team_id = ?) OR (team_name = ?)";
	        PreparedStatement pst = c.prepareStatement(sql);
	        pst.setInt(1, t.getTeam_ID());
	        pst.setString(2, t.getTeam_name());
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
            String sql = "SELECT COUNT(*) FROM team";
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
	        String sql = "SELECT " + field1 + ", " + field2 + ", COUNT(*) FROM team GROUP BY " + field1 + ", " + field2;
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
	
	public boolean isTeamIDExists(int teamID) {
	    boolean exists = false;
	    try {
	        Connection c = JDBCUtil.getConnection();
	        String sql = "SELECT 1 FROM team WHERE team_id = ?";
	        PreparedStatement pst = c.prepareStatement(sql);
	        pst.setInt(1, teamID);
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
