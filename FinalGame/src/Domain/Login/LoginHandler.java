package Domain.Login;

public class LoginHandler {
	
	boolean nickNameExist = false;
	boolean passwordExist = false;
	Login newLoginAttempt;
	String UserInput;
	UserSession sessionUser;
	
	public LoginHandler() {
		}
	

	public void sendNickname(String UserInput) {
		newLoginAttempt = new Login(UserInput);
		if(newLoginAttempt.CheckNickName()) {
			setNickNameExist(true);
			this.UserInput = UserInput;
		}
		if(!newLoginAttempt.CheckNickName()) {
			setNickNameExist(false);
		}
	}

	public void sendPassword(String UserInput){
		if(newLoginAttempt.CheckPassword(UserInput)){
			setPasswordExist(true);
			createSessionForUser();
		}
		if(!newLoginAttempt.CheckPassword(UserInput)){
			setPasswordExist(false);
		}

	}
	

	public void createSessionForUser() {
		 @SuppressWarnings("unused")
		UserSession newSession = new UserSession(newLoginAttempt.getNickname(), newLoginAttempt.getPassword());
	}
	
	public void setNickNameExist(boolean xyz) {
		nickNameExist = xyz;
	}
	
	public boolean getNickNameExist() {
		return nickNameExist;
	}

	public void setPasswordExist(boolean xyz) {
		passwordExist = xyz;
	}

	public boolean getPasswordExist() {
		return passwordExist;
	}
	
}
