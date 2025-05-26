package controller;

import dao.AffiliationDAO;
import model.Affiliation;
import view.AffiliationManagementJPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.util.ArrayList;

public class AffiliationController {
    private AffiliationManagementJPanel view;

    public AffiliationController(AffiliationManagementJPanel view) {
        this.view = view;
        loadData();
        addEventHandlers();
    }

    private void loadData() {
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);
        ArrayList<Affiliation> list = AffiliationDAO.getInstance().selectALL();
        for (Affiliation a : list) {
            model.addRow(new Object[]{a.getAffiliation_id(), a.getAffiliation_name(), a.getDescription()});
        }
        view.getLblCount().setText("Total: " + list.size());
    }
    private void clearForm() {
        view.getTxtAffiliationID().setText("");
        view.getTxtAffiliationName().setText("");
        view.getTxtDescription().setText("");
    }

    private void searchAffiliation() {
        String keyword = view.getTxtSearch().getText().trim();
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);
        ArrayList<Affiliation> list = AffiliationDAO.getInstance().search(keyword);
        for (Affiliation a : list) {
            model.addRow(new Object[]{a.getAffiliation_id(), a.getAffiliation_name(), a.getDescription()});
        }
        view.getLblCount().setText("Total: " + list.size());
    }

    private void addEventHandlers() {
        view.getBtnSearch().addActionListener(e -> searchAffiliation());
        view.getBtnRefresh().addActionListener(e -> {
            loadData();
            clearForm();
        });

        view.getBtnAdd().addActionListener(e -> addAffiliation());
        view.getBtnUpdate().addActionListener(e -> updateAffiliation());
        view.getBtnDelete().addActionListener(e -> deleteAffiliation());

        view.getTable().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable().getSelectedRow();
                if (row >= 0) {
                    view.getTxtAffiliationID().setText(view.getTableModel().getValueAt(row, 0).toString());
                    view.getTxtAffiliationName().setText(view.getTableModel().getValueAt(row, 1).toString());
                    view.getTxtDescription().setText(view.getTableModel().getValueAt(row, 2).toString());
                }
            }
        });
    }

    private void addAffiliation() {
        try {
            int id = Integer.parseInt(view.getTxtAffiliationID().getText().trim());
            String name = view.getTxtAffiliationName().getText().trim();
            String desc = view.getTxtDescription().getText().trim();

            if (name.isEmpty() || desc.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please fill in all fields!");
                return;
            }

            Affiliation a = new Affiliation(id, name, desc);
            if (AffiliationDAO.getInstance().exists(a)) {
                JOptionPane.showMessageDialog(view, "Affiliation ID or Name already exists!");
                return;
            }

            AffiliationDAO.getInstance().insert(a);
            loadData();
            clearForm();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "ID must be a number!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
        }
    }

    private void updateAffiliation() {
        try {
            int id = Integer.parseInt(view.getTxtAffiliationID().getText().trim());
            String name = view.getTxtAffiliationName().getText().trim();
            String desc = view.getTxtDescription().getText().trim();

            if (name.isEmpty() || desc.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please fill in all fields!");
                return;
            }

            Affiliation a = new Affiliation(id, name, desc);
            AffiliationDAO.getInstance().update(a);
            loadData();
            clearForm();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "ID must be a number!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
        }
    }

    private void deleteAffiliation() {
        try {
            int id = Integer.parseInt(view.getTxtAffiliationID().getText().trim());
            int confirm = JOptionPane.showConfirmDialog(view, "Are you sure to delete this record?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                AffiliationDAO.getInstance().delete(new Affiliation(id, null, null));
                loadData();
                clearForm();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "ID must be a number!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
        }
    }
}
