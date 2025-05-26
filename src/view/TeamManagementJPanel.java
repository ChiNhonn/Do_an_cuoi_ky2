package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TeamManagementJPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtTeamID, txtTeamName, txtMeetingFrequency, txtSearch;
    private JLabel lblCount;
    private JButton btnAdd, btnUpdate, btnDelete, btnRefresh, btnSearch, btnGroupStats;
    private JComboBox<String> comboField1, comboField2;

    public TeamManagementJPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // ==== Hàng 1: Thanh chức năng ====
        JPanel functionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        functionPanel.setBorder(BorderFactory.createTitledBorder("Function"));

        btnAdd = createIconButton("Add", "/icon/add.png");
        btnUpdate = createIconButton("Update", "/icon/update.png");
        btnDelete = createIconButton("Delete", "/icon/delete.png");
        btnSearch = createIconButton("Search", "/icon/search.png");
        txtSearch = new JTextField(15);
        btnRefresh = createIconButton("Refresh", "/icon/refresh.png");

        functionPanel.add(btnAdd);
        functionPanel.add(btnUpdate);
        functionPanel.add(btnDelete);
        functionPanel.add(btnSearch);
        functionPanel.add(txtSearch);
        functionPanel.add(btnRefresh);

        // ==== Hàng 2: Group Stats + ComboBox ====
        JPanel groupPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        groupPanel.setBorder(BorderFactory.createTitledBorder("Group Statistics Option"));

        btnGroupStats = createIconButton("Group Stats", "/icon/groupIcon.png");
        comboField1 = new JComboBox<>(new String[]{"team_id", "team_name", "meeting_frequency"});
        comboField2 = new JComboBox<>(new String[]{"team_id", "team_name", "meeting_frequency"});

        groupPanel.add(btnGroupStats);
        groupPanel.add(new JLabel("Group by:"));
        groupPanel.add(comboField1);
        groupPanel.add(comboField2);

        // ==== Hàng 3: Form nhập liệu Team ====
        JPanel inputPanel = new JPanel(new GridLayout(2, 6, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Team Information"));

        txtTeamID = new JTextField();
        txtTeamName = new JTextField();
        txtMeetingFrequency = new JTextField();

        inputPanel.add(new JLabel("Team ID:"));
        inputPanel.add(txtTeamID);
        inputPanel.add(new JLabel("Team Name:"));
        inputPanel.add(txtTeamName);
        inputPanel.add(new JLabel("Meeting Frequency:"));
        inputPanel.add(txtMeetingFrequency);

        // Thêm ô trống cho cân bằng lưới
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel());

        // ==== Gộp các panel trên thành topPanel ====
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(functionPanel);
        topPanel.add(groupPanel);
        topPanel.add(inputPanel);

        add(topPanel, BorderLayout.NORTH);

        // ==== Panel chính chứa bảng Team và label count ====
        JPanel centerPanel = new JPanel(new BorderLayout());

        tableModel = new DefaultTableModel(
                new String[]{"Team ID", "Team Name", "Meeting Frequency"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Teams"));
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

    // Getters
    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tableModel; }
    public JTextField getTxtTeamID() { return txtTeamID; }
    public JTextField getTxtTeamName() { return txtTeamName; }
    public JTextField getTxtMeetingFrequency() { return txtMeetingFrequency; }
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
