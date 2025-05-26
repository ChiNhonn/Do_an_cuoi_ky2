package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.TeamrelDAO;
import model.Teamrel;
import view.TeamrelManagementJPanel;

public class TeamrelController {
    private TeamrelManagementJPanel view;
    private TeamrelDAO dao;

    public TeamrelController(TeamrelManagementJPanel view) {
        this.view = view;
        this.dao = TeamrelDAO.getInstance();
        init();
    }

    private void init() {
        loadTable();

        view.getBtnAdd().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addTeamrel();
            }
        });

        view.getBtnDelete().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                deleteTeamrel();
            }
        });

        view.getBtnRefresh().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadTable();
            }
        });

        view.getBtnSearch().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                searchTeamrel();
            }
        });
    }

    private void loadTable() {
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);

        ArrayList<Teamrel> list = dao.selectALL();
        for (Teamrel tr : list) {
            model.addRow(new Object[]{tr.getAgent_ID(), tr.getTeam_ID()});
        }

        view.getLblCount().setText("Total Relations: " + list.size());
    }

    private void addTeamrel() {
        try {
            int agentID = Integer.parseInt(view.getTxtAgentID().getText());
            int teamID = Integer.parseInt(view.getTxtTeamID().getText());

            Teamrel tr = new Teamrel(agentID, teamID);

            if (dao.exists(tr)) {
                JOptionPane.showMessageDialog(view, "Relation already exists!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int result = dao.insert(tr);
            if (result > 0) {
                JOptionPane.showMessageDialog(view, "Add relation successfully!");
                loadTable();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(view, "Add failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteTeamrel() {
        JTable table = view.getTable();
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(view, "Please select a row to delete!");
            return;
        }

        int agentID = (int) table.getValueAt(row, 0);
        int teamID = (int) table.getValueAt(row, 1);

        Teamrel tr = new Teamrel(agentID, teamID);

        int confirm = JOptionPane.showConfirmDialog(view, "Confirm delete?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int result = dao.delete(tr);
            if (result > 0) {
                JOptionPane.showMessageDialog(view, "Deleted successfully!");
                loadTable();
            } else {
                JOptionPane.showMessageDialog(view, "Delete failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void searchTeamrel() {
        String keyword = view.getTxtSearch().getText().trim();
        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Enter keyword to search!");
            return;
        }

        ArrayList<Teamrel> list = dao.search(keyword);
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);
        for (Teamrel tr : list) {
            model.addRow(new Object[]{tr.getAgent_ID(), tr.getTeam_ID()});
        }

        view.getLblCount().setText("Total Relations: " + list.size());
    }

    private void clearForm() {
        view.getTxtAgentID().setText("");
        view.getTxtTeamID().setText("");
    }
}
