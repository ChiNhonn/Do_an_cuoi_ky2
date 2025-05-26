package controller;

import dao.AccountDAO;
import model.Account;
import view.AccountManagementJPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class AccountController {
    private final AccountManagementJPanel view;

    public AccountController(AccountManagementJPanel view) {
        this.view = view;
        initController();
    }

    private void initController() {
        loadAccountData();
        addEventListeners();
    }

    // Load dữ liệu tài khoản vào bảng
    private void loadAccountData() {
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);
        ArrayList<Account> list = AccountDAO.getInstance().selectALL();
        for (Account acc : list) {
            model.addRow(new Object[]{acc.getConfirm(), acc.getUsername(), acc.getPassword(), acc.getGmail()});
        }
        view.getLblCount().setText("Total: " + list.size());
    }

    // Xoá dữ liệu trong form nhập liệu
    private void clearForm() {
        view.getTxtConfirm().setText("");
        view.getTxtUsername().setText("");
        view.getTxtPassword().setText("");
        view.getTxtGmail().setText("");
        view.getTxtSearch().setText("");
    }

    // Tìm kiếm tài khoản theo từ khóa
    private void searchAccount() {
        String keyword = view.getTxtSearch().getText().trim();
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);
        ArrayList<Account> list = AccountDAO.getInstance().search(keyword);
        for (Account acc : list) {
            model.addRow(new Object[]{acc.getConfirm(), acc.getUsername(), acc.getPassword(), acc.getGmail()});
        }
        view.getLblCount().setText("Total: " + list.size());
    }

    // Thêm tài khoản mới
    private void addAccount() {
        try {
            String confirm = view.getTxtConfirm().getText().trim();
            String username = view.getTxtUsername().getText().trim();
            String password = view.getTxtPassword().getText().trim();
            String gmail = view.getTxtGmail().getText().trim();

            if (confirm.isEmpty() || username.isEmpty() || password.isEmpty() || gmail.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please fill in all fields!");
                return;
            }

            Account acc = new Account(confirm, username, password, gmail);

            if (AccountDAO.getInstance().exists(acc)) {
                JOptionPane.showMessageDialog(view, "Account already exists!");
                return;
            }

            AccountDAO.getInstance().insert(acc);
            loadAccountData();
            clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
        }
    }

    // Cập nhật tài khoản
    private void updateAccount() {
        try {
            String confirm = view.getTxtConfirm().getText().trim();
            String username = view.getTxtUsername().getText().trim();
            String password = view.getTxtPassword().getText().trim();
            String gmail = view.getTxtGmail().getText().trim();

            if (confirm.isEmpty() || username.isEmpty() || password.isEmpty() || gmail.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please fill in all fields!");
                return;
            }

            Account acc = new Account(confirm, username, password, gmail);
            AccountDAO.getInstance().update(acc);
            loadAccountData();
            clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
        }
    }

    // Xoá tài khoản
    private void deleteAccount() {
        try {
            String confirm = view.getTxtConfirm().getText().trim();
            if (confirm.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please select an account to delete!");
                return;
            }

            int confirmOption = JOptionPane.showConfirmDialog(view, "Are you sure to delete this account?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirmOption == JOptionPane.YES_OPTION) {
                AccountDAO.getInstance().delete(new Account(confirm, null, null, null));
                loadAccountData();
                clearForm();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
        }
    }

    // Gán sự kiện cho các nút và bảng
    private void addEventListeners() {
        view.getBtnSearch().addActionListener(e -> searchAccount());
        view.getBtnRefresh().addActionListener(e -> {
            loadAccountData();
            clearForm();
        });
        view.getBtnAdd().addActionListener(e -> addAccount());
        view.getBtnUpdate().addActionListener(e -> updateAccount());
        view.getBtnDelete().addActionListener(e -> deleteAccount());

        view.getTable().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable().getSelectedRow();
                if (row >= 0) {
                    view.getTxtConfirm().setText(view.getTableModel().getValueAt(row, 0).toString());
                    view.getTxtUsername().setText(view.getTableModel().getValueAt(row, 1).toString());
                    view.getTxtPassword().setText(view.getTableModel().getValueAt(row, 2).toString());
                    view.getTxtGmail().setText(view.getTableModel().getValueAt(row, 3).toString());
                }
            }
        });
    }
}
