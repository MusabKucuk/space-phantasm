package UI;

import Domain.Login.LoginHandler;
import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class PasswordGUI extends JFrame implements ActionListener {

	static JTextField pswField;
	static JButton pswSubmitBtn;

	static BuildModeGUI buildModeGUI;

	static JLabel header;
	static JLabel l;

	public PasswordGUI(LoginHandler loginHandler) {

		setBounds(300, 200, 1000, 600);
		setResizable(false);
		setTitle("Password Screen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		header = new JLabel("Enter password");
		pswSubmitBtn = new JButton("GO!");
		pswSubmitBtn.addActionListener(e -> {
		});

		pswField = new JTextField(16);
		l = new JLabel("");

		JPanel pswPanel = new JPanel();
		pswPanel.add(header);
		pswPanel.add(pswSubmitBtn);
		pswPanel.add(pswField);
		pswPanel.add(l);
		pswPanel.add(new JLabel(new ImageIcon("Images/ex1.png")));
		add(pswPanel);

		pswSubmitBtn.addActionListener(e -> {
			loginHandler.sendPassword(pswField.getText());

			if (loginHandler.getPasswordExist()) {
				l.setText("Correct Password");
				dispose();
				buildModeGUI = new BuildModeGUI();
				buildModeGUI.setVisible(true);
			} else {
				l.setText("Wrong Password!");
			}
			pswField.setText("");
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}