package view;

import java.awt.BorderLayout;

import Charts.AffiliationChart;
import Charts.AgentChart;
import Charts.AgentSalaryChart;
import Charts.MissionChart;
import Charts.SkillChart;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class ChartDashboard extends JFrame {
    private JPanel mainPanel;
    private JLabel backgroundLabel;
    private ImageIcon backgroundIcon;
    public ChartDashboard() {
        setTitle("Statistical Charts");
        setSize(1200, 800);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);
        
        URL urlIconKeylock = getClass().getResource("/icon/chart-icon.png");
        if (urlIconKeylock != null) {
            Image img = Toolkit.getDefaultToolkit().createImage(urlIconKeylock);
            this.setIconImage(img);
        }

        String[] chartList = {
            "Agent Distribution by Country",
            "Average Salary by Country",
            "Mission Status by Location",
            "Number of Agents per Skill",
            "Top 10 Organizations by Number of Agents",
        };

        mainPanel = new JPanel(new BorderLayout());

        backgroundIcon = new ImageIcon(getClass().getResource("/icon/ChartDashboard.jpg"));
        backgroundLabel = new JLabel(backgroundIcon); 
        backgroundLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundLabel.setVerticalAlignment(SwingConstants.CENTER);
        mainPanel.add(backgroundLabel, BorderLayout.CENTER);

        JList<String> chartMenu = new JList<>(chartList);
        chartMenu.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
        chartMenu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        chartMenu.setBackground(new Color(173, 216, 230)); 
        chartMenu.setSelectionBackground(new Color(135, 206, 250)); 
        chartMenu.setSelectionForeground(Color.BLACK);
        chartMenu.setFixedCellHeight(40);

        DefaultListCellRenderer renderer = (DefaultListCellRenderer) chartMenu.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER); 

        JScrollPane listScrollPane = new JScrollPane(chartMenu);
        listScrollPane.setBorder(BorderFactory.createEmptyBorder()); 


        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS)); 
        leftPanel.setBackground(new Color(173, 216, 230)); 

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(173, 216, 230));
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JLabel chartCategoryLabel = new JLabel("Chart Categories");
        chartCategoryLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        chartCategoryLabel.setForeground(new Color(0, 0, 139)); 
        chartCategoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 

        headerPanel.add(javax.swing.Box.createHorizontalGlue()); 
        headerPanel.add(chartCategoryLabel);
        headerPanel.add(javax.swing.Box.createHorizontalGlue()); 

        leftPanel.add(headerPanel);
        leftPanel.add(listScrollPane);

        JLabel welcomeLabel = new JLabel("Welcome to ChartDashboard");
        welcomeLabel.setFont(new Font("Segoe UI", Font.ITALIC, 20));
        welcomeLabel.setForeground(new Color(0, 0, 128)); 
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        leftPanel.add(welcomeLabel);
        
        JButton backButton = new JButton("Back to Main Dashboard");
        backButton.setFocusPainted(false);
        backButton.setBackground(new Color(100, 149, 237)); 
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        backButton.addActionListener(e -> {
            dispose(); 
            new Dashboard2().setVisible(true); 
        });

        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(backButton);
        
        getContentPane().add(leftPanel, BorderLayout.WEST);
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        chartMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = chartMenu.locationToIndex(e.getPoint());
                Rectangle cellBounds = (index == -1) ? null : chartMenu.getCellBounds(index, index);

                if (index != -1 && cellBounds != null && cellBounds.contains(e.getPoint())) {
                    String selectedValue = chartMenu.getModel().getElementAt(index);
                    showChart(selectedValue);
                } else {
                    chartMenu.clearSelection(); 
                    mainPanel.removeAll();
                    mainPanel.setLayout(new BorderLayout());
                    mainPanel.add(backgroundLabel, BorderLayout.CENTER);
                    mainPanel.revalidate();
                    mainPanel.repaint();
                }
            }
        });


    }

    private void showChart(String selected) {
        mainPanel.removeAll();

        switch (selected) {
            case "Agent Distribution by Country":
                AgentChart agentChart = new AgentChart();
                mainPanel.add(agentChart, BorderLayout.CENTER);
                break;

            case "Average Salary by Country":
                AgentSalaryChart agentSalChart = new AgentSalaryChart();
                mainPanel.add(agentSalChart, BorderLayout.CENTER);
                break;

            case "Mission Status by Location":
                MissionChart missionChart = new MissionChart();
                mainPanel.add(missionChart, BorderLayout.CENTER);
                break;

            case "Number of Agents per Skill":
                SkillChart skillChart = new SkillChart();
                mainPanel.add(skillChart, BorderLayout.CENTER);
                break;

            case "Top 10 Organizations by Number of Agents":
                AffiliationChart affiliChart = new AffiliationChart();
                mainPanel.add(affiliChart, BorderLayout.CENTER);
                break;

            default:
                mainPanel.add(new JLabel("This chart is under development..."), BorderLayout.CENTER);
        }

        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new ChartDashboard().setVisible(true);
        });
    }
}