package Domain.Obstacles;

import java.util.Random;

public class ExplosiveObstacle extends SuperObstacle {

	Random random = new Random();
	int hp = 1;
	int type = 2;
	private boolean isExplode = false;
	private int velocity;
	int ID = 0;

	public ExplosiveObstacle(int initX, int initY) {
		super(initX, initY);
		super.setWidth(30);
		super.setHeight(30);
		hp = 1;
		ID = random.nextInt(1000);
		type = 2;
		velocity = 0;
	}


	@Override
	public void explodeObs() {
		isExplode = true;
		setVelocity();
	}

	public int getType() {
		return 2;
	}

	public int getID() {
		return ID;
	}

	public int getVelocity() {
		return this.velocity;
	}

	public void setVelocity() {
		this.velocity = this.velocity - 10;
	}

	public boolean getExplode() {
		return isExplode;
	}
	
	public void lowerHP() {
		this.hp = hp - 1;
	}
	
	public int getHP() {
		return hp;
	}

	public void setExplode(boolean explode) {
		isExplode = explode;
	}

	public void MoveEplosive() {

		this.velocity = -10;
	}

}