package Domain.Obstacles;

import java.util.Random;

public class MagicalObstacle extends SuperObstacle {
	
	Random random = new Random();
	int hp = 1;
	int type = 3;
	int ID = 0;
	private boolean isExplode = false;
	private int velocity;
	private boolean touchedPaddle;

	public MagicalObstacle(int initX, int initY) {
		super(initX, initY);
		super.setWidth(24);
		super.setHeight(20);
		ID = random.nextInt(3);
		type = 3;
		hp = 1;
		velocity = 0;
		touchedPaddle = false;
	}

	
	public boolean hasTouchedPaddle() {
		return touchedPaddle;
	}


	public void setHasTouchedPaddle(boolean hasTouchedPaddle) {
		this.touchedPaddle = hasTouchedPaddle;
	}
	
	@Override
	public void explodeObs() {
		isExplode = true;
		setVelocity();
	}

	public void lowerHP() {
		this.hp = hp - 1;
	}
	
	public int getHP() {
		return hp;
	}
	
	public int getType() {
		return 3;
	}

	public int getID() {
		return ID;
	}

	public int getVelocity() {
		return this.velocity;
	}

	public void setVelocity() {
		this.velocity = this.velocity - 5;
	}

	public boolean getExplode() {
		return isExplode;
	}

	public void setExplode(boolean explode) {
		isExplode = explode;
	}

	public void MoveEplosive() {

		this.velocity = -10;
	}
}
