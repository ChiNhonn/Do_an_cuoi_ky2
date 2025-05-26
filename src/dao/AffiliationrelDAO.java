package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.JDBCUtil;
import model.Affiliationrel;

public class AffiliationrelDAO implements DAOInterface<Affiliationrel> {

    public static AffiliationrelDAO getInstance() {
        return new AffiliationrelDAO();
    }

    @Override
    public int insert(Affiliationrel t) {
        int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "INSERT INTO affiliationrel(agent_id, affiliation_id, affiliation_strength) VALUES(?, ?, ?)";
            PreparedStatement pst = c.prepareStatement(sql);

            pst.setInt(1, t.getAgent_ID());
            pst.setInt(2, t.getAffiliation_ID());
            pst.setString(3, t.getAffiliation_strength());

            result = pst.executeUpdate();

            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(Affiliationrel t) {
        int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "UPDATE affiliationrel SET affiliation_strength = ? WHERE agent_id = ? AND affiliation_id = ?";
            PreparedStatement pst = c.prepareStatement(sql);

            pst.setString(1, t.getAffiliation_strength());
            pst.setInt(2, t.getAgent_ID());
            pst.setInt(3, t.getAffiliation_ID());

            result = pst.executeUpdate();

            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(Affiliationrel t) {
        int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "DELETE FROM affiliationrel WHERE agent_id = ? AND affiliation_id = ?";
            PreparedStatement pst = c.prepareStatement(sql);

            pst.setInt(1, t.getAgent_ID());
            pst.setInt(2, t.getAffiliation_ID());

            result = pst.executeUpdate();

            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Affiliationrel> selectALL() {
        ArrayList<Affiliationrel> list = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM affiliationrel";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int agent_id = rs.getInt("agent_id");
                int affiliation_id = rs.getInt("affiliation_id");
                String affiliation_strength = rs.getString("affiliation_strength");
                Affiliationrel affiliationrel = new Affiliationrel(affiliation_id, agent_id, affiliation_strength);
                list.add(affiliationrel);
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
    public Affiliationrel selectByID(Affiliationrel t) {
        Affiliationrel result = null;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM affiliationrel WHERE agent_id = ? AND affiliation_id = ?";
            PreparedStatement pst = c.prepareStatement(sql);

            pst.setInt(1, t.getAgent_ID());
            pst.setInt(2, t.getAffiliation_ID());

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String affiliation_strength = rs.getString("affiliation_strength");
                result = new Affiliationrel(t.getAffiliation_ID(), t.getAgent_ID(), affiliation_strength);
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
    public ArrayList<Affiliationrel> selectByCondition(String condition) {
        ArrayList<Affiliationrel> list = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM affiliationrel WHERE " + condition;
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int agent_id = rs.getInt("agent_id");
                int affiliation_id = rs.getInt("affiliation_id");
                String affiliation_strength = rs.getString("affiliation_strength");
                Affiliationrel affiliationrel = new Affiliationrel(affiliation_id, agent_id, affiliation_strength);
                list.add(affiliationrel);
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
    public ArrayList<Affiliationrel> search(String keyword) {
        ArrayList<Affiliationrel> list = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM affiliationrel " +
                         "WHERE CAST(agent_id AS CHAR) LIKE ? " +
                         "OR CAST(affiliation_id AS CHAR) LIKE ? " +
                         "OR affiliation_strength LIKE ?";
            PreparedStatement pst = c.prepareStatement(sql);
            String likeKeyword = "%" + keyword + "%";
            pst.setString(1, likeKeyword);
            pst.setString(2, likeKeyword);
            pst.setString(3, likeKeyword);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int agentID = rs.getInt("agent_id");
                int affiliationID = rs.getInt("affiliation_id");
                String strength = rs.getString("affiliation_strength");
                list.add(new Affiliationrel(affiliationID, agentID, strength));
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
    public boolean exists(Affiliationrel t) {
        boolean result = false;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM affiliationrel WHERE agent_id = ? AND affiliation_id = ?";
            PreparedStatement pst = c.prepareStatement(sql);

            pst.setInt(1, t.getAgent_ID());
            pst.setInt(2, t.getAffiliation_ID());

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
        int total = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT COUNT(*) FROM affiliationrel";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
            rs.close();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    @Override
    public ArrayList<Object[]> countGroupByTwoFields(String field1, String field2) {
        ArrayList<Object[]> list = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT " + field1 + ", " + field2 + ", COUNT(*) FROM affiliationrel GROUP BY " + field1 + ", " + field2;
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Object[] row = new Object[3];
                row[0] = rs.getObject(field1);
                row[1] = rs.getObject(field2);
                row[2] = rs.getInt(3);
                list.add(row);
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
