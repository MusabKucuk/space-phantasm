package Domain.Obstacles;

import java.util.Random;

public class SuperObstacle {
	public boolean isExplode = false;
	@SuppressWarnings("unused")
	private boolean bol;
	private int width;
	private int height;
	private int positionX;
	private int positionY;
	int type = 0;
	int count = 0;
	Random rand = new Random();
	int prob;
	private int simpleObsNum;
	private int hp;
	private boolean isFreeze = false;

	public SuperObstacle(int initX, int initY) { // <x,y>

		this.width = 24;
		this.height = 20;
		this.positionX = initX;
		this.positionY = initY;
		type = 0;
		hp = 1;
		prob = rand.nextInt(9);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getType() {
		return 0;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getSimpleObsNum() {
		return simpleObsNum;
	}

	public void setSimpleObsNum(int simpleObsNum) {
		this.simpleObsNum = simpleObsNum;
	}

	
	public void lowerHP() {
		// REQUIRES: HP > 0
		// EFFECTS: decrease HP by 1
		this.hp = hp - 1;
	}

	public int getHP() {
		// EFFECTS: return HP
		return hp;
	}

	public void explodeObs() {

	}

	public void setExplode(boolean b) {
	}

	public boolean getExplode() {
		return bol = false;
	}

	public String getFirmHPString() {
		String s = "oley!";
		return s;
	}

	public int getVelocity() {
		return 0;
	}

	public void MoveEplosive() {

	}
	public boolean hasTouchedPaddle() {
		return bol = false;
	}


	public void setHasTouchedPaddle(boolean hasTouchedPaddle) {
		
	}

	

	public void changeObsLoc() {

		if (prob < 2) {
			if (count < 5) {
				count += 1;
				setPositionX(getPositionX() + 1);
			} else if (count >= 5 && count < 10) {
				count += 1;
				setPositionX(getPositionX() - 1);
			} else if (count >= 10) {
				count = 0;
			}
		}
	}

	public void changeExpObsLoc() {
		if (prob < 2) {
			if (count < 5) {
				count += 1;
				setPositionX(getPositionX() + 1);
				setPositionY(getPositionY() + 1);
			} else if (count >= 5 && count < 10) {
				count += 1;
				setPositionX(getPositionX() + 1);
				setPositionY(getPositionY() - 1);
			} else if (count >= 10 && count < 15) {
				count += 1;
				setPositionX(getPositionX() - 1);
				setPositionY(getPositionY() - 1);
			} else if (count >= 15 && count < 20) {
				count += 1;
				setPositionX(getPositionX() - 1);
				setPositionY(getPositionY() + 1);

			} else if (count >= 20) {
				count = 0;
			}
		}
	}
	public boolean isFreeze() {
		return isFreeze;
	}

	public void setFreeze(boolean freeze) {
		isFreeze = freeze;
	}

}
