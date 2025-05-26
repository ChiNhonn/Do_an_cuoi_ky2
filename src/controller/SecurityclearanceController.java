package controller;

import java.awt.DefaultFocusTraversalPolicy;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.AgentDAO;
import dao.SecurityclearanceDAO;
import model.Securityclearance;
import view.SecurityclearanceManagementJPanel;

public class SecurityclearanceController {
    private SecurityclearanceManagementJPanel view;
    private SecurityclearanceDAO dao;

    public SecurityclearanceController(SecurityclearanceManagementJPanel view) {
        this.view = view;
        this.dao = new SecurityclearanceDAO();
        loadData();
        addEventHandlers();
    }

    private void loadData() {
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);
        ArrayList<Securityclearance> list = SecurityclearanceDAO.getInstance().selectALL();
        for (Securityclearance sc : list) {
            model.addRow(new Object[]{
                    sc.getSc_ID(),
                    sc.getSc_level(),
                    sc.getDescription()
            });
        }
        view.getLblCount().setText("Total: " + list.size());
    }

    private void clearForm() {
        view.getTxtScID().setText("");
        view.getTxtScLevel().setText("");
        view.getTxtDescription().setText("");
    }

    private void addSecurityClearance() {
        try {
            int scID = Integer.parseInt(view.getTxtScID().getText().trim());
            String scLevel = view.getTxtScLevel().getText().trim();
            String description = view.getTxtDescription().getText().trim();
            if (scLevel.isEmpty() || description.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please fill in all fields.");
                return;
            }
            Securityclearance sc = new Securityclearance(scID, scLevel, description);
            if (dao.exists(sc)) {
                JOptionPane.showMessageDialog(view, "Security Clearance ID already exists!");
                return;
            }
            dao.insert(sc);
            loadData();
            clearForm();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Security Clearance ID must be an integer.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error adding security clearance: " + e.getMessage());
        }
    }

    private void updateSecurityClearance() {
        try {
            int scID = Integer.parseInt(view.getTxtScID().getText().trim());
            String scLevel = view.getTxtScLevel().getText().trim();
            String description = view.getTxtDescription().getText().trim();
            if (scLevel.isEmpty() || description.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please fill in all fields.");
                return;
            }
            Securityclearance sc = new Securityclearance(scID, scLevel, description);
            dao.update(sc);
            loadData();
            clearForm();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Security Clearance ID must be an integer.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error updating security clearance: " + e.getMessage());
        }
    }

    private void deleteSecurityClearance() {
        try {
            int scID = Integer.parseInt(view.getTxtScID().getText().trim());
            int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this security clearance?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dao.delete(new Securityclearance(scID, null, null));
                loadData();
                clearForm();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Security Clearance ID must be an integer.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error deleting security clearance: " + e.getMessage());
        }
    }

    private void searchSecurityClearance() {
        String keyword = view.getTxtSearch().getText().trim();
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);
        ArrayList<Securityclearance> list = dao.search(keyword);
        for (Securityclearance sc : list) {
            model.addRow(new Object[]{
                    sc.getSc_ID(),
                    sc.getSc_level(),
                    sc.getDescription()
            });
        }
        view.getLblCount().setText("Total: " + list.size());
    }

    private void addEventHandlers() {
        view.getBtnAdd().addActionListener(e -> addSecurityClearance());
        view.getBtnUpdate().addActionListener(e -> updateSecurityClearance());
        view.getBtnDelete().addActionListener(e -> deleteSecurityClearance());
        view.getBtnRefresh().addActionListener(e -> {
            loadData();
            clearForm();
        });
        view.getBtnSearch().addActionListener(e -> searchSecurityClearance());

        view.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable().getSelectedRow();
                if (row >= 0) {
                    view.getTxtScID().setText(view.getTableModel().getValueAt(row, 0).toString());
                    view.getTxtScLevel().setText(view.getTableModel().getValueAt(row, 1).toString());
                    view.getTxtDescription().setText(view.getTableModel().getValueAt(row, 2).toString());
                }
            }
        });
    }
}