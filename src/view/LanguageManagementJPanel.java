package view;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LanguageManagementJPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtLanguageID, txtLanguageName, txtSearch;
    private JLabel lblCount;
    private JButton btnAdd, btnUpdate, btnDelete, btnRefresh, btnSearch;

    public LanguageManagementJPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // ==== Hàng 1: Thanh công cụ ====
        JPanel toolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        toolPanel.setBorder(BorderFactory.createTitledBorder("Function"));

        btnAdd = createIconButton("/icon/add.png", "Add");
        btnUpdate = createIconButton("/icon/update.png", "Update");
        btnDelete = createIconButton("/icon/delete.png", "Delete");
        btnSearch = createIconButton("/icon/search.png", "Search");
        txtSearch = new JTextField(20);
        btnRefresh = createIconButton("/icon/refresh.png", "Refresh");

        toolPanel.add(btnAdd);
        toolPanel.add(btnUpdate);
        toolPanel.add(btnDelete);
        toolPanel.add(btnSearch);
        toolPanel.add(txtSearch);
        toolPanel.add(btnRefresh);

        // ==== Bỏ Group Stats, nên không có panel nào cho phần này ====

        // ==== Hàng 2 (trước đây là inputPanel): Form nhập liệu Language ====
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Language Information"));

        txtLanguageID = new JTextField();
        txtLanguageName = new JTextField();

        inputPanel.add(new JLabel("Language ID:"));
        inputPanel.add(txtLanguageID);
        inputPanel.add(new JLabel("Language Name:"));
        inputPanel.add(txtLanguageName);

        // ==== Gộp topPanel ====
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(toolPanel);
        topPanel.add(inputPanel);
        add(topPanel, BorderLayout.NORTH);

        // ==== Panel trung tâm: chứa bảng Language và Total ====
        JPanel centerPanel = new JPanel(new BorderLayout());

        // ==== Table Language ====
        tableModel = new DefaultTableModel(
                new String[]{"Language ID", "Language Name"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Language List"));
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // ==== Count Label ====
        lblCount = new JLabel("Total: 0");
        JPanel countPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        countPanel.add(lblCount);
        centerPanel.add(countPanel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);
    }

    private JButton createIconButton(String iconPath, String text) {
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
    public JTextField getTxtLanguageID() { return txtLanguageID; }
    public JTextField getTxtLanguageName() { return txtLanguageName; }
    public JTextField getTxtSearch() { return txtSearch; }
    public JLabel getLblCount() { return lblCount; }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnUpdate() { return btnUpdate; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnRefresh() { return btnRefresh; }
    public JButton getBtnSearch() { return btnSearch; }
}
