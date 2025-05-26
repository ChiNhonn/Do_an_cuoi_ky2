package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class Dashboard2 extends JFrame {
    private JPanel mainPanel;

    public Dashboard2() {
        setTitle("SpyAgency Management");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(1200, 800);
        setLocationRelativeTo(null); 
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());

        
        URL urlIconKeylock = getClass().getResource("/icon/Spy_icon.png");
        if (urlIconKeylock != null) {
            Image img = Toolkit.getDefaultToolkit().createImage(urlIconKeylock);
            this.setIconImage(img);
        }
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenu menuCharts = new JMenu("Charts");
        JMenuItem exitItem = new JMenuItem("Exit", loadIcon("/icon/exit_icon.png", 20, 20));
        JMenuItem logoutItem = new JMenuItem("Logout", loadIcon("/icon/logout_icon.png", 20, 20));
        JMenuItem openChartDashboard = new JMenuItem("Open Chart Dashboard", loadIcon("/icon/chart-icon.png", 20, 20));


        menuFile.addSeparator();
        menuFile.add(exitItem);
        menuFile.addSeparator();
        menuFile.add(logoutItem);

        menuCharts.add(openChartDashboard);

        menuBar.add(menuFile);
        menuBar.add(menuCharts);
        setJMenuBar(menuBar);

        mainPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/icon/dashboard.jpg"));
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundLabel.setVerticalAlignment(SwingConstants.CENTER);
        mainPanel.removeAll();
        mainPanel.add(backgroundLabel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();

        exitItem.addActionListener(e -> System.exit(0));

        logoutItem.addActionListener(e -> {
            dispose();
            new LoginInterface2().setVisible(true);
        });

        openChartDashboard.addActionListener(e -> {
            dispose();
            new ChartDashboard().setVisible(true);
        });

        String[] managerList = {
            "affiliation", "affiliationrel", "agent", "language",
            "languagerel", "mission", "securityclearance", "skill",
            "skillrel", "team", "teamrel", "account"
        };

        JList<String> manager = new JList<>(managerList);
        manager.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        manager.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        manager.setBackground(new Color(173, 216, 230)); 
        manager.setForeground(Color.BLACK);
        manager.setSelectionBackground(new Color(135, 206, 250));
        manager.setSelectionForeground(Color.BLACK);
        manager.setFixedCellHeight(40);
        manager.setAlignmentX(Component.CENTER_ALIGNMENT); 

        DefaultListCellRenderer renderer = (DefaultListCellRenderer) manager.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER); 

        JScrollPane listScrollPane = new JScrollPane(manager);
        listScrollPane.setPreferredSize(new Dimension(220, getHeight()));
        listScrollPane.setBorder(BorderFactory.createEmptyBorder());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS)); 
        leftPanel.setBackground(new Color(173, 216, 230));

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(173, 216, 230));
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JLabel adminLabel = new JLabel("Hi Admin");
        adminLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        adminLabel.setForeground(new Color(0, 0, 139));
        adminLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 

        headerPanel.add(Box.createHorizontalGlue());
        headerPanel.add(adminLabel);
        headerPanel.add(Box.createHorizontalGlue());

        leftPanel.add(headerPanel);
        leftPanel.add(listScrollPane);

        getContentPane().add(leftPanel, BorderLayout.WEST);


        manager.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = manager.locationToIndex(e.getPoint());
                Rectangle cellBounds = (index == -1) ? null : manager.getCellBounds(index, index);
                if (index != -1 && cellBounds != null && cellBounds.contains(e.getPoint())) {
                    String selectedValue = manager.getModel().getElementAt(index);
                    showPanel(selectedValue); 
                } else {
                    mainPanel.removeAll();
                    mainPanel.setLayout(new BorderLayout());
                    mainPanel.add(backgroundLabel, BorderLayout.CENTER);
                    mainPanel.revalidate();
                    mainPanel.repaint();
                }
            }
        });
    }

    private void showPanel(String value) {
        mainPanel.removeAll();
        switch (value) {
            case "affiliation":
                AffiliationManagementJPanel affView = new AffiliationManagementJPanel();
                new controller.AffiliationController(affView);
                mainPanel.add(affView, BorderLayout.CENTER);
                break;
            case "affiliationrel":
                AffiliationrelManagementJPanel affRelView = new AffiliationrelManagementJPanel();
                new controller.AffiliationrelController(affRelView);
                mainPanel.add(affRelView, BorderLayout.CENTER);
                break;
            case "agent":
                AgentManagementJPanel agentView = new AgentManagementJPanel();
                new controller.AgentController(agentView);
                mainPanel.add(agentView, BorderLayout.CENTER);
                break;
            case "language":
                LanguageManagementJPanel langView = new LanguageManagementJPanel();
                new controller.LanguageController(langView);
                mainPanel.add(langView, BorderLayout.CENTER);
                break;
            case "languagerel":
                LanguagerelManagementJPanel langrelView = new LanguagerelManagementJPanel();
                new controller.LanguagerelController(langrelView);
                mainPanel.add(langrelView, BorderLayout.CENTER);
                break;
            case "mission":
                MissionManagementJPanel missView = new MissionManagementJPanel();
                new controller.MissionController(missView);
                mainPanel.add(missView, BorderLayout.CENTER);
                break;
            case "securityclearance":
                SecurityclearanceManagementJPanel scView = new SecurityclearanceManagementJPanel();
                new controller.SecurityclearanceController(scView);
                mainPanel.add(scView, BorderLayout.CENTER);
                break;
            case "skill":
                SkillManagementJPanel skillView = new SkillManagementJPanel();
                new controller.SkillController(skillView);
                mainPanel.add(skillView, BorderLayout.CENTER);
                break;
            case "skillrel":
                SkillrelManagementJPanel skillrelView = new SkillrelManagementJPanel();
                new controller.SkillrelController(skillrelView);
                mainPanel.add(skillrelView, BorderLayout.CENTER);
                break;
            case "team":
                TeamManagementJPanel teamView = new TeamManagementJPanel();
                new controller.TeamController(teamView);
                mainPanel.add(teamView, BorderLayout.CENTER);
                break;
            case "teamrel":
                TeamrelManagementJPanel teamrelView = new TeamrelManagementJPanel();
                new controller.TeamrelController(teamrelView);
                mainPanel.add(teamrelView, BorderLayout.CENTER);
                break;
            case "account":
                AccountManagementJPanel accView = new AccountManagementJPanel();
                new controller.AccountController(accView);
                mainPanel.add(accView, BorderLayout.CENTER);
                break;
            default:
                mainPanel.add(new JLabel("Chức năng đang phát triển: " + value), BorderLayout.CENTER);
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
    private ImageIcon loadIcon(String path, int width, int height) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(image);
        } else {
            System.err.println("Không tìm thấy icon: " + path);
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Dashboard2().setVisible(true));
    }
}
