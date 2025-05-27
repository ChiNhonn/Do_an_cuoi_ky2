package Charts;

import chartdao.AgentChartDAO;
import org.jfree.chart.*;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class AgentChart extends JPanel {
    private DefaultPieDataset dataset;
    private AgentChartDAO dao;

    public AgentChart() {
        dao = new AgentChartDAO();
        dataset = new DefaultPieDataset();
        loadData();

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Agent Distribution by Country",
                dataset,
                true, 
                true,
                false);

        pieChart.setBackgroundPaint(Color.white);
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setBackgroundPaint(new Color(245, 245, 245));
        plot.setOutlineVisible(false);
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setCircular(true);
        plot.setLabelGap(0.02);
        plot.setLabelBackgroundPaint(Color.WHITE);
        plot.setLabelShadowPaint(null);
        plot.setLabelOutlinePaint(null);

        pieChart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 20));

        ChartPanel chartPanel = new ChartPanel(pieChart);
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
        add(controlPanel, BorderLayout.NORTH);
        add(chartPanel, BorderLayout.CENTER);
    }

    private void loadData() {
        dataset.clear();
        var data = dao.countByCountry();
        for (Object[] row : data) {
            String country = String.valueOf(row[0]);
            Number count = (Number) row[1];
            dataset.setValue(country, count);
        }
    }

}
