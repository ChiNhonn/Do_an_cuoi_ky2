package chartdao;

import config.JDBCUtil;
import java.sql.*;
import java.util.ArrayList;

public class AffiliationChartDAO {

    public ArrayList<Object[]> getTop10AffiliationsByAgentCount() {
        ArrayList<Object[]> list = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = """
                SELECT a.affiliation_name, COUNT(ar.agent_id) AS total_agents
                FROM affiliationrel ar
                JOIN affiliation a ON ar.affiliation_id = a.affiliation_id
                GROUP BY a.affiliation_name
                ORDER BY total_agents DESC
                LIMIT 10
            """;

            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String affiliationName = rs.getString("affiliation_name");
                int totalAgents = rs.getInt("total_agents");
                list.add(new Object[]{affiliationName, totalAgents});
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
