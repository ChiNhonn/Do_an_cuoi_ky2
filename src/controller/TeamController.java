package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.SkillrelDAO;
import dao.TeamDAO;
import model.Team;
import view.TeamManagementJPanel;

public class TeamController {
    private TeamManagementJPanel view;
    private TeamDAO dao;

    public TeamController(TeamManagementJPanel view) {
        this.view = view;
        this.dao = new TeamDAO();
        loadData();
        addEventHandlers();
    }
    private void loadData() {
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);
        ArrayList<Team> list = TeamDAO.getInstance().selectALL();
        for (Team team : list) {
            model.addRow(new Object[]{
                    team.getTeam_ID(),
                    team.getTeam_name(),
                    team.getMeeting_frequency()
            });
        }
        view.getLblCount().setText("Total: " + list.size());
    }

    private void clearForm() {
        view.getTxtTeamID().setText("");
        view.getTxtTeamName().setText("");
        view.getTxtMeetingFrequency().setText("");
    }

    private void addTeam() {
        try {
            int teamID = Integer.parseInt(view.getTxtTeamID().getText().trim());
            String teamName = view.getTxtTeamName().getText().trim();
            String meetingFrequency = view.getTxtMeetingFrequency().getText().trim();
            if (teamName.isEmpty() || meetingFrequency.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please fill in all fields.");
                return;
            }

            Team team = new Team(teamID, teamName, meetingFrequency);
            if (dao.exists(team)) {
                JOptionPane.showMessageDialog(view, "Team ID or Team Name already exists!");
                return;
            }
            dao.insert(team);
            loadData();
            clearForm();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Team ID must be an integer.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error adding team: " + e.getMessage());
        }
    }

    private void updateTeam() {
        try {
            int teamID = Integer.parseInt(view.getTxtTeamID().getText().trim());
            String teamName = view.getTxtTeamName().getText().trim();
            String meetingFrequency = view.getTxtMeetingFrequency().getText().trim();
            if (teamName.isEmpty() || meetingFrequency.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please fill in all fields.");
                return;
            }
            Team team = new Team(teamID, teamName, meetingFrequency);
            dao.update(team);
            loadData();
            clearForm();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Team ID must be an integer.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error updating team: " + e.getMessage());
        }
    }

    private void deleteTeam() {
        try {
            int teamID = Integer.parseInt(view.getTxtTeamID().getText().trim());
            int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this team?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dao.delete(new Team(teamID, null, null));
                loadData();
                clearForm();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Team ID must be an integer.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error deleting team: " + e.getMessage());
        }
    }

    private void searchTeam() {
        String keyword = view.getTxtSearch().getText().trim();
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);
        ArrayList<Team> list = dao.search(keyword);
        for (Team team : list) {
            model.addRow(new Object[]{
                    team.getTeam_ID(),
                    team.getTeam_name(),
                    team.getMeeting_frequency()
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
        view.getBtnAdd().addActionListener(e -> addTeam());
        view.getBtnUpdate().addActionListener(e -> updateTeam());
        view.getBtnDelete().addActionListener(e -> deleteTeam());
	    view.getBtnGroupStats().addActionListener(e -> showGroupStats());
        view.getBtnRefresh().addActionListener(e -> {
            loadData();
            clearForm();
        });
        view.getBtnSearch().addActionListener(e -> searchTeam());

        view.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable().getSelectedRow();
                if (row >= 0) {
                    view.getTxtTeamID().setText(view.getTableModel().getValueAt(row, 0).toString());
                    view.getTxtTeamName().setText(view.getTableModel().getValueAt(row, 1).toString());
                    view.getTxtMeetingFrequency().setText(view.getTableModel().getValueAt(row, 2).toString());
                }
            }
        });
    }
}