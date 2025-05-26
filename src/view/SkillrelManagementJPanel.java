package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SkillrelManagementJPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtAgentID, txtSkillID, txtSearch;
    private JLabel lblCount;
    private JButton btnAdd, btnDelete, btnRefresh, btnSearch;

    public SkillrelManagementJPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // ==== Hàng 1: Thanh chức năng (function panel) ====
        JPanel functionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        functionPanel.setBorder(BorderFactory.createTitledBorder("Functions"));

        btnAdd = createIconButton("Add Relation", "/icon/add.png");
        btnDelete = createIconButton("Delete Relation", "/icon/delete.png");
        btnRefresh = createIconButton("Refresh", "/icon/refresh.png");
        btnSearch = createIconButton("Search Relation", "/icon/search.png");

        txtSearch = new JTextField(20);

        functionPanel.add(btnAdd);
        functionPanel.add(btnDelete);
        functionPanel.add(btnRefresh);
        functionPanel.add(new JLabel("Search:"));
        functionPanel.add(txtSearch);
        functionPanel.add(btnSearch);

        // ==== Hàng 2: Nhập liệu thông tin relation (information panel) ====
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Relation Information"));

        txtAgentID = new JTextField(10);
        txtSkillID = new JTextField(10);

        infoPanel.add(new JLabel("Agent ID:"));
        infoPanel.add(txtAgentID);
        infoPanel.add(new JLabel("Skill ID:"));
        infoPanel.add(txtSkillID);

        // ==== Gộp 2 hàng trên thành panel trên cùng ====
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(functionPanel);
        topPanel.add(infoPanel);

        add(topPanel, BorderLayout.NORTH);

        // ==== Bảng quan hệ ====
        tableModel = new DefaultTableModel(new String[]{"Agent ID", "Skill ID"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Agent Skills"));
        add(scrollPane, BorderLayout.CENTER);

        // ==== Label tổng số ====
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblCount = new JLabel("Total Relations: 0");
        bottomPanel.add(lblCount);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JButton createIconButton(String tooltip, String iconPath) {
        java.net.URL imgURL = getClass().getResource(iconPath);
        JButton btn = new JButton();

        if (imgURL != null) {
            btn.setIcon(new ImageIcon(imgURL));
        } else {
            btn.setText(tooltip.substring(0, 1));
        }

        btn.setToolTipText(tooltip);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

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
    public JTextField getTxtSkillID() { return txtSkillID; }
    public JTextField getTxtSearch() { return txtSearch; }
    public JLabel getLblCount() { return lblCount; }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnRefresh() { return btnRefresh; }
    public JButton getBtnSearch() { return btnSearch; }
}
