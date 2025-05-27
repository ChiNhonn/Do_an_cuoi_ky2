package Charts;

import chartdao.AgentSalaryByCountryChartDAO;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AgentSalaryChart extends JPanel {

    private DefaultCategoryDataset dataset;
    private AgentSalaryByCountryChartDAO dao;

    public AgentSalaryChart() {
        dao = new AgentSalaryByCountryChartDAO();
        dataset = new DefaultCategoryDataset();
        loadData();

        JFreeChart chart = ChartFactory.createBarChart(
                "Average Agent Salary by Country",
                "Avg Salary (USD)",   
                "Country",            
                dataset,
                PlotOrientation.HORIZONTAL, 
                false, true, false);

        chart.setBackgroundPaint(Color.white);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(245, 245, 245));
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.gray);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(60, 179, 113)); 
        renderer.setMaximumBarWidth(0.08);

        chart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 20));
        plot.getDomainAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 14));
        plot.getRangeAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 14));
        plot.getDomainAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.getRangeAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setPreferredSize(new Dimension(800, 500));

        ImageIcon refreshIcon = new ImageIcon(getClass().getResource("/icon/refresh.png"));
        Image image = refreshIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        refreshIcon = new ImageIcon(image);
        JButton refreshButton = new JButton("Refresh", refreshIcon);
        refreshButton.setHorizontalTextPosition(SwingConstants.CENTER);
        refreshButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        refreshButton.setFocusPainted(false);
        refreshButton.setBackground(new Color(100, 149, 237));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        refreshButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        refreshButton.addActionListener((ActionEvent e) -> {
            loadData();
        });

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        controlPanel.add(refreshButton);

        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.NORTH);
    }

    private void loadData() {
        dataset.clear();
        var data = dao.averageSalaryByCountry();
        for (Object[] row : data) {
            dataset.addValue((Double) row[1], "Salary", (String) row[0]);
        }
    }
}
