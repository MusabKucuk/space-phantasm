package Test;

import Domain.Login.Login;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class loginTest {
	
	 // Modifies: Login Object 
	 // Requires: Login Handler, User Input, credentials Database
	 // Effects:  Signals controller to initiate the game, if login is successfull
	
	Login login;
	String nonexistent_nickname; 
	String existent_nickname;
	ArrayList<String> nicknamesInDB;
	ArrayList<String> passwordsInDB;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

@BeforeEach
	void setUp() throws Exception {
		nicknamesInDB = new ArrayList<String>();
		passwordsInDB = new ArrayList<String>();
		
		nicknamesInDB.add("yavuz");
		nicknamesInDB.add("burak");
		nicknamesInDB.add("doga");
		nicknamesInDB.add("musab");
		nicknamesInDB.add("cem");
		nicknamesInDB.add("efe");
		nicknamesInDB.add("nazir");
		nicknamesInDB.add("attila");
		
		
		passwordsInDB.add("1234");
		passwordsInDB.add("mypassword");
		passwordsInDB.add("zongulduck");
		passwordsInDB.add("yavuzefe");
		passwordsInDB.add("doga");
		passwordsInDB.add("e");
		passwordsInDB.add("bestTA");
		passwordsInDB.add("gursoy");
		
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void nonExistentNicknameTest() {
	   
		nonexistent_nickname = "randomString";
		login = new Login(nonexistent_nickname);
		assertFalse(login.CheckNickName());
		
	}
	
	@Test
	void NicknameFoundInDatabaseTest() {
	
		for(int i=0; i < nicknamesInDB.size(); i++) {
			existent_nickname = nicknamesInDB.get(i);
			login = new Login(existent_nickname);
			assertTrue(login.CheckNickName());
		}
	
	}
	
	@Test
	void EmptyInputTest() {
		String empty = "";
		login = new Login(empty);
		assertFalse(login.CheckNickName());
	}
	
	
	@Test
	void wrongPasswordTest() {
		for(int i=0; i < nicknamesInDB.size(); i++) {
			existent_nickname = nicknamesInDB.get(i);
			login = new Login(existent_nickname);
			assertTrue(login.CheckNickName());
			assertFalse(login.CheckPassword("anyString"));
		}
	}
		
		
		@Test
		void successfulTest() {
			for(int i=0; i < nicknamesInDB.size(); i++) {
				existent_nickname = nicknamesInDB.get(i);
				login = new Login(existent_nickname);
				assertTrue(login.CheckNickName());
				assertTrue(login.CheckPassword(passwordsInDB.get(i)));
			}
		
		}
	
}
