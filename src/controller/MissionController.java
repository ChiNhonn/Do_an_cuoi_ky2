package controller;

import java.awt.DefaultFocusTraversalPolicy;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.MissionDAO;
import model.Mission;
import view.MissionManagementJPanel;

public class MissionController {
	private MissionManagementJPanel view;
	private MissionDAO dao;
	public MissionController(MissionManagementJPanel view) {
		this.view = view;
		this.dao = new MissionDAO();
		loadData();
		addEventHandlers();
	}
	
	private void loadData() {
		DefaultTableModel model = view.getTableModel();
		model.setRowCount(0);
		ArrayList<Mission> list = MissionDAO.getInstance().selectALL();
		for(Mission a : list) {
			model.addRow(new Object[] {
					a.getMission_ID(),
					a.getMission_name(),
					a.getLocation(),
					a.getAgent_ID(),
					a.getAccess_ID(),
					a.getTeam_ID(),
					a.getMission_status()
			});
		}
		view.getLblCount().setText("Total: "+ list.size());
	}
	
	private void clearForm() {
		view.getTxtMissionID().setText("");
		view.getTxtMissionName().setText("");
		view.getTxtLocation().setText("");
		view.getTxtAgentID().setText("");
		view.getTxtAccessID().setText("");
		view.getTxtTeamID().setText("");
		view.getTxtMissionStatus().setText("");
	}
	
	private void addMission() {
		try {
			int MissionID = Integer.parseInt(view.getTxtMissionID().getText().trim());
			String MissionName = view.getTxtMissionName().getText().trim();
			String Location = view.getTxtLocation().getText().trim();
			int AgentID = Integer.parseInt(view.getTxtAgentID().getText().trim());
			int AccessID = Integer.parseInt(view.getTxtAccessID().getText().trim());
			int TeamID = Integer.parseInt(view.getTxtTeamID().getText().trim());
			String MissionStatus = view.getTxtMissionStatus().getText().trim();
			
			if(MissionName.isEmpty() || Location.isEmpty() ||
					MissionStatus.isEmpty()) {
				JOptionPane.showMessageDialog(view, "Please fill in all fields");
				return;
			}
			
			Mission a = new Mission(MissionID, MissionName, Location, AgentID, AccessID, TeamID, MissionStatus);
			if (MissionDAO.getInstance().exists(a)) {
			JOptionPane.showMessageDialog(view, "Mission ID or MissionName  already exists!");
			return;
		}
			MissionDAO.getInstance().insert(a);
			loadData();
			clearForm();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
		}
	}
	private void updateMission() {
		try {
			int MissionID = Integer.parseInt(view.getTxtMissionID().getText().trim());
			String MissionName = view.getTxtMissionName().getText().trim();
			String Location = view.getTxtLocation().getText().trim();
			int AgentID = Integer.parseInt(view.getTxtAgentID().getText().trim());
			int AccessID = Integer.parseInt(view.getTxtAccessID().getText().trim());
			int TeamID = Integer.parseInt(view.getTxtTeamID().getText().trim());
			String MissionStatus = view.getTxtMissionStatus().getText().trim();

			if(MissionName.isEmpty() || Location.isEmpty() ||
					MissionStatus.isEmpty()) {
				JOptionPane.showMessageDialog(view, "Please fill in all fields");
				return;
			}
			
			Mission a = new Mission(MissionID, MissionName, Location, AgentID, AccessID, TeamID, MissionStatus);
			MissionDAO.getInstance().update(a);
			loadData();
			clearForm();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(view, "ID must be an integer!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
		}
	}
	private void deleteMission() {
		try {
			int MissionID = Integer.parseInt(view.getTxtMissionID().getText().trim());
			int confirm = JOptionPane.showConfirmDialog(view, "Are you sure to delete this record?", "Confirm", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
			MissionDAO.getInstance().delete(new Mission(MissionID, null, null, 0, 0, 0, null));
			loadData();
			clearForm();
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(view, "ID must be an integer!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
		}
	}
	private void searchMission() {
		String keyword = view.getTxtSearch().getText().trim();
		DefaultTableModel model = view.getTableModel();
		model.setRowCount(0);
		ArrayList<Mission> list = MissionDAO.getInstance().search(keyword);
		for (Mission a : list) {
			model.addRow(new Object[]{
			a.getMission_ID(), 
			a.getMission_name(), 
			a.getLocation(), 
			a.getAgent_ID(), 
			a.getAccess_ID(), 
			a.getTeam_ID(), 
			a.getMission_status()
		});
		}
		view.getLblCount().setText("Total: " + list.size());
	}
	
	private void showGroupStats() {
	    String field1 = view.getComboField1().getSelectedItem().toString();
	    String field2 = view.getComboField2().getSelectedItem().toString();
	    ArrayList<Object[]> stats = dao.countGroupByTwoFields(field1, field2);
	    DefaultTableModel model = view.getTableModel();
	    model.setRowCount(0); 
	    model.setColumnCount(0); 
	    model.addColumn(field1);
	    model.addColumn(field2);
	    model.addColumn("Count");
	    for (Object[] row : stats) {
	        model.addRow(row);
	    }
	    view.getLblCount().setText("Total Groups: " + stats.size());
	}
	private void addEventHandlers() {
	    view.getBtnSearch().addActionListener(e -> searchMission());

	    view.getBtnRefresh().addActionListener(e -> {
	        loadData();
	        clearForm();
	    });

	    view.getBtnAdd().addActionListener(e -> addMission());
	    view.getBtnUpdate().addActionListener(e -> updateMission());
	    view.getBtnDelete().addActionListener(e -> deleteMission());
	    view.getBtnGroupStats().addActionListener(e -> showGroupStats());
	    view.getTable().addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            int row = view.getTable().getSelectedRow();
	            if (row >= 0) {
	                view.getTxtMissionID().setText(view.getTableModel().getValueAt(row, 0).toString());
	                view.getTxtMissionName().setText(view.getTableModel().getValueAt(row, 1).toString());
	                view.getTxtLocation().setText(view.getTableModel().getValueAt(row, 2).toString());
	                view.getTxtAgentID().setText(view.getTableModel().getValueAt(row, 3).toString());
	                view.getTxtAccessID().setText(view.getTableModel().getValueAt(row, 4).toString());
	                view.getTxtTeamID().setText(view.getTableModel().getValueAt(row, 5).toString());
	                view.getTxtMissionStatus().setText(view.getTableModel().getValueAt(row, 6).toString());
	            }
	        }
	    });
	}
}
