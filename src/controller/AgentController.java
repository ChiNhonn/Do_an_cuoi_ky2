package controller;

import java.awt.DefaultFocusTraversalPolicy;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.AgentDAO;
import model.Agent;
import view.AgentManagementJPanel;

public class AgentController {
	private AgentManagementJPanel view;
	private AgentDAO dao;
	public AgentController(AgentManagementJPanel view) {
		this.view = view;
		this.dao = new AgentDAO();
		loadData();
		addEventHandlers();
	}
	
	private void loadData() {
		DefaultTableModel model = view.getTableModel();
		model.setRowCount(0);
		ArrayList<Agent> list = AgentDAO.getInstance().selectALL();
		for(Agent a : list) {
			model.addRow(new Object[] {
					a.getAgent_ID(),
					a.getFirst_name(),
					a.getLast_name(),
					a.getAddrest(),
					a.getCity(),
					a.getCountry(),
					a.getSalary()
			});
		}
		view.getLblCount().setText("Total: "+ list.size());
	}
	
	private void clearForm() {
		view.getTxtAgentID().setText("");
		view.getTxtFirstName().setText("");
		view.getTxtLastName().setText("");
		view.getTxtAddress().setText("");
		view.getTxtCity().setText("");
		view.getTxtCountry().setText("");
		view.getTxtSalary().setText("");
	}
	
	private void addAgent() {
		try {
			int id = Integer.parseInt(view.getTxtAgentID().getText().trim());
			String firstname = view.getTxtFirstName().getText().trim();
			String lastname = view.getTxtLastName().getText().trim();
			String address = view.getTxtAddress().getText().trim();
			String city = view.getTxtCity().getText().trim();
			String country = view.getTxtCountry().getText().trim();
			float salary = Float.parseFloat(view.getTxtSalary().getText().trim());
			
			if(firstname.isEmpty() || lastname.isEmpty() ||
					address.isEmpty() || city.isEmpty() ||
					country.isEmpty()) {
				JOptionPane.showConfirmDialog(view, "Please fill in all fields");
				return;
			}
			
			Agent a = new Agent(id, firstname, lastname, address, city, country, salary);
			if (AgentDAO.getInstance().exists(a)) {
			JOptionPane.showMessageDialog(view, "Agent ID or full name already exists!");
			return;
		}
			AgentDAO.getInstance().insert(a);
			loadData();
			clearForm();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
		}
	}
	private void updateAgent() {
		try {
			int id = Integer.parseInt(view.getTxtAgentID().getText().trim());
			String firstName = view.getTxtFirstName().getText().trim();
			String lastName = view.getTxtLastName().getText().trim();
			String address = view.getTxtAddress().getText().trim();
			String city = view.getTxtCity().getText().trim();
			String country = view.getTxtCountry().getText().trim();
			float salary = Float.parseFloat(view.getTxtSalary().getText().trim());

			if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || city.isEmpty() || country.isEmpty()) {
			JOptionPane.showMessageDialog(view, "Please fill in all fields!");
			return;
		}

			Agent a = new Agent(id, firstName, lastName, address, city, country, salary);
			AgentDAO.getInstance().update(a);
			loadData();
			clearForm();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(view, "ID must be an integer and salary must be a number!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
		}
	}
	private void deleteAgent() {
		try {
			int id = Integer.parseInt(view.getTxtAgentID().getText().trim());
			int confirm = JOptionPane.showConfirmDialog(view, "Are you sure to delete this record?", "Confirm", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
			AgentDAO.getInstance().delete(new Agent(id, null, null, null, null, null, 0));
			loadData();
			clearForm();
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(view, "ID must be an integer!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
		}
	}
	private void searchAgent() {
		String keyword = view.getTxtSearch().getText().trim();
		DefaultTableModel model = view.getTableModel();
		model.setRowCount(0);
		ArrayList<Agent> list = AgentDAO.getInstance().search(keyword);
		for (Agent a : list) {
			model.addRow(new Object[]{
			a.getAgent_ID(), 
			a.getFirst_name(), 
			a.getLast_name(), 
			a.getAddrest(), 
			a.getCity(), 
			a.getCountry(), 
			a.getSalary()
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
	    view.getBtnSearch().addActionListener(e -> searchAgent());
	    view.getBtnRefresh().addActionListener(e -> {
	        loadData();
	        clearForm();
	    });
	    view.getBtnAdd().addActionListener(e -> addAgent());
	    view.getBtnUpdate().addActionListener(e -> updateAgent());
	    view.getBtnDelete().addActionListener(e -> deleteAgent());
	    view.getBtnGroupStats().addActionListener(e -> showGroupStats());
	    view.getTable().addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            int row = view.getTable().getSelectedRow();
	            if (row >= 0) {
	                view.getTxtAgentID().setText(view.getTableModel().getValueAt(row, 0).toString());
	                view.getTxtFirstName().setText(view.getTableModel().getValueAt(row, 1).toString());
	                view.getTxtLastName().setText(view.getTableModel().getValueAt(row, 2).toString());
	                view.getTxtAddress().setText(view.getTableModel().getValueAt(row, 3).toString());
	                view.getTxtCity().setText(view.getTableModel().getValueAt(row, 4).toString());
	                view.getTxtCountry().setText(view.getTableModel().getValueAt(row, 5).toString());
	                view.getTxtSalary().setText(view.getTableModel().getValueAt(row, 6).toString());
	            }
	        }
	    });
	}
	
}
