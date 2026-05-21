package view.race;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import dao.StageDAO;
import model.Stage;
import model.User;

public class SelectRaceFrm extends JFrame implements ActionListener {
	private JComboBox<Stage> cbxStage;
	private User user;
	private JButton btnBack;

	public SelectRaceFrm(User user) {
		super("Select Race");
		this.user = user;

		StageDAO stageDAO = new StageDAO();
		ArrayList<Stage> stages = stageDAO.getAllStages();

		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.PAGE_AXIS));
		pnMain.add(Box.createRigidArea(new Dimension(0, 10)));

		JLabel lblTitle = new JLabel("Select a Race");
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitle.setFont(lblTitle.getFont().deriveFont(20.0f));
		pnMain.add(lblTitle);
		pnMain.add(Box.createRigidArea(new Dimension(0, 20)));

		JPanel pnCombo = new JPanel();
		pnCombo.setLayout(new FlowLayout());
		pnCombo.add(new JLabel("Race: "));
		cbxStage = new JComboBox<Stage>();
		for (Stage stage : stages) {
			cbxStage.addItem(stage);
		}
		cbxStage.setPreferredSize(new Dimension(350, 25));
		cbxStage.addActionListener(this);
		pnCombo.add(cbxStage);
		pnMain.add(pnCombo);
		pnMain.add(Box.createRigidArea(new Dimension(0, 20)));

		btnBack = new JButton("Back");
		btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnBack.addActionListener(this);
		pnMain.add(btnBack);

		this.setSize(500, 200);
		this.setLocation(200, 10);
		this.add(pnMain, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			(new view.user.StaffHomeFrm(user)).setVisible(true);
			this.dispose();
		} else if (e.getSource() instanceof JComboBox) {
			Stage selectedStage = (Stage) cbxStage.getSelectedItem();
			if (selectedStage != null) {
				if (selectedStage.getTime() != null && selectedStage.getTime().after(new java.util.Date())) {
					JOptionPane.showMessageDialog(this, "Please choose a stage that has already taken place.");
					return;
				}
				(new UpdateResultFrm(user, selectedStage)).setVisible(true);
				this.dispose();
			}
		}
	}
}
