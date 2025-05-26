package view;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AffiliationManagementJPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tablemodel;
    private JTextField txtAffiliationID, txtAffiliationName, txtDescription, txtSearch;
    private JLabel lblCount;
    private JButton btnAdd, btnUpdate, btnDelete, btnRefresh, btnSearch;

    public AffiliationManagementJPanel() {
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

        // ==== Hàng 2: Form nhập liệu Affiliation ====
        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Affiliation Information"));

        txtAffiliationID = new JTextField();
        txtAffiliationName = new JTextField();
        txtDescription = new JTextField();

        inputPanel.add(new JLabel("Affiliation ID:")); inputPanel.add(txtAffiliationID);
        inputPanel.add(new JLabel("Name:")); inputPanel.add(txtAffiliationName);
        inputPanel.add(new JLabel("Description:")); inputPanel.add(txtDescription);
        inputPanel.add(new JLabel()); inputPanel.add(new JLabel()); // filler

        // ==== Gộp topPanel ====
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(toolPanel);
        topPanel.add(inputPanel);
        add(topPanel, BorderLayout.NORTH);

        // ==== Panel trung tâm: chứa bảng Affiliation và Total ====
        JPanel centerPanel = new JPanel(new BorderLayout());

        // ==== Table Affiliation ====
        tablemodel = new DefaultTableModel(
                new String[]{"Affiliation ID", "Name", "Description"}, 0);
        table = new JTable(tablemodel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Affiliation List"));
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

    // Getters
    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tablemodel; }
    public JTextField getTxtAffiliationID() { return txtAffiliationID; }
    public JTextField getTxtAffiliationName() { return txtAffiliationName; }
    public JTextField getTxtDescription() { return txtDescription; }
    public JTextField getTxtSearch() { return txtSearch; }
    public JLabel getLblCount() { return lblCount; }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnUpdate() { return btnUpdate; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnRefresh() { return btnRefresh; }
    public JButton getBtnSearch() { return btnSearch; }
}
