package controller;

import view.LanguagerelManagementJPanel;
import model.Languagerel;
import dao.AgentDAO;
import dao.LanguageDAO;
import dao.LanguagerelDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class LanguagerelController {
    private LanguagerelManagementJPanel view;

    public LanguagerelController(LanguagerelManagementJPanel view) {
        this.view = view;
        loadData();
        addEventHandlers();
    }

    private void loadData() {
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);
        ArrayList<Languagerel> list = LanguagerelDAO.getInstance().selectALL();
        for (Languagerel rel : list) {
            model.addRow(new Object[]{rel.getAgent_ID(), rel.getLanguage_ID()});
        }
        view.getLblCount().setText("Total: " + list.size());
    }

    private void clearForm() {
        view.getTxtAgentID().setText("");
        view.getTxtLanguageID().setText("");
    }

    private void addLanguagerel() {
        try {
            int agentID = Integer.parseInt(view.getTxtAgentID().getText().trim());
            int languageID = Integer.parseInt(view.getTxtLanguageID().getText().trim());

            // Check if agentID exists
            if (!AgentDAO.getInstance().isAgentIDExists(agentID)) {
                JOptionPane.showMessageDialog(view, "Agent ID does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if languageID exists
            if (!LanguageDAO.getInstance().isLanguageIDExists(languageID)) {
                JOptionPane.showMessageDialog(view, "Language ID does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if relation already exists
            if (LanguagerelDAO.getInstance().exists(new Languagerel(agentID, languageID))) {
                JOptionPane.showMessageDialog(view, "This relation already exists!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int result = LanguagerelDAO.getInstance().insert(new Languagerel(agentID, languageID));
            if (result > 0) {
                loadData();
                clearForm();
                JOptionPane.showMessageDialog(view, "Add successful!");
            } else {
                JOptionPane.showMessageDialog(view, "Add failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "IDs must be valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error occurred while adding data!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void deleteLanguagerel() {
        try {
            int agentID = Integer.parseInt(view.getTxtAgentID().getText().trim());
            int languageID = Integer.parseInt(view.getTxtLanguageID().getText().trim());

            int confirm = JOptionPane.showConfirmDialog(view, "Delete this relation?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                LanguagerelDAO.getInstance().delete(new Languagerel(agentID, languageID));
                loadData();
                clearForm();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "IDs must be numbers!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchLanguagerel() {
        String keyword = view.getTxtSearch().getText().trim();
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);

        ArrayList<Languagerel> list = LanguagerelDAO.getInstance().selectALL();
        for (Languagerel rel : list) {
            if (String.valueOf(rel.getAgent_ID()).contains(keyword) || String.valueOf(rel.getLanguage_ID()).contains(keyword)) {
                model.addRow(new Object[]{rel.getAgent_ID(), rel.getLanguage_ID()});
            }
        }
        view.getLblCount().setText("Total: " + model.getRowCount());
    }

    private void addEventHandlers() {
        view.getBtnAdd().addActionListener(e -> addLanguagerel());
        view.getBtnDelete().addActionListener(e -> deleteLanguagerel());
        view.getBtnRefresh().addActionListener(e -> {
            loadData();
            clearForm();
        });
        view.getBtnSearch().addActionListener(e -> searchLanguagerel());

        view.getTable().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable().getSelectedRow();
                if (row >= 0) {
                    view.getTxtAgentID().setText(view.getTableModel().getValueAt(row, 0).toString());
                    view.getTxtLanguageID().setText(view.getTableModel().getValueAt(row, 1).toString());
                }
            }
        });
    }
}
