package view.race;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.ResultDAO;
import model.Result;
import model.Stage;
import model.User;

public class UpdateResultFrm extends JFrame implements ActionListener {
	private JTable tblRacers;
	private JButton btnUpdate;
	private JButton btnReset;
	private User user;
	private Stage stage;
	private ArrayList<Result> results;

	public UpdateResultFrm(User user, Stage stage) {
		super("Update Results - " + stage.getName());
		this.user = user;
		this.stage = stage;

		ResultDAO resultDAO = new ResultDAO();
		results = resultDAO.getRegisteredRacers(stage.getId());

		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.PAGE_AXIS));
		pnMain.add(Box.createRigidArea(new Dimension(0, 10)));

		JLabel lblTitle = new JLabel("Update Results: " + stage.getName());
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitle.setFont(lblTitle.getFont().deriveFont(20.0f));
		pnMain.add(lblTitle);
		pnMain.add(Box.createRigidArea(new Dimension(0, 5)));

		JLabel lblInfo = new JLabel("Location: " + stage.getLocation() + " | Laps: " + stage.getNumberLaps());
		lblInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnMain.add(lblInfo);
		pnMain.add(Box.createRigidArea(new Dimension(0, 10)));

		String[] columnNames = {"Driver Code", "Racer Name", "Team", "Finish Time", "Laps Completed"};
		String[][] data = new String[results.size()][5];
		for (int i = 0; i < results.size(); i++) {
			Result r = results.get(i);
			data[i][0] = r.getContract().getRacer().getDriverCode();
			data[i][1] = r.getContract().getRacer().getName();
			data[i][2] = r.getContract().getTeam().getName();
			data[i][3] = r.getFinishTime() != null ? r.getFinishTime() : "";
			data[i][4] = String.valueOf(r.getLapsCompleted());
		}

		DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
			public boolean isCellEditable(int row, int column) {
				return column == 3 || column == 4;
			}
		};
		tblRacers = new JTable(tableModel);
		tblRacers.setRowHeight(25);

		JScrollPane scrollPane = new JScrollPane(tblRacers);
		scrollPane.setPreferredSize(new Dimension(700, 250));
		pnMain.add(scrollPane);
		pnMain.add(Box.createRigidArea(new Dimension(0, 10)));

		JPanel pnButtons = new JPanel();
		pnButtons.setLayout(new FlowLayout());

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(this);
		pnButtons.add(btnUpdate);

		btnReset = new JButton("Reset");
		btnReset.addActionListener(this);
		pnButtons.add(btnReset);

		pnMain.add(pnButtons);

		this.add(pnMain);
		this.setSize(750, 450);
		this.setLocation(150, 10);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btnClicked = (JButton) e.getSource();

		if (btnClicked.equals(btnUpdate)) {
			if (tblRacers.isEditing()) {
				tblRacers.getCellEditor().stopCellEditing();
			}

			DefaultTableModel model = (DefaultTableModel) tblRacers.getModel();

			for (int i = 0; i < results.size(); i++) {
				Result r = results.get(i);
				String fTime = (String) model.getValueAt(i, 3);
				String lapsStr = (String) model.getValueAt(i, 4);

				int laps = 0;
				if (lapsStr != null && !lapsStr.trim().isEmpty()) {
					try {
						laps = Integer.parseInt(lapsStr.trim());
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(this, "Error not correct laps");
						return;
					}
				}

				if (laps > stage.getNumberLaps() || laps < 0) {
					JOptionPane.showMessageDialog(this, "Invalid laps");
					return;
				}

				boolean hasTime = (fTime != null && !fTime.trim().isEmpty());
				if (hasTime) {
					String[] parts = fTime.trim().split(":");
					if (parts.length != 3) {
						JOptionPane.showMessageDialog(this, "Error correct time");
						return;
					}
					try {
						int h = Integer.parseInt(parts[0]);
						int m = Integer.parseInt(parts[1]);
						int s = Integer.parseInt(parts[2]);
						if (h < 0 || m < 0 || m > 59 || s < 0 || s > 59) {
							JOptionPane.showMessageDialog(this, "Error correct time");
							return;
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(this, "Error correct time");
						return;
					}

					if (laps < stage.getNumberLaps()) {
						JOptionPane.showMessageDialog(this, "Error: inconsistent data");
						return;
					}
				} else {
					if (laps == stage.getNumberLaps()) {
						JOptionPane.showMessageDialog(this, "Missing finish time but full laps");
						return;
					}
				}

				r.setFinishTime(fTime != null ? fTime.trim() : "");
				r.setLapsCompleted(laps);
				r.setStage(stage);
				r.setUser(user);
			}

			ResultDAO resultDAO = new ResultDAO();
			if (resultDAO.updateRaceResults(results)) {
				JOptionPane.showMessageDialog(this, "Results updated successfully!");
				(new view.user.StaffHomeFrm(user)).setVisible(true);
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Failed to update results!");
			}

		} else if (btnClicked.equals(btnReset)) {
			ResultDAO resultDAO = new ResultDAO();
			results = resultDAO.getRegisteredRacers(stage.getId());

			DefaultTableModel model = (DefaultTableModel) tblRacers.getModel();
			model.setRowCount(0);
			for (Result r : results) {
				model.addRow(new String[]{
						r.getContract().getRacer().getDriverCode(),
						r.getContract().getRacer().getName(),
						r.getContract().getTeam().getName(),
						r.getFinishTime() != null ? r.getFinishTime() : "",
						String.valueOf(r.getLapsCompleted())
				});
			}
		}
	}
}
