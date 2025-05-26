package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TeamrelManagementJPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtAgentID, txtTeamID, txtSearch;
    private JLabel lblCount;
    private JButton btnAdd, btnDelete, btnRefresh, btnSearch;

    public TeamrelManagementJPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // ==== Hàng 1: Thanh công cụ (chức năng) ====
        JPanel toolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        toolPanel.setBorder(BorderFactory.createTitledBorder("Function"));

        btnAdd = createIconButton("Add Relation", "/icon/add.png");
        btnDelete = createIconButton("Delete Relation", "/icon/delete.png");
        btnRefresh = createIconButton("Refresh", "/icon/refresh.png");
        btnSearch = createIconButton("Search", "/icon/search.png");
        txtSearch = new JTextField(20);

        toolPanel.add(btnAdd);
        toolPanel.add(btnDelete);
        toolPanel.add(btnRefresh);
        toolPanel.add(btnSearch);
        toolPanel.add(txtSearch);

        // ==== Hàng 2: (Teamrel không cần group stats nên bỏ)====
        // Nếu bạn muốn thêm combo lọc, có thể thêm sau

        // ==== Hàng 3: Form nhập liệu ====
        JPanel inputPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Teamrel Information"));

        txtAgentID = new JTextField();
        txtTeamID = new JTextField();

        inputPanel.add(new JLabel("Agent ID:"));
        inputPanel.add(txtAgentID);
        inputPanel.add(new JLabel("Team ID:"));
        inputPanel.add(txtTeamID);

        // ==== Gộp topPanel ====
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(toolPanel);
        topPanel.add(Box.createVerticalStrut(10));  // khoảng cách
        topPanel.add(inputPanel);

        add(topPanel, BorderLayout.NORTH);

        // ==== Bảng dữ liệu ====
        tableModel = new DefaultTableModel(
                new String[]{"Agent ID", "Team ID"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Teamrel List"));
        add(scrollPane, BorderLayout.CENTER);

        // ==== Panel hiển thị tổng số ====
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblCount = new JLabel("Total Relations: 0");
        bottomPanel.add(lblCount);
        add(bottomPanel, BorderLayout.SOUTH);
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
    public JTextField getTxtAgentID() { return txtAgentID; }
    public JTextField getTxtTeamID() { return txtTeamID; }
    public JTextField getTxtSearch() { return txtSearch; }
    public JLabel getLblCount() { return lblCount; }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnRefresh() { return btnRefresh; }
    public JButton getBtnSearch() { return btnSearch; }
}
