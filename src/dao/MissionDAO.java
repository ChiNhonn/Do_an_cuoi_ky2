package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.JDBCUtil;
import model.Mission;

public class MissionDAO implements DAOInterface<Mission> {

	public static MissionDAO getInstance() {
		return new MissionDAO();
	}
	
	@Override
	public int insert(Mission t) {
		int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "INSERT INTO mission(mission_id, mission_name, location, agent_id, access_id, team_id, mission_status) VALUES(?, ?, ?, ?, ?, ?, ?)";
            	PreparedStatement pst = c.prepareStatement(sql);
            	pst.setInt(1, t.getMission_ID());
            	pst.setString(2, t.getMission_name());
            	pst.setString(3, t.getLocation());
            	pst.setInt(4, t.getAgent_ID());
            	pst.setInt(5, t.getAccess_ID());
            	pst.setInt(6, t.getTeam_ID());
            	pst.setString(7, t.getMission_status());
            	result = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
	}

	@Override
	public int update(Mission t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
	        String sql = "UPDATE mission SET mission_name = ?, location = ?, agent_id = ?, access_id = ?, team_id = ?, mission_status = ? WHERE mission_id = ?";
	        PreparedStatement pst = c.prepareStatement(sql);
	        pst.setString(1, t.getMission_name());
	        pst.setString(2, t.getLocation());
	        pst.setInt(3, t.getAgent_ID());
	        pst.setInt(4, t.getAccess_ID());
	        pst.setInt(5, t.getTeam_ID());
	        pst.setString(6, t.getMission_status());
	        pst.setInt(7, t.getMission_ID());
	        result = pst.executeUpdate();
	        pst.close();
	        JDBCUtil.closeConnection(c);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}

	@Override
	public int delete(Mission t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "DELETE FROM mission WHERE mission_id=?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getMission_ID());
            result = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Mission> selectALL() {
        ArrayList<Mission> list = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM mission";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Mission a = new Mission(
                    rs.getInt("mission_id"),
                    rs.getString("mission_name"),
                    rs.getString("location"),
                    rs.getInt("agent_id"),
                    rs.getInt("access_id"),
                    rs.getInt("team_id"),
                    rs.getString("mission_status")
                );
                list.add(a);
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
    public boolean exists(Mission t) {
        boolean exists = false;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM mission WHERE mission_id = ? OR mission_name = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getMission_ID());
            pst.setString(2, t.getMission_name());
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

    public ArrayList<Mission> search(String keyword) {
        ArrayList<Mission> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM mission WHERE " +
                "CAST(mission_id AS CHAR) LIKE ? OR " +
                "mission_name LIKE ? OR " +
                "location LIKE ? OR " +
                "CAST(agent_id AS CHAR) LIKE ? OR " +
                "CAST(access_id AS CHAR) LIKE ? OR " +
                "CAST(team_id AS CHAR) LIKE ? OR " +
                "mission_status LIKE ?";
            pst = c.prepareStatement(sql);

            String searchKey = "%" + keyword + "%";
            // set tham số 7 lần
            for (int i = 1; i <= 7; i++) {
                pst.setString(i, searchKey);
            }

            rs = pst.executeQuery();
            while (rs.next()) {
                Mission a = new Mission(
                    rs.getInt("mission_id"),
                    rs.getString("mission_name"),
                    rs.getString("location"),
                    rs.getInt("agent_id"),
                    rs.getInt("access_id"),
                    rs.getInt("team_id"),
                    rs.getString("mission_status")
                );
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (c != null) JDBCUtil.closeConnection(c);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public ArrayList<Object[]> countGroupByTwoFields(String field1, String field2) {
        ArrayList<Object[]> list = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT `" + field1 + "`, `" + field2 + "`, COUNT(*) FROM mission GROUP BY `" + field1 + "`, `" + field2 + "`";
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

	@Override
	public Mission selectByID(Mission t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Mission> selectByCondition(String Conition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count() {
		int result = 0;
        try {
			Connection c = JDBCUtil.getConnection();
            String sql = "SELECT COUNT(*) FROM  mission";
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

}
