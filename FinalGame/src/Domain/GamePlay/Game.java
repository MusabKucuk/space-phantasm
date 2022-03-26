package Domain.GamePlay;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

import Domain.Abilities.CGability;
import Domain.Abilities.MagicalHex;
import Domain.Abilities.PaddleExpansionAbility;
import Domain.Abilities.UnstoppableAbility;
import Domain.Obstacles.ExplosiveObstacle;
import Domain.Obstacles.FirmObstacle;
import Domain.Obstacles.PurpleObstacle;
import Domain.Obstacles.SuperObstacle;
import Domain.Ymir.HollowPurpleAbility;
import Domain.Ymir.Ymir;

public class Game {
	private boolean isRunning = true;
	private boolean isBallIntersectSimpleObs = false;
	private int time;
	private int score = 0;
	private static Game instance;
	Paddle paddle;
	Ball ball;
	SuperObstacle superObstacle;
	FirmObstacle firmObstacle;
	ExplosiveObstacle explosiveObstacle;
	Collision collision;
	Timer tm;
	PaddleExpansionAbility paddleExpansionAbility;
	Random rand = new Random();
	ArrayList<SuperObstacle> simpleObsArray = null;
	Ymir ymir;
	HollowPurpleAbility hollowObject;
	int a = 0;
	double tempBallVel;
	MagicalHex magicalHex;
	UnstoppableAbility unstoppableAbility;
	CGability cGability;
	ArrayList<Integer> inventory = new ArrayList<Integer>();

	public Game() {
		paddle = Paddle.getInstance();
		ball = Ball.getInstance();
		superObstacle = new SuperObstacle(400, 400);
		firmObstacle = new FirmObstacle(400, 400);
		explosiveObstacle = new ExplosiveObstacle(400, 400);
		collision = Collision.getInstance();
		paddleExpansionAbility = new PaddleExpansionAbility();
		hollowObject = new HollowPurpleAbility();
		magicalHex = MagicalHex.getInstance();
		unstoppableAbility = new UnstoppableAbility();
		cGability = new CGability();
		tempBallVel = ball.getTotalVelocity();
	}

	public static Game getInstance() {
		if (instance == null)
			instance = new Game();
		return instance;
	}

	public void MoveObstacles() {
		for (int i = 0; i < simpleObsArray.size(); i++) {
			if (simpleObsArray.get(i).getVelocity() < 0) {
				simpleObsArray.get(i)
						.setPositionY(simpleObsArray.get(i).getPositionY() - simpleObsArray.get(i).getVelocity());
				if (simpleObsArray.get(i).getPositionY() > 800) {
					simpleObsArray.remove(i);
				}
			}
		}
	}

