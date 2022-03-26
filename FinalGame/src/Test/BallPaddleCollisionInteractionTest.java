package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Domain.GamePlay.Ball;
import Domain.GamePlay.Collision;
import Domain.GamePlay.Game;
import Domain.GamePlay.Paddle;

class BallPaddleCollisionInteractionTest {
	
	Ball ball;
	Paddle paddle;
	Game game;
	Collision collision;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		paddle = Paddle.getInstance();
		ball = Ball.getInstance();
		game = Game.getInstance();
		collision = Collision.getInstance();
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}
	

	
	@Test
	void ball_rigth_paddle_rigth() {
		ball.setVelocityX(5);
		paddle.setVelocity(10);
		setBallAndPaddlePosition(ball, paddle);
		
		System.out.println(collision.getRectangle(Game.getInstance().getBall())
				.intersects(collision.getRectangle(Game.getInstance().getPaddle())));
		collision = new Collision();
		collision.IsCollidePaddle();
		System.out.println(ball.getVelocityX());
		assertTrue(ball.getVelocityX() > 5 );
		
		
	}
	
	@Test
	void ball_left_paddle_left() {
		
		ball.setVelocityX(-5);
		paddle.setVelocity(-10);
		setBallAndPaddlePosition(ball, paddle);
		
		System.out.println(collision.getRectangle(Game.getInstance().getBall())
				.intersects(collision.getRectangle(Game.getInstance().getPaddle())));
		collision = new Collision();
		collision.IsCollidePaddle();
		System.out.println(ball.getVelocityX());
		assertTrue(ball.getVelocityX() < -5 );
		
		
	}
	
	@Test
	void ball_left_paddle_remains() {
		
		ball.setVelocityX(-5);
		paddle.setVelocity(0);
		setBallAndPaddlePosition(ball, paddle);
		
		System.out.println(collision.getRectangle(Game.getInstance().getBall())
				.intersects(collision.getRectangle(Game.getInstance().getPaddle())));
		collision = new Collision();
		collision.IsCollidePaddle();
		System.out.println(ball.getVelocityX());
		assertTrue(ball.getVelocityX() == -5 );
		
		
	}
	
	@Test
	void ball_rigth_paddle_remains() {
		
		ball.setVelocityX(5);
		paddle.setVelocity(0);
		setBallAndPaddlePosition(ball, paddle);
		
		System.out.println(collision.getRectangle(Game.getInstance().getBall())
				.intersects(collision.getRectangle(Game.getInstance().getPaddle())));
		collision = new Collision();
		collision.IsCollidePaddle();
		System.out.println(ball.getVelocityX());
		assertTrue(ball.getVelocityX() == 5 );
		
		
	}
	
	@Test
	void ball_perpendicular_paddle_rigth() {
		
		ball.setVelocityX(0);
		paddle.setVelocity(-10);
		setBallAndPaddlePosition(ball, paddle);
		
		System.out.println(collision.getRectangle(Game.getInstance().getBall())
				.intersects(collision.getRectangle(Game.getInstance().getPaddle())));
		collision = new Collision();
		collision.IsCollidePaddle();
		System.out.println(ball.getVelocityX());
		System.out.println("saodfjhsadkjfhsdf");
		assertTrue(ball.getVelocityX() < 0);
		
		
		
	}
	
	//helper function to test
	void setBallAndPaddlePosition(Ball ball, Paddle paddle) {
		paddle.setPositionX(500);
		paddle.setPositionY(600);
		ball.setPositionX(500);
		ball.setPositionY(600);
	}

}
