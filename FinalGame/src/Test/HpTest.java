package Test;

import Domain.GamePlay.Collision;
import Domain.GamePlay.Game;
import Domain.GamePlay.Paddle;
import Domain.Obstacles.ExplosiveObstacle;
import Domain.Obstacles.FirmObstacle;
import Domain.Obstacles.MagicalObstacle;
import Domain.Obstacles.SuperObstacle;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

//import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class HpTest {

	Paddle paddle;
	Collision collision;
	SuperObstacle superObstacle;
	ExplosiveObstacle explosiveObstacle;
	FirmObstacle firmObstacle;
	MagicalObstacle magicalObstacle;

	@BeforeEach
	void setUp() throws Exception {
		paddle = Paddle.getInstance();
		superObstacle = new SuperObstacle(20, 15);
		explosiveObstacle = new ExplosiveObstacle(20, 15);
		firmObstacle = new FirmObstacle(20, 15);
		magicalObstacle = new MagicalObstacle(20, 15);
	}

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void InitialHPtest() {

		assertTrue(paddle.getPaddleHP() == 3);
		assertFalse(paddle.getPaddleHP() > 3);

		assertTrue(superObstacle.getHP() == 1);
		assertFalse(superObstacle.getHP() != 1);

		assertTrue(explosiveObstacle.getHP() == 1);
		assertFalse(explosiveObstacle.getHP() != 1);

		assertTrue(firmObstacle.getHP() <= 5 && firmObstacle.getHP() >= 2);
		assertFalse(firmObstacle.getHP() > 5);

		assertTrue(magicalObstacle.getHP() == 1);
		assertFalse(magicalObstacle.getHP() != 1);

	}

	@Test
	void afterCollisionSimpleTest() {
		
		Game.getInstance().getBall().setPositionX(20);
		Game.getInstance().getBall().setPositionY(15);
		
		ArrayList<SuperObstacle> ObsArray = new ArrayList<SuperObstacle>();
		
		ObsArray.add(superObstacle);	
		Game.getInstance().setSimpleObsArray(ObsArray);
		
		collision = new Collision();
		collision.isCollide();
		assertTrue(superObstacle.getHP() == 0);
	}
	
	@Test
	void afterCollisionExplosiveTest() {
		
		Game.getInstance().getBall().setPositionX(20);
		Game.getInstance().getBall().setPositionY(15);
		
		ArrayList<SuperObstacle> ObsArray = new ArrayList<SuperObstacle>();
		
		ObsArray.add(explosiveObstacle);
		Game.getInstance().setSimpleObsArray(ObsArray);
		
		collision = new Collision();
		collision.isCollide();
		assertTrue(explosiveObstacle.getHP() == 0);
	}
	
	@Test
	void afterCollisionFirmTest() {
		
		Game.getInstance().getBall().setPositionX(20);
		Game.getInstance().getBall().setPositionY(15);
		
		ArrayList<SuperObstacle> ObsArray = new ArrayList<SuperObstacle>();
		
		ObsArray.add(firmObstacle);
		Game.getInstance().setSimpleObsArray(ObsArray);
		
		collision = new Collision();
		collision.isCollide();
		assertTrue(firmObstacle.getHP() < 5 && firmObstacle.getHP() >= 1);
	}
	
	@Test
	void afterCollisionMagicalTest() {
		
		Game.getInstance().getBall().setPositionX(20);
		Game.getInstance().getBall().setPositionY(15);
		
		ArrayList<SuperObstacle> ObsArray = new ArrayList<SuperObstacle>();
		
		ObsArray.add(magicalObstacle);
		Game.getInstance().setSimpleObsArray(ObsArray);
		
		collision = new Collision();
		collision.isCollide();
		assertTrue(magicalObstacle.getHP() == 0);
	}

}
