package controller;

import dao.AffiliationDAO;
import dao.AffiliationrelDAO;
import dao.AgentDAO;
import model.Affiliationrel;
import view.AffiliationrelManagementJPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.util.ArrayList;

public class AffiliationrelController {
    private AffiliationrelManagementJPanel view;

    public AffiliationrelController(AffiliationrelManagementJPanel view) {
        this.view = view;
        loadData();
        addEventHandlers();
    }

    private void loadData() {
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);
        ArrayList<Affiliationrel> list = AffiliationrelDAO.getInstance().selectALL();
        for (Affiliationrel a : list) {
            model.addRow(new Object[]{a.getAgent_ID(), a.getAffiliation_ID(), a.getAffiliation_strength()});
        }
        view.getLblCount().setText("Total: " + list.size());
    }

    private void searchAffiliationrel() {
        String keyword = view.getTxtSearch().getText().trim();
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);
        ArrayList<Affiliationrel> list = AffiliationrelDAO.getInstance().search(keyword);
        for (Affiliationrel a : list) {
            model.addRow(new Object[]{
                a.getAgent_ID(), 
                a.getAffiliation_ID(), 
                a.getAffiliation_strength()
            });
        }

        view.getLblCount().setText("Total: " + list.size());
    }


    private void addEventHandlers() {
        view.getBtnSearch().addActionListener(e -> searchAffiliationrel());
        view.getBtnRefresh().addActionListener(e -> {
            clearFields();
            loadData();
        });
        view.getBtnAdd().addActionListener(e -> addAffiliationrel());
        view.getBtnUpdate().addActionListener(e -> updateAffiliationrel());
        view.getBtnDelete().addActionListener(e -> deleteAffiliationrel());

        view.getTable().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable().getSelectedRow();
                if (row >= 0) {
                    view.getTxtAgentID().setText(view.getTableModel().getValueAt(row, 0).toString());
                    view.getTxtAffiliationID().setText(view.getTableModel().getValueAt(row, 1).toString());
                    view.getTxtStrength().setText(view.getTableModel().getValueAt(row, 2).toString());
                }
            }
        });
    }

    private void clearFields() {
        view.getTxtAgentID().setText("");
        view.getTxtAffiliationID().setText("");
        view.getTxtStrength().setText("");
        view.getTxtSearch().setText("");
    }

    private void addAffiliationrel() {
        try {
            int agentID = Integer.parseInt(view.getTxtAgentID().getText().trim());
            int affiliationID = Integer.parseInt(view.getTxtAffiliationID().getText().trim());
            String strength = view.getTxtStrength().getText().trim();

            if (!AgentDAO.getInstance().isAgentIDExists(agentID)) {
                JOptionPane.showMessageDialog(view, "Lỗi: Agent ID không tồn tại trong bảng agent!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!AffiliationDAO.getInstance().isAffiliationIDExists(affiliationID)) {
                JOptionPane.showMessageDialog(view, "Lỗi: Affiliation ID không tồn tại trong bảng affiliation!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Affiliationrel a = new Affiliationrel(agentID, affiliationID, strength);
            int result = AffiliationrelDAO.getInstance().insert(a);
            if (result > 0) {
                loadData();
                JOptionPane.showMessageDialog(view, "Thêm thành công!");
            } else {
                JOptionPane.showMessageDialog(view, "Thêm thất bại!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập số hợp lệ cho ID!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void updateAffiliationrel() {
        try {
            int agentID = Integer.parseInt(view.getTxtAgentID().getText());
            int affiliationID = Integer.parseInt(view.getTxtAffiliationID().getText());
            String strength = view.getTxtStrength().getText();
            Affiliationrel a = new Affiliationrel(affiliationID, agentID, strength);
            AffiliationrelDAO.getInstance().update(a);
            loadData();
            JOptionPane.showMessageDialog(view, "Update successful!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Invalid input data!");
        }
    }

    private void deleteAffiliationrel() {
        try {
            int agentID = Integer.parseInt(view.getTxtAgentID().getText());
            int affiliationID = Integer.parseInt(view.getTxtAffiliationID().getText());
            Affiliationrel a = new Affiliationrel(affiliationID, agentID, "");
            AffiliationrelDAO.getInstance().delete(a);
            loadData();
            JOptionPane.showMessageDialog(view, "Delete successful!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Invalid input data!");
        }
    }
}
