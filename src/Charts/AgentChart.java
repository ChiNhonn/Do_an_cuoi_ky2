package Charts;

import chartdao.AgentChartDAO;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class AgentChart extends JPanel {
    private DefaultCategoryDataset dataset;
    private AgentChartDAO dao;

    public AgentChart() {
        dao = new AgentChartDAO();
        dataset = new DefaultCategoryDataset();
        loadData(); 

        JFreeChart barChart = ChartFactory.createBarChart(
                "Agent Count by Country",
                "Country",
                "Number of Agents",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        barChart.setBackgroundPaint(Color.white);
        CategoryPlot plot = barChart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(245, 245, 245)); 
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.gray);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(79, 129, 189)); 
        renderer.setMaximumBarWidth(0.08);

        barChart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 20));
        plot.getDomainAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 14));
        plot.getRangeAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 14));
        plot.getDomainAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.getRangeAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setPreferredSize(new Dimension(800, 500));

        ImageIcon refreshIcon = new ImageIcon(getClass().getResource("/icon/refresh.png"));
        Image image = refreshIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        refreshIcon = new ImageIcon(image);
        JButton refreshButton = new JButton("Refresh", refreshIcon);
        refreshButton.setHorizontalTextPosition(SwingConstants.CENTER); // Chữ canh giữa
        refreshButton.setVerticalTextPosition(SwingConstants.BOTTOM); // Chữ nằm dưới icon
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
        add(controlPanel, BorderLayout.NORTH);
        add(chartPanel, BorderLayout.CENTER);
    }

    private void loadData() {
        dataset.clear();
        var data = dao.countByCountry();
        for (Object[] row : data) {
            dataset.addValue((int) row[1], "Agents", (String) row[0]);
        }
    }
}
