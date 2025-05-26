package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MissionManagementJPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tablemodel;
    private JTextField txtMissionID, txtMissionName, txtLocation, txtAgentID, txtAccessID, txtTeamID, txtMissionStatus, txtSearch;
    private JLabel lblCount;
    private JButton btnAdd, btnUpdate, btnDelete, btnRefresh, btnSearch;
    private JComboBox<String> comboField1, comboField2;
    private JButton btnGroupStats;

    public MissionManagementJPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Thanh công cụ
        JPanel toolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        toolPanel.setBorder(BorderFactory.createTitledBorder("Function"));

        btnAdd = createIconButton("Add", "/icon/add.png");
        btnUpdate = createIconButton("Update", "/icon/update.png");
        btnDelete = createIconButton("Delete", "/icon/delete.png");
        btnSearch = createIconButton("Search", "/icon/search.png");
        txtSearch = new JTextField(20);
        btnRefresh = createIconButton("Refresh", "/icon/refresh.png");

        toolPanel.add(btnAdd);
        toolPanel.add(btnUpdate);
        toolPanel.add(btnDelete);
        toolPanel.add(btnSearch);
        toolPanel.add(txtSearch);
        toolPanel.add(btnRefresh);

        // Panel group stats option (bạn có thể bỏ nếu muốn)
        JPanel groupPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        groupPanel.setBorder(BorderFactory.createTitledBorder("Group Statistics Option"));

        btnGroupStats = createIconButton("Group Stats", "/icon/groupIcon.png");
        comboField1 = new JComboBox<>(new String[]{"mission_id", "mission_name", "location", "agent_id", "access_id", "team_id", "mission_status"});
        comboField2 = new JComboBox<>(new String[]{"mission_id", "mission_name", "location", "agent_id", "access_id", "team_id", "mission_status"});

        groupPanel.add(btnGroupStats);
        groupPanel.add(new JLabel("Group by:"));
        groupPanel.add(comboField1);
        groupPanel.add(comboField2);

        // Form nhập liệu
        JPanel inputPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Mission Information"));

        txtMissionID = new JTextField();
        txtMissionName = new JTextField();
        txtLocation = new JTextField();
        txtAgentID = new JTextField();
        txtAccessID = new JTextField();
        txtTeamID = new JTextField();
        txtMissionStatus = new JTextField();

        inputPanel.add(new JLabel("Mission ID:"));       inputPanel.add(txtMissionID);
        inputPanel.add(new JLabel("Mission Name:"));     inputPanel.add(txtMissionName);
        inputPanel.add(new JLabel("Location:"));         inputPanel.add(txtLocation);
        inputPanel.add(new JLabel("Agent ID:"));         inputPanel.add(txtAgentID);
        inputPanel.add(new JLabel("Access ID:"));        inputPanel.add(txtAccessID);
        inputPanel.add(new JLabel("Team ID:"));          inputPanel.add(txtTeamID);
        inputPanel.add(new JLabel("Mission Status:"));   inputPanel.add(txtMissionStatus);
        inputPanel.add(new JLabel());  inputPanel.add(new JLabel()); // filler

        // Gộp top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(toolPanel);
        topPanel.add(groupPanel);  // bạn muốn bỏ thì comment dòng này
        topPanel.add(inputPanel);

        add(topPanel, BorderLayout.NORTH);

        // Bảng mission list
        tablemodel = new DefaultTableModel(
                new String[]{"Mission ID", "Mission Name", "Location", "Agent ID", "AccessID", "TeamID", "Mission Status"}, 0);
        table = new JTable(tablemodel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Mission List"));

        // Panel trung tâm chứa bảng và label count
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        lblCount = new JLabel("Total: 0");
        JPanel countPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        countPanel.add(lblCount);

        centerPanel.add(countPanel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);
    }

    private JButton createIconButton(String text, String iconPath) {
        java.net.URL imgURL = getClass().getResource(iconPath);
        JButton btn = new JButton();

        if (imgURL != null) {
            btn.setIcon(new ImageIcon(imgURL));
        } else {
            btn.setText("X");
        }

        btn.setText(text);
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setContentAreaFilled(true);
                btn.setBackground(new Color(220, 220, 220));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setContentAreaFilled(false);
            }
        });

        return btn;
    }

    // Getter (nếu cần)
    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tablemodel; }
    public JTextField getTxtMissionID() { return txtMissionID; }
    public JTextField getTxtMissionName() { return txtMissionName; }
    public JTextField getTxtLocation() { return txtLocation; }
    public JTextField getTxtAgentID() { return txtAgentID; }
    public JTextField getTxtAccessID() { return txtAccessID; }
    public JTextField getTxtTeamID() { return txtTeamID; }
    public JTextField getTxtMissionStatus() { return txtMissionStatus; }
    public JTextField getTxtSearch() { return txtSearch; }
    public JLabel getLblCount() { return lblCount; }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnUpdate() { return btnUpdate; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnRefresh() { return btnRefresh; }
    public JButton getBtnSearch() { return btnSearch; }
    public JComboBox<String> getComboField1() { return comboField1; }
    public JComboBox<String> getComboField2() { return comboField2; }
    public JButton getBtnGroupStats() { return btnGroupStats; }
}
