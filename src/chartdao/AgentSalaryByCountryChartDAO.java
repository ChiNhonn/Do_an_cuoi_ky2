package chartdao;

import config.JDBCUtil;
import java.sql.*;
import java.util.ArrayList;

public class AgentSalaryByCountryChartDAO {

    public ArrayList<Object[]> averageSalaryByCountry() {
        ArrayList<Object[]> list = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT country, AVG(salary) FROM agent GROUP BY country";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String country = rs.getString(1);
                double avgSalary = rs.getDouble(2);
                list.add(new Object[]{country, avgSalary});
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
