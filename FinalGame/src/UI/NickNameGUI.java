package UI;

import Domain.Login.LoginHandler;

import javax.swing.*;

@SuppressWarnings("serial")
public class NickNameGUI extends JFrame {

	static JTextField inputField;
	static JFrame Frame;
	static JButton submit_btn;
	static JButton exit_btn;
	static JLabel header;
	static JLabel l;
	static PasswordGUI passwordGUI;

	boolean isFrameDisposed = false;
	static boolean isSubmitClicked = false;
	boolean isExitBtnPressed = false;

	boolean isGoClicked = false;

	public NickNameGUI(LoginHandler loginHandler) {

		setBounds(300, 200, 1000, 600);
		setResizable(false);
		setTitle("Login Screen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		header = new JLabel("enter nickname");
		l = new JLabel("nothing entered");

		submit_btn = new JButton("submit");
		exit_btn = new JButton("exit");

		inputField = new JTextField(16);

		JPanel p = new JPanel();
		p.add(header);
		p.add(inputField);
		p.add(submit_btn);

		submit_btn.addActionListener(e -> {
			loginHandler.sendNickname(inputField.getText());

			if (loginHandler.getNickNameExist()) {
				l.setText("nickname found, logging in!");
				dispose();
				passwordGUI = new PasswordGUI(loginHandler);
				passwordGUI.setVisible(true);
			} else {
				l.setText("nickname can't be found");
			}
			inputField.setText("");

		});

		p.add(l);
		p.add(exit_btn);

		exit_btn.addActionListener(e -> {
			System.exit(-1);
		});

		p.add(new JLabel(new ImageIcon("Images/ex1.png")));
		add(p);
	}
}
