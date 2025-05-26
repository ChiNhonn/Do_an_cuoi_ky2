package chartdao;

import config.JDBCUtil;
import java.sql.*;
import java.util.ArrayList;

public class MissionChartDAO {
    public ArrayList<Object[]> countStatusByLocation() {
        ArrayList<Object[]> list = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT location, mission_status, COUNT(*) FROM mission GROUP BY location, mission_status";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String location = rs.getString(1);
                String status = rs.getString(2);
                int count = rs.getInt(3);
                list.add(new Object[]{location, status, count});
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