	public void MoveBall() {

		if (tempBallVel != ball.getTotalVelocity()) {
			System.out.println(ball.getTotalVelocity());
			tempBallVel = ball.getTotalVelocity();
		}
		if (a == 0) {
			ymir = new Ymir();
			ymir.coinFlip();
			a = 1;
		}
		// System.out.println(simpleObsArray.size() + "********");
		if ((ball.getPositionY() > 40 && !collision.getRectangle(Game.getInstance().getBall())
				.intersects(collision.getRectangle(Game.getInstance().getPaddle())))
				|| (collision.isBallCollidePaddle == true && ball.getPositionY() > 40)) {
			ball.setPositionY(ball.getPositionY() - ball.getVelocityY());
			ball.setPositionX(ball.getPositionX() + ball.getVelocityX());
			collision.preventSecondCollide = 0;
			collision.isBallCollidePaddle = false;
			collision.counter = 0;

			if (ball.getPositionX() <= -1 || ball.getPositionX() >= 1170) {
				ball.setVelocityX(-1 * ball.getVelocityX());
			}

		} else if (ball.getPositionY() <= 40) {

			ball.setVelocityY(-1 * ball.getVelocityY());
			ball.setPositionY(ball.getPositionY() - 2 * ball.getVelocityY());
		}

		else if (collision.getRectangle(Game.getInstance().getBall())
				.intersects(collision.getRectangle(Game.getInstance().getPaddle()))) {

			collision.IsCollidePaddle();
		}

		for (int i = 0; i < getSimpleObsArray().size(); ++i) {
			if (collision.getRectangle(Game.getInstance().getBall())
					.intersects(collision.getRectangle(Game.getInstance().getSimpleObsArray().get(i)))) {

				if (!Game.getInstance().simpleObsArray.get(i).getExplode()) {
					if (unstoppableAbility.isActivated() == false) {

//						if (ball.getVelocityY() > 0) {
//							ball.setVelocityY(-1 * ball.getVelocityY());
//						}
//						ball.setVelocityX(-1 * ball.getVelocityX());
					}
				}
				collision.isCollide();
				collision.setBallIntersectWithSimpleObs(false);
			}
		}

		if (ball.getPositionY() > 750 && !PaddleBallCollision()) {
			try {
				TimeUnit.MILLISECONDS.sleep(50);
				paddle.lowerPaddleHP();
				if (paddle.getPaddleHP() == 0) {
					System.exit(0); // GAME OVER CONDITION!
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			ball = Ball.getInstance();
			paddle.setPositionX(540);
			ball.setPositionX(paddle.getPositionX() + ((paddle.getWidth() - ball.getWidth()) / 2));
			ball.setPositionY(715);
			ball.setVelocityX(0);
			ball.setVelocityY(6);
			ball.setStarted(false);
		}
	}

	public boolean PaddleBallCollision() {

		if (ball.getPositionY() + ball.getHeight() >= paddle.getPositionY()
				&& ball.getPositionX() >= paddle.getPositionX() - 8
				&& ball.getPositionX() <= paddle.getPositionX() + paddle.getWidth()) {
			return true;

		} else {
			return false;
		}
	}

	public void increaseScore(int time) {
		score = score + 300 / (time + 1);
	}

	public void generateArrayListfromLoad(String saveString) {
		setSimpleObsArray(ObstacleFactory.getInstance().getObstaclesBySavedGame(saveString));
		setScore(ObstacleFactory.getInstance().getSavedScore());
		paddle.setPaddleHP(ObstacleFactory.getInstance().getSavedHp());
		
		if(ObstacleFactory.getInstance().getPaddleExpansion() == 1) {
		inventory.add(1);
		}
	
		if(ObstacleFactory.getInstance().getHexAbility() == 1) {
			inventory.add(0);
			}
	}

	public ArrayList<SuperObstacle> randomSuperObstacleArrayGenerator(int SimpleObstacleNum, int FirmObstacleNum,
			int explosiveObsNum, int magicalObsNum) {
		return ObstacleFactory.getInstance().randomObstacleGenerator(SimpleObstacleNum, FirmObstacleNum,
				explosiveObsNum, magicalObsNum);
	}

	public void purpleObstacleAppender() {
		// simpleObsArray
		ArrayList<PurpleObstacle> purpleObstacleArray = hollowObject.createPurpleObstacle();
		for (int i = 0; i < purpleObstacleArray.size(); i++) {
			getSimpleObsArray().add(purpleObstacleArray.get(i));
		}
	}

	public String ObstacleListStateSaver() {
		String result = "";
		String[] temp_inventory = new String[2];
		temp_inventory[0] = "0";
		temp_inventory[1] = "0";
		
		if(inventory.contains(1)) {
			temp_inventory[0] = "1";
		}
		if(inventory.contains(0)) {
			temp_inventory[1] = "1";
		}

		for (int i = 0; i < simpleObsArray.size(); i++) {

			String temp = (simpleObsArray.get(i).getPositionX() + "_" + simpleObsArray.get(i).getPositionY() + "_"
					+ simpleObsArray.get(i).getType() + "_" + paddle.getPaddleHPString() + "_" + getScoreAsString()
					+ "_" + temp_inventory[0] + "_" + temp_inventory[1] + "&");
			
			result = result + temp;

		}

	
		return result;
	}

	public String getScoreAsString() {
		String s = Integer.toString(getScore());
		return s;
	}

	public void setRunning(boolean xyz) {
		this.isRunning = xyz;
	}

	public boolean getIsRunning() {
		return this.isRunning;
	}

	public Paddle getPaddle() {
		return paddle;
	}

	public Ball getBall() {
		return ball;
	}

	public ArrayList<SuperObstacle> getSimpleObsArray() {
		return simpleObsArray;
	}

	public void setSimpleObsArray(ArrayList<SuperObstacle> simpleObsArray) {
		this.simpleObsArray = simpleObsArray;
	}

	public SuperObstacle getSimpleObstacle() {
		return superObstacle;
	}

	public void RotatePaddle(String direction) {
		paddle.rotatePaddle(direction);
	}

	public void MovePaddle(String left_or_right) {
		paddle.changePaddleLocation(left_or_right);
	}

	public boolean isBallIntersectSimpleObs() {
		return isBallIntersectSimpleObs;
	}

	public void setBallIntersectSimpleObs(boolean ballIntersectSimpleObs) {
		isBallIntersectSimpleObs = ballIntersectSimpleObs;
	}

	public ArrayList<SuperObstacle> getSimpleObstacleArray() {
		return simpleObsArray;
	}

	public boolean isExplode() {
		return superObstacle.getExplode();
	}

	public ExplosiveObstacle getExplosiveObstacle() {
		return explosiveObstacle;
	}

	public void setExplosiveObstacle(ExplosiveObstacle explosiveObstacle) {
		this.explosiveObstacle = explosiveObstacle;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public ArrayList<Integer> getInventory() {
		return inventory;
	}

	public void setInventory(ArrayList<Integer> inventory) {
		this.inventory = inventory;
	}

	public PaddleExpansionAbility getPaddleExpansionAbility() {
		return paddleExpansionAbility;
	}

	public MagicalHex getMagicalHex() {
		return magicalHex;
	}

	public Collision getCollision() {
		return collision;
	}

	public UnstoppableAbility getUnstoppableAbility() {
		return unstoppableAbility;
	}

	public CGability getcGability() {
		return cGability;
	}
	
}
