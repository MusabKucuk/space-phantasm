package Domain;

import javax.swing.SwingUtilities;

import Domain.Login.LoginHandler;
import UI.NickNameGUI;
import UI.PasswordGUI;

public class programStartup {
	private static NickNameGUI NickNameGUI;
	private static PasswordGUI PasswordGUI;
	
	private static Domain.Login.LoginHandler LoginHandler;

	public static void main(String[] args) {
		LoginHandler = new LoginHandler();
				
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
			CreateAndShowGUI();
			}
		});	
	}
	
	private static void CreateAndShowGUI() {
		NickNameGUI = new NickNameGUI(LoginHandler);
		NickNameGUI.setVisible(true);
		PasswordGUI = new PasswordGUI(LoginHandler);
		PasswordGUI.setVisible(false);		
	}
}