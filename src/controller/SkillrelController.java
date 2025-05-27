package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.AgentDAO;
import dao.SkillDAO;
import dao.SkillrelDAO;
import model.Skillrel;
import view.SkillrelManagementJPanel;

public class SkillrelController {
    private SkillrelManagementJPanel view;
    private SkillrelDAO dao;

    public SkillrelController(SkillrelManagementJPanel view) {
        this.view = view;
        this.dao = SkillrelDAO.getInstance();
        loadData();
        addEventHandlers();
    }

    private void loadData() {
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);
        ArrayList<Skillrel> list = SkillrelDAO.getInstance().selectALL();
        for (Skillrel rel : list) {
            model.addRow(new Object[]{
                    rel.getAgent_ID(),
                    rel.getSkill_ID()
            });
        }
        view.getLblCount().setText("Total Relations: " + list.size());
    }

    private void clearForm() {
        view.getTxtAgentID().setText("");
        view.getTxtSkillID().setText("");
    }

    private void addSkillRel() {
        try {
            int agentID = Integer.parseInt(view.getTxtAgentID().getText().trim());
            int skillID = Integer.parseInt(view.getTxtSkillID().getText().trim());

            if (!AgentDAO.getInstance().isAgentIDExists(agentID)) {
                JOptionPane.showMessageDialog(view, "Agent ID does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!SkillDAO.getInstance().isSkillIDExists(skillID)) {
                JOptionPane.showMessageDialog(view, "Skill ID does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Skillrel rel = new Skillrel(agentID, skillID);
            if (SkillrelDAO.getInstance().exists(rel)) {
                JOptionPane.showMessageDialog(view, "This agent-skill relation already exists!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int result = SkillrelDAO.getInstance().insert(rel);
            if (result > 0) {
                loadData();
                clearForm();
                JOptionPane.showMessageDialog(view, "Relation added successfully!");
            } else {
                JOptionPane.showMessageDialog(view, "Failed to add relation!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Agent ID and Skill ID must be valid integers.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error adding relation: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }


    private void deleteSkillRel() {
        try {
            int agentID = Integer.parseInt(view.getTxtAgentID().getText().trim());
            int skillID = Integer.parseInt(view.getTxtSkillID().getText().trim());
            int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this relation?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                Skillrel relToDelete = new Skillrel(agentID, skillID);
                if (dao.delete(relToDelete) > 0) {
                    JOptionPane.showMessageDialog(view, "Relation deleted successfully.");
                    loadData();
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(view, "Failed to delete relation.");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Agent ID and Skill ID must be integers.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error deleting relation: " + e.getMessage());
        }
    }

    private void addEventHandlers() {
        view.getBtnAdd().addActionListener(e -> addSkillRel());
        view.getBtnDelete().addActionListener(e -> deleteSkillRel());
        view.getBtnRefresh().addActionListener(e -> {
            loadData();
            clearForm();
        });
        view.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable().getSelectedRow();
                if (row >= 0) {
                    view.getTxtAgentID().setText(view.getTableModel().getValueAt(row, 0).toString());
                    view.getTxtSkillID().setText(view.getTableModel().getValueAt(row, 1).toString());
                }
            }
        });
    }
}