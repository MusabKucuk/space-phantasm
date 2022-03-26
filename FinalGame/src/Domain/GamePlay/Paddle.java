package Domain.GamePlay;

public class Paddle {
	private int width;
	private int height;
	private int positionX;
	private int positionY;
	private int velocity;
	private double rotationVelocity;
	private String rotationDirection;
	private double angle;
	private String direction;
	private boolean paddleGoesRigth = false;
	private int paddleHP;
	private static Paddle instance;
	private double rotatePosX;
	private double rotatePosY;

	public boolean isPaddleGoesRigth() {
		return paddleGoesRigth;
	}

	public Paddle() {
		this.width = 120;
		this.height = 20;
		this.paddleHP = 3;
	}

	public static Paddle getInstance() {
		if (instance == null)
			instance = new Paddle();
		return instance;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
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

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public String getRotationDirection() {
		return rotationDirection;
	}

	public void setRotationDirection(String rotationDirection) {
		this.rotationDirection = rotationDirection;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getRotationVelocity() {
		return rotationVelocity;
	}

	public void setRotationVelocity(double rotationVelocity) {
		this.rotationVelocity = rotationVelocity;
	}

	public double getRotatePosX() {
		return rotatePosX;
	}

	public void setRotatePosX(double rotatePosX) {
		this.rotatePosX = rotatePosX;
	}

	public double getRotatePosY() {
		return rotatePosY;
	}

	public void setRotatePosY(double rotatePosY) {
		this.rotatePosY = rotatePosY;
	}

	public void rotatePaddle(String direction) {
		if (direction.equals("right")) {
			setAngle(getAngle() + getRotationVelocity());
		} else if (direction.equals("left")) {
			setAngle(getAngle() + getRotationVelocity());
		}
	}

	public void changePaddleLocation(String left_or_right) {

		if (getPositionX() < 0) {
			setVelocity(0);
			setPositionX(0);
		}

		else if (getPositionX() + getWidth()> 1200) {
			setVelocity(0);
			setPositionX(1200 - getWidth());
		}

		if (left_or_right.equals("left")) {
			paddleGoesRigth = false;
			setPositionX(getPositionX() + getVelocity());
			setRotatePosX(getRotatePosX() + getVelocity());
		}

		else if (left_or_right.equals("right")) {
			paddleGoesRigth = true;
			setPositionX(getPositionX() + getVelocity());
			setRotatePosX(getRotatePosX() + getVelocity());
		}

	}

	public int getPaddleHP() {
		// EFFECTS: return paddleHP
		return paddleHP;
	}
	
	public void setPaddleHP(int hp) {
		// REQUIRES: paddleHP >= 0
		// MODIFIES: paddleHP
        paddleHP = hp; 
    }

	public String getPaddleHPString() {
		String s = Integer.toString(getPaddleHP());
		return s;
	}

	public void lowerPaddleHP() {
		//REQUIRES: paddleHP > 0
		// EFFECTS: decrease paddleHP by 1
		this.paddleHP = paddleHP - 1;
	}

}