package Domain.GamePlay;

import Domain.Abilities.*;
import Domain.Obstacles.SuperObstacle;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Collision {
	private boolean isBallIntersectWithSimpleObs = false;
	public boolean isBallCollidePaddle = false;
	private static Collision instance;
	private double angle;
	double tempBallVx;
	double tempBallVy;
	public int preventSecondCollide = 0;
	public int counter = 0;
	Random random = new Random();

	public static Collision getInstance() {
		if (instance == null)
			instance = new Collision();
		return instance;
	}

	public void isCollide() {
		// REQUIRES: obstacle type recognition
		// EFFECTS: call lowerHp function according to obstacle type
		counter++;

		double xBall;
		double yBall;
		double xObs;
		double yObs;
		double obsWidth;
		double obsHeigth;
		double ballWidth;
		double ballHeigth;
		for (int i = 0; i < Game.getInstance().getSimpleObsArray().size(); ++i) {
			if (getRectangle(Game.getInstance().getBall())
					.intersects(getRectangle(Game.getInstance().getSimpleObsArray().get(i))) && counter == 1

			) {
				isBallIntersectWithSimpleObs = true;

				if (!Game.getInstance().simpleObsArray.get(i).getExplode()) {
					if (Game.getInstance().getUnstoppableAbility().isActivated() == false) {

						xBall = Game.getInstance().getBall().getPositionX();
						yBall = Game.getInstance().getBall().getPositionY();
						xObs = getRectangle(Game.getInstance().getSimpleObsArray().get(i)).getX();
						yObs = getRectangle(Game.getInstance().getSimpleObsArray().get(i)).getY();
						obsWidth = getRectangle(Game.getInstance().getSimpleObsArray().get(i)).getWidth();
						obsHeigth = getRectangle(Game.getInstance().getSimpleObsArray().get(i)).getHeight();
						ballWidth = Game.getInstance().getBall().getWidth();
						ballHeigth = Game.getInstance().getBall().getHeight();
						// System.out.println(yBall +" - " + (yObs+obsHeigth));
						if (xBall > (xObs - (ballWidth / 2)) && xBall < (xObs + obsWidth + (ballWidth / 2))
								&& yBall >= yObs + obsHeigth) {
							Game.getInstance().getBall().setVelocityY(Game.getInstance().getBall().getVelocityY() * -1);
						} else if (xBall > (xObs - (ballWidth / 2)) && xBall < (xObs + obsWidth + (ballWidth / 2))
								&& yBall >= yObs - ballHeigth) {
							Game.getInstance().getBall().setVelocityY(Game.getInstance().getBall().getVelocityY() * -1);
						} else if (xBall >= (xObs - ballWidth) && yBall > yObs - (ballHeigth / 2)) {
							Game.getInstance().getBall().setVelocityX(Game.getInstance().getBall().getVelocityX() * -1);
						} else if (xBall >= (xObs + obsWidth) && yBall > yObs - (ballHeigth / 2)) {
							Game.getInstance().getBall().setVelocityX(Game.getInstance().getBall().getVelocityX() * -1);
						} else {
							Line2D line1 = new Line2D.Double(
									Game.getInstance().getBall().getPositionX()
											+ Game.getInstance().getBall().getWidth() / 2,
									Game.getInstance().getBall().getPositionY()
											+ Game.getInstance().getBall().getHeight() / 2,
									Game.getInstance().getSimpleObsArray().get(i).getPositionX()
											+ Game.getInstance().getSimpleObsArray().get(i).getWidth() / 2,
									Game.getInstance().getSimpleObsArray().get(i).getPositionY()
											+ Game.getInstance().getSimpleObsArray().get(i).getHeight() / 2);
							Line2D line2 = new Line2D.Double(0, 0, Game.getInstance().getBall().getVelocityX(),
									Game.getInstance().getBall().getVelocityY());
							angle = Math.toDegrees(angleBetween2Lines(line1, line2)) - 180;
							Game.getInstance().getBall().setVelocityX((6 * Math.cos(Math.toRadians(90 - angle))));
							Game.getInstance().getBall().setVelocityY((6 * Math.cos(Math.toRadians(angle))));
						}

					}
				}

				isBallIntersectWithSimpleObs = false;

				int type = Game.getInstance().getSimpleObsArray().get(i).getType();

				if (!Game.getInstance().getSimpleObstacleArray().get(i).isFreeze()
						|| Game.getInstance().getUnstoppableAbility().isActivated()) {

					if (type == 1) {

						if (Game.getInstance().getSimpleObsArray().get(i).getHP() > 1
								&& Game.getInstance().getUnstoppableAbility().isActivated() == false) {

							Game.getInstance().getSimpleObsArray().get(i).lowerHP();

						} else {

							Game.getInstance().simpleObsArray.remove(i);
							Game.getInstance().increaseScore(Game.getInstance().getTime());

						}
					} else if (type == 0) {

						Game.getInstance().simpleObsArray.get(i).lowerHP();
						// System.out.println(Game.getInstance().simpleObsArray.get(i).getHP());
						Game.getInstance().simpleObsArray.remove(i);
						Game.getInstance().increaseScore(Game.getInstance().getTime());

					} else if (type == 2 && !Game.getInstance().simpleObsArray.get(i).getExplode()) {

						Game.getInstance().simpleObsArray.get(i).lowerHP();
						// System.out.println(Game.getInstance().simpleObsArray.get(i).getHP());
						Game.getInstance().simpleObsArray.get(i).MoveEplosive();
						Game.getInstance().simpleObsArray.get(i).setExplode(true);
						Game.getInstance().increaseScore(Game.getInstance().getTime());

					} else if (type == 3 && !Game.getInstance().simpleObsArray.get(i).getExplode()) {

						Game.getInstance().simpleObsArray.get(i).MoveEplosive();
						Game.getInstance().simpleObsArray.get(i).setExplode(true);
						Game.getInstance().simpleObsArray.get(i).lowerHP();
						// System.out.println(Game.getInstance().simpleObsArray.get(i).getHP());
						Game.getInstance().increaseScore(Game.getInstance().getTime());
					}

					else if (type == -1) {

						Game.getInstance().simpleObsArray.remove(i);
					}
				}
			}

		}
	}

	public void expCollidePaddle() {

		for (int i = 0; i < Game.getInstance().getSimpleObsArray().size(); ++i) {

			int type = Game.getInstance().getSimpleObsArray().get(i).getType();

			if (getRectangle(Game.getInstance().getPaddle())
					.intersects(getRectangle(Game.getInstance().getSimpleObsArray().get(i)))
					&& Game.getInstance().getSimpleObsArray().get(i).getType() == 2) {
				Game.getInstance().getPaddle().lowerPaddleHP();
				Game.getInstance().simpleObsArray.remove(i);
				if (Game.getInstance().getPaddle().getPaddleHP() == 0) {
					System.exit(0); // GAME OVER CONDITION!
				}
			}

			if (type == 3) {

				if (getRectangle(Game.getInstance().getPaddle())
						.intersects(getRectangle(Game.getInstance().getSimpleObsArray().get(i)))
						&& Game.getInstance().getSimpleObsArray().get(i).hasTouchedPaddle() == false) {
					Game.getInstance().getSimpleObsArray().get(i).setHasTouchedPaddle(true);
					Game.getInstance().simpleObsArray.remove(i);

					int randomAbility = random.nextInt(4);

					System.out.println("Ability => " + randomAbility);

					switch (randomAbility) {
					case 0:
						Game.getInstance().getcGability().addHpToPaddle();
						break;

					case 1:
						if (Game.getInstance().getPaddleExpansionAbility().isActivated() == false)
							Game.getInstance().getInventory().add(0);
						break;

					case 2:
						if (Game.getInstance().getMagicalHex().isActivated() == false)
							Game.getInstance().getInventory().add(1);
						break;

					case 3:
						if (Game.getInstance().getUnstoppableAbility().isActivated() == false)
							Game.getInstance().getUnstoppableAbility().unstoppableSphere();
						break;

					default:
						break;
					}

				}
			}

		}

	}

	public void collideBullet() {

		for (int j = 0; j < Game.getInstance().getMagicalHex().getFiredBullets().size(); ++j) {

			for (int i = 0; i < Game.getInstance().getSimpleObsArray().size(); ++i) {

				if (getRectangle(Game.getInstance().getSimpleObsArray().get(i))
						.intersects(getRectangleBullet1(Game.getInstance().getMagicalHex().getFiredBullets().get(j)))
						|| getRectangle(Game.getInstance().getSimpleObsArray().get(i))
								.intersects(getRectangleBullet2(Game.getInstance().getMagicalHex().getFiredBullets().get(j)))) {

					if (!Game.getInstance().simpleObsArray.get(i).getExplode())
						Game.getInstance().getMagicalHex().getFiredBullets().remove(j);

					int type = Game.getInstance().getSimpleObsArray().get(i).getType();
					if (!Game.getInstance().getSimpleObstacleArray().get(i).isFreeze()
							|| Game.getInstance().getUnstoppableAbility().isActivated()) {

						if (type == 1) {
							if (Game.getInstance().getSimpleObsArray().get(i).getHP() > 1
									&& Game.getInstance().getUnstoppableAbility().isActivated() == false) {
								Game.getInstance().getSimpleObsArray().get(i).lowerHP();
							} else {
								Game.getInstance().simpleObsArray.remove(i);
								Game.getInstance().increaseScore(Game.getInstance().getTime());
							}
						} else if (type == 0) {
							Game.getInstance().simpleObsArray.get(i).lowerHP();
							// System.out.println(Game.getInstance().simpleObsArray.get(i).getHP());
							Game.getInstance().simpleObsArray.remove(i);
							Game.getInstance().increaseScore(Game.getInstance().getTime());
						} else if (type == 2 && !Game.getInstance().simpleObsArray.get(i).getExplode()) {
							Game.getInstance().simpleObsArray.get(i).lowerHP();
							// System.out.println(Game.getInstance().simpleObsArray.get(i).getHP());
							Game.getInstance().simpleObsArray.get(i).MoveEplosive();
							Game.getInstance().simpleObsArray.get(i).setExplode(true);

							Game.getInstance().increaseScore(Game.getInstance().getTime());
						} else if (type == 3 && !Game.getInstance().simpleObsArray.get(i).getExplode()) {
							Game.getInstance().simpleObsArray.get(i).MoveEplosive();
							Game.getInstance().simpleObsArray.get(i).setExplode(true);
							Game.getInstance().simpleObsArray.get(i).lowerHP();
							// System.out.println(Game.getInstance().simpleObsArray.get(i).getHP());
							Game.getInstance().increaseScore(Game.getInstance().getTime());
						} else if (type == -1) {

							Game.getInstance().simpleObsArray.remove(i);
						}
					}
				}
			}
		}
	}

	public Rectangle2D getRectangleBullet1(Bullet bullet) {
		Rectangle2D e = new Rectangle2D() {
			@Override
			public Rectangle2D getBounds2D() {
				return null;
			}

			@Override
			public double getX() {
				return bullet.getBullet1PosX();
			}

			@Override
			public double getY() {
				return bullet.getBullet1PosY();
			}

			@Override
			public double getWidth() {
				return bullet.getBullet1Width();
			}

			@Override
			public double getHeight() {
				return bullet.getBullet1Height();
			}

			@Override
			public boolean isEmpty() {
				return false;
			}

			@Override
			public void setFrame(double x, double y, double w, double h) {

			}

			@Override
			public Rectangle2D createIntersection(Rectangle2D r) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Rectangle2D createUnion(Rectangle2D r) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int outcode(double x, double y) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public void setRect(double x, double y, double w, double h) {
				// TODO Auto-generated method stub

			}
		};

		e.setFrame(bullet.getBullet1PosX(), bullet.getBullet1PosY(), bullet.getBullet1Width(),
				bullet.getBullet1Height());
		return e;
	}

	public Rectangle2D getRectangleBullet2(Bullet bullet) {
		Rectangle2D e = new Rectangle2D() {
			@Override
			public Rectangle2D getBounds2D() {
				return null;
			}

			@Override
			public double getX() {
				return bullet.getBullet2PosX();
			}

			@Override
			public double getY() {
				return bullet.getBullet2PosY();
			}

			@Override
			public double getWidth() {
				return bullet.getBullet2Width();
			}

			@Override
			public double getHeight() {
				return bullet.getBullet2Height();
			}

			@Override
			public boolean isEmpty() {
				return false;
			}

			@Override
			public void setFrame(double x, double y, double w, double h) {

			}

			@Override
			public Rectangle2D createIntersection(Rectangle2D r) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Rectangle2D createUnion(Rectangle2D r) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int outcode(double x, double y) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public void setRect(double x, double y, double w, double h) {
				// TODO Auto-generated method stub

			}
		};

		e.setFrame(bullet.getBullet2PosX(), bullet.getBullet2PosY(), bullet.getBullet2Width(),
				bullet.getBullet2Height());
		return e;
	}

	public Ellipse2D getRectangle(Ball ball) {
		Ellipse2D e = new Ellipse2D() {
			@Override
			public Rectangle2D getBounds2D() {
				return null;
			}

			@Override
			public double getX() {
				return ball.getPositionX();
			}

			@Override
			public double getY() {
				return ball.getPositionY();
			}

			@Override
			public double getWidth() {
				return ball.getWidth();
			}

			@Override
			public double getHeight() {
				return ball.getHeight();
			}

			@Override
			public boolean isEmpty() {
				return false;
			}

			@Override
			public void setFrame(double x, double y, double w, double h) {

			}
		};

		e.setFrame(ball.getPositionX(), ball.getPositionY(), ball.getWidth(), ball.getHeight());
		return e;
	}

	public void IsCollidePaddle() {
		// REQUIRES: Counter, getRectangle helper functions, isBallCollide(), game
		// instance (ball instance, paddle instance), preventScondCollide
		// MODIFIES: isBallCollidePaddle, preventSecondCollid, used getter-setter
		// methods have modified according to collision style
		// EFFECTS: Change in Game.getInstance().getBall().getVelocityX and
		// .getVelocityY. If if ball and paddle moves in same direction,
		// ball.getTotalVelocity increse by 3 or -3 if negetive direction. Also
		// ball.getVelocityY negates when a collision happens.

		counter++;
		if (getRectangle(Game.getInstance().getBall()).intersects(getRectangle(Game.getInstance().getPaddle()))
				&& counter == 1) {
			isBallCollidePaddle = true;

			if (Game.getInstance().getBall().getVelocityX() / Game.getInstance().getPaddle().getVelocity() > 0
					&& Game.getInstance().getPaddle().getVelocity() != 0 && preventSecondCollide != 1) {
				preventSecondCollide = 1;

				if (Game.getInstance().getBall().getVelocityX() > 0) {

					Game.getInstance().getBall().setVelocityX(Game.getInstance().getBall().getVelocityX() + 0.5);
					Game.getInstance().getBall().setVelocityY(-Game.getInstance().getBall().getVelocityY());
				}

				else if (Game.getInstance().getBall().getVelocityX() < 0) {

					Game.getInstance().getBall().setVelocityX(Game.getInstance().getBall().getVelocityX() - 0.5);
					Game.getInstance().getBall().setVelocityY(-Game.getInstance().getBall().getVelocityY());

				}

			} else if (Game.getInstance().getBall().getVelocityX() == 0
					&& Game.getInstance().getPaddle().isPaddleGoesRigth() == false
					&& Game.getInstance().getPaddle().getVelocity() != 0) {

				Game.getInstance().getBall()
						.setVelocityX(-Game.getInstance().getBall().getTotalVelocity() / Math.sqrt(2));
				Game.getInstance().getBall()
						.setVelocityY(Game.getInstance().getBall().getTotalVelocity() / Math.sqrt(2));
				preventSecondCollide = 1;

			} else if (Game.getInstance().getBall().getVelocityX() == 0
					&& Game.getInstance().getPaddle().isPaddleGoesRigth() == true
					&& Game.getInstance().getPaddle().getVelocity() != 0) {

				Game.getInstance().getBall()
						.setVelocityX(Game.getInstance().getBall().getTotalVelocity() / Math.sqrt(2));
				Game.getInstance().getBall()
						.setVelocityY(Game.getInstance().getBall().getTotalVelocity() / Math.sqrt(2));
				preventSecondCollide = 1;

			} else if (Game.getInstance().getBall().getVelocityX() / Game.getInstance().getPaddle().getVelocity() < 0
					&& Game.getInstance().getPaddle().getVelocity() != 0 && preventSecondCollide != 1) {

				if (Game.getInstance().getBall().getVelocityX() < 0) {

					tempBallVx = Game.getInstance().getBall().getVelocityX();
					tempBallVy = Game.getInstance().getBall().getVelocityY();

					Game.getInstance().getBall().setVelocityX(-tempBallVy - 0.5);
					Game.getInstance().getBall().setVelocityY(-tempBallVx);
					preventSecondCollide = 1;
				}

				if (Game.getInstance().getBall().getVelocityX() > 0) {

					tempBallVx = Game.getInstance().getBall().getVelocityX();
					tempBallVy = Game.getInstance().getBall().getVelocityY();

					Game.getInstance().getBall().setVelocityX(-tempBallVy - 0.5);
					Game.getInstance().getBall().setVelocityY(tempBallVx);
					preventSecondCollide = 1;
				}

			} else if (Game.getInstance().getPaddle().getVelocity() == 0
					&& Game.getInstance().getBall().getVelocityX() == 0) {

				Game.getInstance().getBall().setVelocityY(-1 * Game.getInstance().getBall().getVelocityY());
				Game.getInstance().getBall().setPositionY(
						Game.getInstance().getBall().getPositionY() - 2 * Game.getInstance().getBall().getVelocityY());

			} else if (Game.getInstance().getPaddle().getVelocity() == 0
					&& Game.getInstance().getBall().getVelocityX() != 0) {

				Game.getInstance().getBall().setVelocityY(-1 * Game.getInstance().getBall().getVelocityY());
				Game.getInstance().getBall().setPositionY(
						Game.getInstance().getBall().getPositionY() - 2 * Game.getInstance().getBall().getVelocityY());

			}
		} else if (preventSecondCollide == 1 && !getRectangle(Game.getInstance().getBall())
				.intersects(getRectangle(Game.getInstance().getPaddle()))) {
			preventSecondCollide = 0;
		}
	}

	public Rectangle getRectangle(SuperObstacle superObstacle) {
		Rectangle r = new Rectangle();
		r.setBounds(superObstacle.getPositionX(), superObstacle.getPositionY(), superObstacle.getWidth(),
				superObstacle.getHeight());
		return r;
	}

	public boolean isBallIntersectWithSimpleObs() {
		return isBallIntersectWithSimpleObs;
	}

	public void setBallIntersectWithSimpleObs(boolean ballIntersectWithSimpleObs) {
		isBallIntersectWithSimpleObs = ballIntersectWithSimpleObs;

	}

	public Rectangle getRectangle(Paddle paddle) {
		Rectangle r = new Rectangle();
		r.setBounds(paddle.getPositionX(), paddle.getPositionY(), paddle.getWidth(), paddle.getHeight());
		return r;
	}

	public double angleBetween2Lines(Line2D line1, Line2D line2) {
		// REQUIRES: length of the lines > 0
		// EFFECTS: return angle between two lines
		double angle1 = Math.atan2(line1.getY1() - line1.getY2(), line1.getX1() - line1.getX2());
		double angle2 = Math.atan2(line2.getY1() - line2.getY2(), line2.getX1() - line2.getX2());
		// return Math.abs(angle1 - angle2);
		return (angle1 - angle2);
	}
}
