package view;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AffiliationrelManagementJPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtAgentID, txtAffiliationID, txtStrength, txtSearch;
    private JLabel lblCount;
    private JButton btnAdd, btnUpdate, btnDelete, btnRefresh, btnSearch;

    public AffiliationrelManagementJPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

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

        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("AffiliationRel Information"));

        txtAgentID = new JTextField();
        txtAffiliationID = new JTextField();
        txtStrength = new JTextField();

        inputPanel.add(new JLabel("Agent ID:")); inputPanel.add(txtAgentID);
        inputPanel.add(new JLabel("Affiliation ID:")); inputPanel.add(txtAffiliationID);
        inputPanel.add(new JLabel("Strength:")); inputPanel.add(txtStrength);
        inputPanel.add(new JLabel()); inputPanel.add(new JLabel()); // filler

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(toolPanel);
        topPanel.add(inputPanel);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());

        tableModel = new DefaultTableModel(
                new String[]{"Agent ID", "Affiliation ID", "Strength"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("AffiliationRel List"));
        centerPanel.add(scrollPane, BorderLayout.CENTER);

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

    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tableModel; }
    public JTextField getTxtAgentID() { return txtAgentID; }
    public JTextField getTxtAffiliationID() { return txtAffiliationID; }
    public JTextField getTxtStrength() { return txtStrength; }
    public JTextField getTxtSearch() { return txtSearch; }
    public JLabel getLblCount() { return lblCount; }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnUpdate() { return btnUpdate; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnRefresh() { return btnRefresh; }
    public JButton getBtnSearch() { return btnSearch; }
}
