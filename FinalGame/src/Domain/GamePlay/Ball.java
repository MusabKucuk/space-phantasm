package Domain.GamePlay;

import java.awt.*;

public class Ball {

	private int width;
	private int height;
	private double positionX;
	private double positionY;
	private double velocityY;
	private double velocityX;
	@SuppressWarnings("unused")
	private double totalVelocity;
	private static Ball instance;
	private boolean isStarted;
	private Rectangle ballBounds;

	public Ball() {
		this.isStarted = false;
		this.width = 16;
		this.height = 16;
		this.velocityY = 6;
		this.velocityX = 0;
		this.totalVelocity = (Math.pow(Math.pow(getVelocityX(), 2) + Math.pow(getVelocityY(), 2), 0.5));
	}

	public static Ball getInstance() {
		if (instance == null)
			instance = new Ball();
		return instance;
	}

	public boolean getIsStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	public double getTotalVelocity() {
		return Math.pow(Math.pow(getVelocityX(), 2) + Math.pow(getVelocityY(), 2), 0.5);
	}

	public void setTotalVelocity(double d) {
		this.totalVelocity = d;
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

	public double getPositionX() {
		return positionX;
	}

	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	public double getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(double velocitY) {
		this.velocityY = velocitY;
	}

	public double getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	public Rectangle getBallBounds() {
		return ballBounds;
	}

	public void setBallBounds(Rectangle ballBounds) {
		this.ballBounds = ballBounds;
	}
}
