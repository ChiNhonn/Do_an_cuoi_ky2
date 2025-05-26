package controller;

import view.LanguageManagementJPanel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import dao.LanguageDAO;
import model.Language;

public class LanguageController {
	private LanguageManagementJPanel view;
	
	public LanguageController(LanguageManagementJPanel view) {
		this.view = view;
		loadData();
		addEventHandlers();
	}
	private void loadData() {
		DefaultTableModel model = view.getTableModel();
		model.setRowCount(0);
		ArrayList<Language> list = LanguageDAO.getInstance().selectALL();
		for (Language a : list) {
			model.addRow(new Object[]{a.getLanguage_ID(), a.getLanguage_name()});
		}
		view.getLblCount().setText("Total: " + list.size());
	}
	private void clearForm() {
		view.getTxtLanguageID().setText("");
		view.getTxtLanguageName().setText("");
	}
	private void addLanguage() {
		try {
		int id = Integer.parseInt(view.getTxtLanguageID().getText().trim());
		String name = view.getTxtLanguageName().getText().trim();

		if (name.isEmpty()) {
		JOptionPane.showMessageDialog(view, "Please fill in all fields!");
		return;
	}
		Language a = new Language(id, name);
		if (LanguageDAO.getInstance().exists(a)) {
		JOptionPane.showMessageDialog(view, "LanguageID or LanguageName already exists!");
		return;
	}
		LanguageDAO.getInstance().insert(a);
			loadData();
			clearForm();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(view, "ID must be a number!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
		}
	}
	private void updateLanguage() {
		try {
		int id = Integer.parseInt(view.getTxtLanguageID().getText().trim());
		String name = view.getTxtLanguageName().getText().trim();
		if (name.isEmpty()) {
		JOptionPane.showMessageDialog(view, "Please fill in all fields!");
		return;
	}
		Language a = new Language(id, name);
		LanguageDAO.getInstance().update(a);
			loadData();
			clearForm();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(view, "ID must be a number!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
		}
	}
	private void deleteLanguage() {
		try {
			int id = Integer.parseInt(view.getTxtLanguageID().getText().trim());
			int confirm = JOptionPane.showConfirmDialog(view, "Are you sure to delete this record?", "Confirm", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			LanguageDAO.getInstance().delete(new Language(id, null));
			loadData();
			clearForm();
		}
	} catch (NumberFormatException e) {
		JOptionPane.showMessageDialog(view, "ID must be a number!");
	} catch (Exception e) {
		JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
		}
	}
	private void searchLanguage() {
		String keyword = view.getTxtSearch().getText().trim();
		DefaultTableModel model = view.getTableModel();
		model.setRowCount(0);
		ArrayList<Language> list = LanguageDAO.getInstance().search(keyword);
		for (Language a : list) {
			model.addRow(new Object[]{a.getLanguage_ID(), a.getLanguage_name()});
		}
			view.getLblCount().setText("Total: " + list.size());
		}

		private void addEventHandlers() {
			view.getBtnSearch().addActionListener(e -> searchLanguage());
			view.getBtnRefresh().addActionListener(e -> {
			loadData();
			clearForm();
		});

		view.getBtnAdd().addActionListener(e -> addLanguage());
		view.getBtnUpdate().addActionListener(e -> updateLanguage());
		view.getBtnDelete().addActionListener(e -> deleteLanguage());
		view.getTable().addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			int row = view.getTable().getSelectedRow();
			if (row >= 0) {
				view.getTxtLanguageID().setText(view.getTableModel().getValueAt(row, 0).toString());
				view.getTxtLanguageName().setText(view.getTableModel().getValueAt(row, 1).toString());
				}
			}
		});
	}
}
