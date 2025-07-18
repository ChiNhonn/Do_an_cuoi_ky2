package view;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SecurityclearanceManagementJPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtScID, txtScLevel, txtDescription, txtSearch;
    private JLabel lblCount;
    private JButton btnAdd, btnUpdate, btnDelete, btnRefresh, btnSearch;

    public SecurityclearanceManagementJPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

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

        JPanel inputPanel = new JPanel(new GridLayout(2, 6, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Security Clearance Information"));

        txtScID = new JTextField();
        txtScLevel = new JTextField();
        txtDescription = new JTextField();

        inputPanel.add(new JLabel("Security Clearance ID:"));
        inputPanel.add(txtScID);
        inputPanel.add(new JLabel("Level:"));
        inputPanel.add(txtScLevel);
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(txtDescription);
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(toolPanel);
        topPanel.add(inputPanel);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());

        tableModel = new DefaultTableModel(
                new String[]{"Security Clearance ID", "Level", "Description"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Security Clearances"));
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
            btn.setText(text.substring(0, 1)); 
        }

        btn.setToolTipText(text);
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

    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tableModel; }
    public JTextField getTxtScID() { return txtScID; }
    public JTextField getTxtScLevel() { return txtScLevel; }
    public JTextField getTxtDescription() { return txtDescription; }
    public JTextField getTxtSearch() { return txtSearch; }
    public JLabel getLblCount() { return lblCount; }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnUpdate() { return btnUpdate; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnRefresh() { return btnRefresh; }
    public JButton getBtnSearch() { return btnSearch; }
  
}
