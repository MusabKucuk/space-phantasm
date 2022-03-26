package Domain.Login;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Login {
	private String nickname;
	private String password;

	protected static boolean checkNickname = false;
	protected static boolean checkPassword = false;

	public Login(String nickname) {
		this.nickname = nickname;
	}

	public boolean CheckPassword(String userInputPassword) {
		if (userInputPassword.equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean CheckNickName() {
		String str = "";
		String temp = "";
		try {
			FileInputStream fis = new FileInputStream("src/credentials.txt");
			Scanner sc = new Scanner(fis);
			while (sc.hasNextLine()) {
				str = sc.nextLine();
				temp = str;
				if (str.split("%")[0].equals(nickname) && !str.equals("")) {
					password = temp.split("%")[1];
					sc.close();
					return true;
				}
			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public String getPassword() {
		return password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
