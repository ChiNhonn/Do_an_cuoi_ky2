package dao;

import java.util.ArrayList;

import config.JDBCUtil;
import model.Agent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AgentDAO implements DAOInterface<Agent> {

	public static AgentDAO getInstance() {
		return new AgentDAO();
	}
	
	@Override
	public int insert(Agent t) {
		int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "INSERT INTO agent(agent_id, first_name, last_name, address, city, country, salary) VALUES(?, ?, ?, ?, ?, ?, ?)";
            	PreparedStatement pst = c.prepareCall(sql);
            	pst.setInt(1, t.getAgent_ID());
            	pst.setString(2, t.getFirst_name());
            	pst.setString(3, t.getLast_name());
            	pst.setString(4, t.getAddrest());
            	pst.setString(5, t.getCity());
            	pst.setString(6, t.getCountry());
            	pst.setFloat(7, t.getSalary());
            	result = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
	@Override
	public int update(Agent t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "UPDATE agent SET first_name = ?, last_name = ?, address = ?, city = ?, country = ?, salary = ? WHERE agent_id = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getFirst_name());
			pst.setString(2, t.getLast_name());
			pst.setString(3, t.getAddrest());
			pst.setString(4, t.getCity());
			pst.setString(5, t.getCountry());
			pst.setFloat(6, t.getSalary());
			pst.setInt(7, t.getAgent_ID());
			result = pst.executeUpdate(); 
			pst.close();
            JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(Agent t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "DELETE FROM agent WHERE agent_id=?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getAgent_ID());
            result = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

	@Override
	public ArrayList<Agent> selectALL() {
		ArrayList<Agent> list = new ArrayList<Agent>();
        try {
			Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM agent";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            		int id = rs.getInt("agent_id");
            		String first_name = rs.getString("first_name");
            		String last_name = rs.getString("last_name");
            		String address = rs.getString("address");
            		String city = rs.getString("city");
            		String country = rs.getString("country");
            		float salary = rs.getFloat("salary");
            		Agent agent = new Agent(id, first_name, last_name, address, city, country, salary);
                list.add(agent);
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
	public Agent selectByID(Agent t) {
		Agent result = null;
	        try {
				Connection c = JDBCUtil.getConnection();
				String sql = "SELECT * FROM agent WHERE agent_id = ?";
	            PreparedStatement pst = c.prepareStatement(sql);
	            pst.setInt(1, t.getAgent_ID());
	            ResultSet rs = pst.executeQuery();
	            while (rs.next()) {
	        		int id = rs.getInt("agent_id");
	        		String first_name = rs.getString("first_name");
	        		String last_name = rs.getString("last_name");
	        		String address = rs.getString("address");
	        		String city = rs.getString("city");
	        		String country = rs.getString("country");
	        		float salary = rs.getFloat("salary");
	        		result = new Agent(id, first_name, last_name, address, city, country, salary);
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
	public ArrayList<Agent> selectByCondition(String Conition) {
		ArrayList<Agent> list = new ArrayList<Agent>();
        try {
			Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM agent WHERE " + Conition;
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            		int id = rs.getInt("agent_id");
            		String first_name = rs.getString("first_name");
            		String last_name = rs.getString("last_name");
            		String address = rs.getString("address");
            		String city = rs.getString("city");
            		String country = rs.getString("country");
            		float salary = rs.getFloat("salary");
            		Agent agent = new Agent(id, first_name, last_name, address, city, country, salary);
                list.add(agent);
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
	public ArrayList<Agent> search(String keyword) {
	    ArrayList<Agent> list = new ArrayList<>();
	    try {
	        Connection c = JDBCUtil.getConnection();
	        String sql = "SELECT * FROM agent WHERE " +
	                "CAST(agent_id AS CHAR) LIKE ? OR " +
	                "first_name LIKE ? OR " +
	                "last_name LIKE ? OR " +
	                "city LIKE ? OR " +
	                "country LIKE ? OR " +
	                "address LIKE ?";
	        PreparedStatement pst = c.prepareStatement(sql);
	        String likeKeyword = "%" + keyword + "%";
	        pst.setString(1, likeKeyword);
	        pst.setString(2, likeKeyword);
	        pst.setString(3, likeKeyword);
	        pst.setString(4, likeKeyword);
	        pst.setString(5, likeKeyword);
	        pst.setString(6, likeKeyword); 
	        
	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	            int id = rs.getInt("agent_id");
	            String first_name = rs.getString("first_name");
	            String last_name = rs.getString("last_name");
	            String address = rs.getString("address");
	            String city = rs.getString("city");
	            String country = rs.getString("country");
	            float salary = rs.getFloat("salary");
	            Agent agent = new Agent(id, first_name, last_name, address, city, country, salary);
	            list.add(agent);
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
	public boolean exists(Agent t) {
		boolean result = false;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM agent WHERE (agent_id = ?) OR (first_name = ? AND last_name = ?)";
	        PreparedStatement pst = c.prepareStatement(sql);
	        pst.setInt(1, t.getAgent_ID());
	        pst.setString(2, t.getFirst_name());
	        pst.setString(3, t.getLast_name());
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
            String sql = "SELECT COUNT(*) FROM agent";
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
	        String sql = "SELECT " + field1 + ", " + field2 + ", COUNT(*) FROM agent GROUP BY " + field1 + ", " + field2;
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
	
	public boolean isAgentIDExists(int agentID) {
	    boolean exists = false;
	    try {
	        Connection c = JDBCUtil.getConnection();
	        String sql = "SELECT 1 FROM agent WHERE agent_id = ?";
	        PreparedStatement pst = c.prepareStatement(sql);
	        pst.setInt(1, agentID);
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
