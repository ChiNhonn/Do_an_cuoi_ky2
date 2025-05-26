package Charts;

import chartdao.MissionChartDAO;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class MissionChart extends JPanel {

    private DefaultCategoryDataset dataset;
    private MissionChartDAO dao;

    public MissionChart() {
        dao = new MissionChartDAO();
        dataset = new DefaultCategoryDataset();
        loadData();

        JFreeChart chart = ChartFactory.createStackedBarChart(
                "Mission Status by Location",
                "Location",
                "Number of Missions",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        chart.setBackgroundPaint(Color.white);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(245, 245, 245));
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.gray);

        StackedBarRenderer renderer = (StackedBarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);

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
        add(chartPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.NORTH);
    }

    private void loadData() {
        dataset.clear();
        ArrayList<Object[]> data = dao.countStatusByLocation();
        for (Object[] row : data) {
            String location = (String) row[0];
            String status = (String) row[1];
            int count = (int) row[2];
            dataset.addValue(count, status, location);
        }
    }
}
