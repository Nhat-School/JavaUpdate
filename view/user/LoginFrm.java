package view.user;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import dao.UserDao;
import model.User;

public class LoginFrm extends JFrame implements ActionListener {
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton btnLogin;

	public LoginFrm() {
		super("Login");
		txtUsername = new JTextField(15);
		txtPassword = new JPasswordField(15);
		btnLogin = new JButton("Login");

		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.PAGE_AXIS));
		pnMain.add(Box.createRigidArea(new Dimension(0, 10)));

		JLabel lblHome = new JLabel("F1 Championship - Login");
		lblHome.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblHome.setFont(lblHome.getFont().deriveFont(20.0f));
		pnMain.add(lblHome);
		pnMain.add(Box.createRigidArea(new Dimension(0, 20)));

		JPanel pnUsername = new JPanel();
		pnUsername.setLayout(new FlowLayout());
		pnUsername.add(new JLabel("Username:"));
		pnUsername.add(txtUsername);
		pnMain.add(pnUsername);

		JPanel pnPass = new JPanel();
		pnPass.setLayout(new FlowLayout());
		pnPass.add(new JLabel("Password:"));
		pnPass.add(txtPassword);
		pnMain.add(pnPass);

		pnMain.add(btnLogin);
		pnMain.add(Box.createRigidArea(new Dimension(0, 10)));
		btnLogin.addActionListener(this);
		btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.setSize(400, 200);
		this.setLocation(200, 10);
		this.setContentPane(pnMain);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if ((e.getSource() instanceof JButton) && (((JButton) e.getSource()).equals(btnLogin))) {
			User user = new User();
			user.setUsername(txtUsername.getText());
			user.setPassword(new String(txtPassword.getPassword()));

			UserDao ud = new UserDao();
			if (ud.checkLogin(user)) {
				(new StaffHomeFrm(user)).setVisible(true);
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Incorrect username and/or password!");
			}
		}
	}

	public static void main(String[] args) {
		LoginFrm myFrame = new LoginFrm();
		myFrame.setVisible(true);
	}
}
