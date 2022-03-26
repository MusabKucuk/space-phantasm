package Domain.Obstacles;

import java.util.Random;

public class FirmObstacle extends SuperObstacle {

	Random random = new Random();
	int hp = random.nextInt(5 - 2) + 2;
	int type = 1;
	int ID = 0;

	public FirmObstacle(int initX, int initY) {
		super(initX, initY);
		super.setWidth(24);
		super.setHeight(20);
		hp = random.nextInt(5 - 2) + 2;
		ID = random.nextInt(1000);
		type = 1;
	}

	

	public String getFirmHPString() {
		String s = Integer.toString(getHP());
		return s;
	}

	public int getType() {
		return 1;
	}

	public int getID() {
		return ID;
	}

	public int getHP() {
		return hp;
	}

	public void lowerHP() {
		this.hp = hp - 1;
	}

}
