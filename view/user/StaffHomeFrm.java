package view.user;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import model.User;
import view.race.SelectRaceFrm;

public class StaffHomeFrm extends JFrame implements ActionListener {
	private JButton btnUpdateResults;
	private JButton btnRegister;
	private JButton btnViewStats;
	private User user;

	public StaffHomeFrm(User user) {
		super("Staff Home");
		this.user = user;

		JPanel listPane = new JPanel();
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

		
		JLabel lblHome = new JLabel("Staff Home");
		lblHome.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblHome.setFont(lblHome.getFont().deriveFont(28.0f));
		listPane.add(lblHome);
		listPane.add(Box.createRigidArea(new Dimension(0, 20)));

		
		btnRegister = new JButton("Register");
		btnRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
		listPane.add(btnRegister);
		listPane.add(Box.createRigidArea(new Dimension(0, 10)));


		btnUpdateResults = new JButton("Update Results");
		btnUpdateResults.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnUpdateResults.addActionListener(this);
		listPane.add(btnUpdateResults);
		listPane.add(Box.createRigidArea(new Dimension(0, 10)));



		btnViewStats = new JButton("View Statistics");
		btnViewStats.setAlignmentX(Component.CENTER_ALIGNMENT);
		listPane.add(btnViewStats);

		this.setSize(600, 300);
		this.setLocation(200, 10);
		this.add(listPane, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnUpdateResults)) {
			(new SelectRaceFrm(user)).setVisible(true);
			this.dispose();
		}
	}
}
