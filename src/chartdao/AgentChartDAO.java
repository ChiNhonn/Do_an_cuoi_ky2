package chartdao;

import config.JDBCUtil;
import java.sql.*;
import java.util.ArrayList;

public class AgentChartDAO {

    public ArrayList<Object[]> countByCountry() {
        ArrayList<Object[]> list = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT country, COUNT(*) FROM agent GROUP BY country";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String country = rs.getString(1);
                int count = rs.getInt(2);
                list.add(new Object[]{country, count});
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
