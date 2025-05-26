package view;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AccountManagementJPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtConfirm, txtUsername, txtPassword, txtGmail, txtSearch;
    private JLabel lblCount;
    private JButton btnAdd, btnUpdate, btnDelete, btnRefresh, btnSearch;

    public AccountManagementJPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // ==== Thanh công cụ (hàng trên cùng) ====
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

        // ==== Form nhập liệu ====
        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Account Information"));

        txtConfirm = new JTextField();
        txtUsername = new JTextField();
        txtPassword = new JTextField();
        txtGmail = new JTextField();

        inputPanel.add(new JLabel("Confirm:")); inputPanel.add(txtConfirm);
        inputPanel.add(new JLabel("Username:")); inputPanel.add(txtUsername);
        inputPanel.add(new JLabel("Password:")); inputPanel.add(txtPassword);
        inputPanel.add(new JLabel("Gmail:")); inputPanel.add(txtGmail);

        // ==== Gộp phần trên cùng ====
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(toolPanel);
        topPanel.add(inputPanel);
        add(topPanel, BorderLayout.NORTH);

        // ==== Bảng và tổng số dòng ====
        JPanel centerPanel = new JPanel(new BorderLayout());

        tableModel = new DefaultTableModel(
            new String[]{"Confirm", "Username", "Password", "Gmail"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Account List"));
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

    // ===== Getters để controller sử dụng =====
    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tableModel; }
    public JTextField getTxtConfirm() { return txtConfirm; }
    public JTextField getTxtUsername() { return txtUsername; }
    public JTextField getTxtPassword() { return txtPassword; }
    public JTextField getTxtGmail() { return txtGmail; }
    public JTextField getTxtSearch() { return txtSearch; }
    public JLabel getLblCount() { return lblCount; }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnUpdate() { return btnUpdate; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnRefresh() { return btnRefresh; }
    public JButton getBtnSearch() { return btnSearch; }
}
