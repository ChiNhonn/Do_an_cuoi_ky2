package view;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AgentManagementJPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tablemodel;
    private JTextField txtAgenID, txtFirstName, txtLastName, txtAddress, txtCity, txtCountry, txtSalary, txtSearch;
    private JLabel lblCount;
    private JButton btnAdd, btnUpdate, btnDelete, btnRefresh, btnSearch;
    private JComboBox<String> comboField1, comboField2;
    private JButton btnGroupStats;

    public AgentManagementJPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // ==== Hàng 1: Thanh công cụ ====
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

        // ==== Hàng 2: Group Stats + ComboBox ====
        JPanel groupPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        groupPanel.setBorder(BorderFactory.createTitledBorder("Group Statistics Option"));

        btnGroupStats = createIconButton("Group Stats", "/icon/groupIcon.png");
        comboField1 = new JComboBox<>(new String[]{"agent_id", "first_name", "last_name", "address", "city", "country", "salary"});
        comboField2 = new JComboBox<>(new String[]{"agent_id", "first_name", "last_name", "address", "city", "country", "salary"});

        groupPanel.add(btnGroupStats);
        groupPanel.add(new JLabel("Group by:"));
        groupPanel.add(comboField1);
        groupPanel.add(comboField2);

        // ==== Hàng 3: Form nhập liệu Agent ====
        JPanel inputPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Agent Information"));

        txtAgenID = new JTextField();
        txtFirstName = new JTextField();
        txtLastName = new JTextField();
        txtAddress = new JTextField();
        txtCity = new JTextField();
        txtCountry = new JTextField();
        txtSalary = new JTextField();

        inputPanel.add(new JLabel("Agent ID:")); inputPanel.add(txtAgenID);
        inputPanel.add(new JLabel("First Name:")); inputPanel.add(txtFirstName);
        inputPanel.add(new JLabel("Last Name:")); inputPanel.add(txtLastName);
        inputPanel.add(new JLabel("Address:")); inputPanel.add(txtAddress);
        inputPanel.add(new JLabel("City:")); inputPanel.add(txtCity);
        inputPanel.add(new JLabel("Country:")); inputPanel.add(txtCountry);
        inputPanel.add(new JLabel("Salary:")); inputPanel.add(txtSalary);
        inputPanel.add(new JLabel()); inputPanel.add(new JLabel()); // filler

        // ==== Gộp topPanel ====
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(toolPanel);
        topPanel.add(groupPanel);
        topPanel.add(inputPanel);
        add(topPanel, BorderLayout.NORTH);

        // ==== Panel trung tâm: chứa bảng Agent và Total ====
        JPanel centerPanel = new JPanel(new BorderLayout());

        // ==== Table Agent ====
        tablemodel = new DefaultTableModel(
                new String[]{"Agent ID", "First Name", "Last Name", "Address", "City", "Country", "Salary"}, 0);
        table = new JTable(tablemodel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Agent List"));
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // ==== Count Label ====
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

    // Getters giữ nguyên
    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tablemodel; }
    public JTextField getTxtAgentID() { return txtAgenID; }
    public JTextField getTxtFirstName() { return txtFirstName; }
    public JTextField getTxtLastName() { return txtLastName; }
    public JTextField getTxtAddress() { return txtAddress; }
    public JTextField getTxtCity() { return txtCity; }
    public JTextField getTxtCountry() { return txtCountry; }
    public JTextField getTxtSalary() { return txtSalary; }
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