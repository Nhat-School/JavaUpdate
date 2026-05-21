package view.user;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import model.User;
import view.race.SelectRaceFrm;

public class StaffHomeFrm extends JFrame implements ActionListener {
	private JButton btnUpdateResults;
	private User user;

	public StaffHomeFrm(User user) {
		super("Staff Home");
		this.user = user;

		JPanel listPane = new JPanel();
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

		JPanel lblPane = new JPanel();
		lblPane.setLayout(new BoxLayout(lblPane, BoxLayout.LINE_AXIS));
		lblPane.add(Box.createRigidArea(new Dimension(450, 0)));
		JLabel lblUser = new JLabel("Staff: " + user.getFullName());
		lblUser.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblPane.add(lblUser);
		listPane.add(lblPane);
		listPane.add(Box.createRigidArea(new Dimension(0, 20)));

		JLabel lblHome = new JLabel("Staff Home");
		lblHome.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblHome.setFont(lblHome.getFont().deriveFont(28.0f));
		listPane.add(lblHome);
		listPane.add(Box.createRigidArea(new Dimension(0, 20)));

		btnUpdateResults = new JButton("Update Results");
		btnUpdateResults.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnUpdateResults.addActionListener(this);
		listPane.add(btnUpdateResults);

		this.setSize(600, 300);
		this.setLocation(200, 10);
		this.add(listPane, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		(new SelectRaceFrm(user)).setVisible(true);
		this.dispose();
	}
}
