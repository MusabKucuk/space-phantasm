package Domain.Login;

public class UserSession { //unused 
	@SuppressWarnings("unused")
	private  String nickname;
	@SuppressWarnings("unused")
	private  String  password;
	@SuppressWarnings("unused")
	private int score;
	private boolean loggedIn;
	
	
	public UserSession(String nickname, String password) {
		this.nickname = nickname;
		this.password = password; 
		score = 0;
	}
	
	
	public boolean getLoggedIn() {
		return this.loggedIn;
	}
	
	public void setLoggedIn(boolean xyz) {
		this.loggedIn = xyz;
	}	
}
