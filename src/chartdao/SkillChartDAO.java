package chartdao;

import config.JDBCUtil;
import java.sql.*;
import java.util.ArrayList;

public class SkillChartDAO {

    public ArrayList<Object[]> countAgentsPerSkill() {
        ArrayList<Object[]> list = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT s.skill_name, COUNT(sr.agent_id) " +
                         "FROM skill s " +
                         "LEFT JOIN skillrel sr ON s.skill_id = sr.skill_id " +
                         "GROUP BY s.skill_name";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String skillName = rs.getString(1);
                int agentCount = rs.getInt(2);
                list.add(new Object[]{skillName, agentCount});
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
