package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.SecurityclearanceDAO;
import dao.SkillDAO;
import model.Skill;
import view.SkillManagementJPanel;

public class SkillController {
    private SkillManagementJPanel view;
    private SkillDAO dao;

    public SkillController(SkillManagementJPanel view) {
        this.view = view;
        this.dao = SkillDAO.getInstance();
        loadData();
        addEventHandlers();
    }

    private void loadData() {
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);
        ArrayList<Skill> list = SkillDAO.getInstance().selectALL();
        for (Skill skill : list) {
            model.addRow(new Object[]{
                    skill.getSkill_ID(),
                    skill.getSkill_name()
            });
        }
        view.getLblCount().setText("Total: " + list.size());
    }

    private void clearForm() {
        view.getTxtSkillID().setText("");
        view.getTxtSkillName().setText("");
    }

    private void addSkill() {
        try {
            int skillID = Integer.parseInt(view.getTxtSkillID().getText().trim());
            String skillName = view.getTxtSkillName().getText().trim();

            if (skillName.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please enter skill name.");
                return;
            }

            Skill skill = new Skill(skillID, skillName);
            if (dao.exists(skill)) {
                JOptionPane.showMessageDialog(view, "Skill ID or Skill Name already exists!");
                return;
            }
            dao.insert(skill);
            loadData();
            clearForm();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Skill ID must be an integer.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error adding skill: " + e.getMessage());
        }
    }

    private void deleteSkill() {
        try {
            int skillID = Integer.parseInt(view.getTxtSkillID().getText().trim());
            int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this skill?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                Skill skillToDelete = new Skill(skillID, null);
                if (dao.delete(skillToDelete) > 0) {
                    JOptionPane.showMessageDialog(view, "Skill deleted successfully.");
                    loadData();
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(view, "Failed to delete skill.");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Skill ID must be an integer.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error deleting skill: " + e.getMessage());
        }
    }

    private void searchSkill() {
        String keyword = view.getTxtSearch().getText().trim();
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);
        ArrayList<Skill> list = dao.search(keyword);
        for (Skill skill : list) {
            model.addRow(new Object[]{
                    skill.getSkill_ID(),
                    skill.getSkill_name()
            });
        }
        view.getLblCount().setText("Total: " + list.size());
    }

    private void addEventHandlers() {
        view.getBtnAdd().addActionListener(e -> addSkill());
        view.getBtnDelete().addActionListener(e -> deleteSkill());
        view.getBtnRefresh().addActionListener(e -> {
            loadData();
            clearForm();
        });
        view.getBtnSearch().addActionListener(e -> searchSkill());

        view.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable().getSelectedRow();
                if (row >= 0) {
                    view.getTxtSkillID().setText(view.getTableModel().getValueAt(row, 0).toString());
                    view.getTxtSkillName().setText(view.getTableModel().getValueAt(row, 1).toString());
                }
            }
        });
    }
}