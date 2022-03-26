package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Domain.GamePlay.Game;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements ActionListener, KeyListener {

	int counter = 0;
	int time = 0;
	int positionX;
	int positionY;
	boolean isRotate = false;
	double increase = 0.3;
	int win = 0;
	JLabel pic = new JLabel(new ImageIcon("Images/ex1.png"));
	JLabel prize = new JLabel(new ImageIcon("Images/prize.png"));
	File bombFile = new File("Images/Bomb.png");
	File simpFile = new File("Images/Simple.png");
	BufferedImage bombImg;
	BufferedImage simpImg;

	Game game;
	Timer tm = new Timer(10, this);

	public GamePanel() {
		tm.start();
		addKeyListener(this);
		setFocusable(true);
		game = Game.getInstance();
		game.getPaddle().setPositionX(540);
		game.getBall().setPositionY(715);
		game.getPaddle().setRotatePosX(game.getPaddle().getPositionX());

		try {
			bombImg = ImageIO.read(new File("Images/Bomb.png"));
			simpImg = ImageIO.read(new File("Images/Simple.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {

		if (!game.getSimpleObsArray().isEmpty()) {

			super.paint(g);
			drawPaddle(g);
			drawBall(g);
			drawSimpObstacles(g);
			drawHealth(g);
			drawScore(g);
			drawInventory(g);
			drawBullets(g);
			drawUsingAbility(g);

			if (game.getMagicalHex().isActivated() == true)
				drawCannons(g);

			time++;
			game.setTime(time / 100);

		} else {
			win++;
			if (win == 1)
				g.dispose();
			win = 2;
			super.paint(g);
			drawPrize(g);
			drawScore(g);
		}
	}

	public void drawPrize(Graphics g) {
		String str = "YOU WON!!! IF YOU WANT THE PRIZE, PRESS PAUSE";
		g.setColor(Color.WHITE);
		g.drawString(str, 875, 30);
		remove(pic);
		setBackground(Color.GRAY);
		add(prize);
	}

	public void drawPaddle(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		game.getPaddle().setPositionY(getHeight() - game.getPaddle().getHeight() - 5);
		game.getPaddle().setRotatePosY(game.getPaddle().getPositionY());
		AffineTransform reset = g2d.getTransform();
		Rectangle2D.Double r = new Rectangle2D.Double(game.getPaddle().getPositionX(), game.getPaddle().getPositionY(),
				game.getPaddle().getWidth(), game.getPaddle().getHeight());

		g2d.setColor(Color.red);
		g2d.rotate(Math.toRadians(game.getPaddle().getAngle()),
				game.getPaddle().getPositionX() + (double) (game.getPaddle().getWidth() / 2),
				game.getPaddle().getPositionY() + (double) (game.getPaddle().getHeight() / 2));
		g2d.fill(r);
		g2d.setTransform(reset);

		add(pic);
	}

	public void drawHealth(Graphics g) {
		g.setColor(Color.red);
		String hp = "HP: " + game.getPaddle().getPaddleHPString();
		g.drawString(hp, 1150, 30);
	}

	public void drawFirmObsHealth(Graphics g, int x, int y) {
		g.setColor(Color.red);
		String hp = "" + game.getSimpleObstacleArray().get(counter).getFirmHPString();
		g.drawString(hp, x + 8, y + 14);
	}

	public void drawBall(Graphics g) {

		if (!game.getBall().getIsStarted()) {
			game.getBall().setPositionX(
					game.getPaddle().getPositionX() + ((game.getPaddle().getWidth() - game.getBall().getWidth()) / 2));
		}

		g.setColor(Color.blue);
		g.fillOval((int) game.getBall().getPositionX(), (int) game.getBall().getPositionY(), game.getBall().getWidth(),
				game.getBall().getHeight());
		Rectangle r = g.getClipBounds();
		game.getBall().setBallBounds(r);
	}

	public void drawSimpObstacles(Graphics g) {

		for (counter = 0; counter < game.getSimpleObsArray().size(); counter++) {
			positionX = Game.getInstance().getSimpleObstacleArray().get(counter).getPositionX();
			positionY = Game.getInstance().getSimpleObstacleArray().get(counter).getPositionY();

			if (Game.getInstance().getSimpleObstacleArray().get(counter).getType() == 0) {
				if (game.getSimpleObstacleArray().get(counter).isFreeze()) {
					g.setColor(Color.CYAN);
					g.fillRect(positionX, positionY, game.getSimpleObsArray().get(counter).getWidth(),
							game.getSimpleObsArray().get(counter).getHeight());
				} else {
					// g.setColor(Color.yellow);
					Graphics2D g2d = (Graphics2D) g.create();
					g2d.drawImage(simpImg, positionX, positionY, this);

				}
				game.getSimpleObsArray().get(counter).changeObsLoc();
			}

			else if (Game.getInstance().getSimpleObstacleArray().get(counter).getType() == 1) {
				if (game.getSimpleObstacleArray().get(counter).isFreeze())
					g.setColor(Color.CYAN);
				else
					g.setColor(Color.green);
				g.fillRect(positionX, positionY, game.getSimpleObsArray().get(counter).getWidth(),
						game.getSimpleObsArray().get(counter).getHeight());

				drawFirmObsHealth(g, positionX, positionY);
				game.getSimpleObsArray().get(counter).changeObsLoc();

			} else if (Game.getInstance().getSimpleObstacleArray().get(counter).getType() == 2) {

				if (game.getSimpleObsArray().get(counter).getVelocity() == 0) {
					if (game.getSimpleObstacleArray().get(counter).isFreeze()) {
						g.setColor(Color.CYAN);
						g.fillOval(positionX, positionY, game.getSimpleObsArray().get(counter).getWidth(),
								game.getSimpleObsArray().get(counter).getHeight());
					} else {
						// g.setColor(Color.ORANGE);
						Graphics2D g2d = (Graphics2D) g.create();
						g2d.drawImage(bombImg, positionX, positionY, this);
					}

					game.getSimpleObsArray().get(counter).changeExpObsLoc();
				}

				else if (game.getSimpleObsArray().get(counter).getVelocity() < 0) {

					g.setColor(Color.red);
					g.fillOval(game.getSimpleObsArray().get(counter).getPositionX(),
							game.getSimpleObsArray().get(counter).getPositionY(),
							game.getSimpleObsArray().get(counter).getWidth(),
							game.getSimpleObsArray().get(counter).getHeight());
				}
			} else if (Game.getInstance().getSimpleObstacleArray().get(counter).getType() == 3) {

				if (game.getSimpleObsArray().get(counter).getVelocity() == 0) {
					if (game.getSimpleObstacleArray().get(counter).isFreeze())
						g.setColor(Color.CYAN);
					else
						g.setColor(Color.pink);
					g.fillRect(positionX, positionY, game.getSimpleObsArray().get(counter).getWidth(),
							game.getSimpleObsArray().get(counter).getHeight());
					game.getSimpleObsArray().get(counter).changeObsLoc();
				}

				else if (game.getSimpleObsArray().get(counter).getVelocity() < 0) {
					g.setColor(Color.orange);
					g.fillOval(game.getSimpleObsArray().get(counter).getPositionX(),
							game.getSimpleObsArray().get(counter).getPositionY(),
							game.getSimpleObsArray().get(counter).getWidth() - 4,
							game.getSimpleObsArray().get(counter).getHeight());
				}
			}

			else if (Game.getInstance().getSimpleObstacleArray().get(counter).getType() == -1) {

				if (game.getSimpleObsArray().get(counter).getVelocity() == 0) {
					if (game.getSimpleObstacleArray().get(counter).isFreeze())
						g.setColor(Color.CYAN);
					else
						g.setColor(Color.magenta);
					g.fillRect(positionX, positionY, game.getSimpleObsArray().get(counter).getWidth(),
							game.getSimpleObsArray().get(counter).getHeight());
					game.getSimpleObsArray().get(counter).changeObsLoc();
				}

			}

		}
	}

	public void drawScore(Graphics g) {
		int x = 30;
		int y = 30;
		g.setColor(Color.black);
		String xxx = "SCORE: " + Integer.toString(game.getScore());
		g.drawString(xxx, x, y);
	}

	public void drawInventory(Graphics g) {
		int x = 170;
		int y = 30;
		g.setColor(Color.black);

		ArrayList<String> inventory = new ArrayList<String>();

		inventory.add(0, "Ability Inventory: ");

		if (Game.getInstance().getInventory().contains(0) && !Game.getInstance().getInventory().contains(1)) {
			inventory.add(1, "Paddle Expansion");
		} else if (inventory.contains("Paddle Expansion")) {
			inventory.remove(1);
		}

		if (Game.getInstance().getInventory().contains(1) && !Game.getInstance().getInventory().contains(0)) {
			inventory.add(1, "Magical Hex");
		} else if (inventory.contains("Magical Hex")) {
			inventory.remove(1);
		}

		if (Game.getInstance().getInventory().contains(0) && Game.getInstance().getInventory().contains(1)) {
			inventory.add(1, "Paddle Expansion and Magical Hex");
		} else if (inventory.contains("Paddle Expansion and Magical Hex")) {
			inventory.remove(1);
		}

		String xxx = String.join("", inventory);

		g.drawString(xxx, x, y);
	}

	public void drawCannons(Graphics g) {

		game.getMagicalHex().setCannon1PosX(Game.getInstance().getPaddle().getPositionX() + 5);
		game.getMagicalHex().setCannon2PosX(
				Game.getInstance().getPaddle().getPositionX() + Game.getInstance().getPaddle().getWidth() - 15);

		g.setColor(Color.GRAY);
		g.fillRect((int) game.getMagicalHex().getCannon1PosX(), (int) game.getMagicalHex().getCannon1PosY(),
				(int) game.getMagicalHex().getCannon1Width(), (int) game.getMagicalHex().getCannon1Height());
		g.fillRect((int) game.getMagicalHex().getCannon2PosX(), (int) game.getMagicalHex().getCannon2PosY(),
				(int) game.getMagicalHex().getCannon2Width(), (int) game.getMagicalHex().getCannon2Height());

	}

	public void drawBullets(Graphics g) {

		for (int i = 0; i < game.getMagicalHex().getFiredBullets().size(); i++) {
			g.setColor(Color.BLACK);
			g.fillOval((int) game.getMagicalHex().getFiredBullets().get(i).getBullet1PosX(),
					(int) game.getMagicalHex().getFiredBullets().get(i).getBullet1PosY(),
					(int) game.getMagicalHex().getFiredBullets().get(i).getBullet1Width(),
					(int) game.getMagicalHex().getFiredBullets().get(i).getBullet1Height());
			g.fillOval((int) game.getMagicalHex().getFiredBullets().get(i).getBullet2PosX(),
					(int) game.getMagicalHex().getFiredBullets().get(i).getBullet2PosY(),
					(int) game.getMagicalHex().getFiredBullets().get(i).getBullet2Width(),
					(int) game.getMagicalHex().getFiredBullets().get(i).getBullet2Height());

			if (game.getMagicalHex().isActivated() == false) // g.dispose();
				game.getMagicalHex().getFiredBullets().remove(i);
		}

	}

	public void drawUsingAbility(Graphics g) {
		ArrayList<String> arr = new ArrayList<String>();

		arr.add(0, "Ability is running: ");

		if (game.getUnstoppableAbility().isActivated() && !game.getPaddleExpansionAbility().isActivated() && !game.getMagicalHex().isActivated()) {
			arr.add(1, "Unstopable Ability");
		} else if (arr.contains("Unstopable Ability")) {
			arr.remove(1);
		}

		if (game.getPaddleExpansionAbility().isActivated() && !game.getUnstoppableAbility().isActivated() && !game.getMagicalHex().isActivated()) {
			arr.add(1, "Paddle Expansion Ability");
		} else if (arr.contains("Paddle Expansion Ability")) {
			arr.remove(1);
		}

		if (game.getMagicalHex().isActivated() && !game.getPaddleExpansionAbility().isActivated() && !game.getUnstoppableAbility().isActivated()) {
			arr.add(1, "Magical Hex");
		} else if (arr.contains("Magical Hex")) {
			arr.remove(1);
		}
		if (game.getPaddleExpansionAbility().isActivated() && game.getMagicalHex().isActivated()) {
			arr.add(1, "M.Hex & P. Expansion A.");
		} else if (arr.contains("M.Hex & P. Expansion A.")) {
			arr.remove(1);
		}
		if (game.getUnstoppableAbility().isActivated() && game.getMagicalHex().isActivated()) {
			arr.add(1, "M.Hex & Unstopable A.");
		} else if (arr.contains("M.Hex & Unstopable A.")) {
			arr.remove(1);
		}
		if (game.getUnstoppableAbility().isActivated() && game.getPaddleExpansionAbility().isActivated()) {
			arr.add(1, "P. Expansion & Unstopable A.");
		} else if (arr.contains("P. Expansion & Unstopable A.")) {
			arr.remove(1);
		}
		if (game.getUnstoppableAbility().isActivated() && game.getPaddleExpansionAbility().isActivated() && game.getMagicalHex().isActivated()) {
			arr.add(1, "P. Expansion & Unstopable & M. Hex");
		} else if (arr.contains("P. Expansion & Unstopable & M. Hex")) {
			arr.remove(1);
		}

		String xxx = String.join("", arr);

		g.drawString(xxx, 850, 30);
	}

	public void actionPerformed(ActionEvent e) {

		if (game.getBall().getIsStarted()) {
			game.MoveBall();
			game.MoveObstacles();
		}

		if (game.getPaddle().getDirection() == "left") {
			game.MovePaddle("left");
		}

		if (game.getPaddle().getDirection() == "right") {
			game.MovePaddle("right");
		}

		if (game.getPaddle().getRotationDirection() == "left" && game.getPaddle().getAngle() >= -45) {
			game.RotatePaddle("left");
			game.getPaddle().setRotatePosX(game.getPaddle().getRotatePosX()
					+ (int) (Math.cos(game.getPaddle().getAngle()) * (game.getPaddle().getWidth() / 2)));
			game.getPaddle().setRotatePosY(game.getPaddle().getRotatePosY()
					+ (int) (Math.sin(-game.getPaddle().getAngle()) * (game.getPaddle().getWidth() / 2)));
			// isRotate = true;
			// increase += 0.3;
			game.getMagicalHex().setCannon1PosY(game.getMagicalHex().getCannon1PosY() + 0.52);
			game.getMagicalHex().setCannon2PosY(game.getMagicalHex().getCannon2PosY() - 0.52);

		}

		if (game.getPaddle().getRotationDirection() == "right" && game.getPaddle().getAngle() <= 45) {
			game.RotatePaddle("right");
			game.getPaddle().setRotatePosX(game.getPaddle().getRotatePosX()
					+ (int) (Math.cos(game.getPaddle().getAngle()) * (game.getPaddle().getWidth() / 2)));
			game.getPaddle().setRotatePosY(game.getPaddle().getRotatePosY()
					- (int) (Math.sin(game.getPaddle().getAngle()) * (game.getPaddle().getWidth() / 2)));
			// isRotate = true;
			// increase += 0.3;
			game.getMagicalHex().setCannon1PosY(game.getMagicalHex().getCannon1PosY() - 0.52);
			game.getMagicalHex().setCannon2PosY(game.getMagicalHex().getCannon2PosY() + 0.52);

		}

		if (game.getMagicalHex().isActivated() == true) {
			for (int i = 0; i < game.getMagicalHex().getFiredBullets().size(); i++) {
				game.getMagicalHex().getFiredBullets().get(i).fire();
				game.getCollision().collideBullet();
			}
		}

		// System.out.println("X: "+game.getPaddle().getRotatePosX());
		// System.out.println("Y: " + game.getPaddle().getRotatePosY());
        game.getCollision().expCollidePaddle();
		repaint();
	}

	public void keyPressed(KeyEvent e) {

		if (KeyEvent.VK_LEFT == e.getKeyCode()) {
			game.getPaddle().setVelocity(-12);
			game.getPaddle().setDirection("left");
		}

		if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
			game.getPaddle().setVelocity(12);
			game.getPaddle().setDirection("right");
		}

		if (KeyEvent.VK_A == e.getKeyCode()) {
			game.getPaddle().setRotationVelocity(-0.8);
			game.getPaddle().setRotationDirection("left");
		}

		if (KeyEvent.VK_D == e.getKeyCode()) {
			game.getPaddle().setRotationVelocity(0.8);
			game.getPaddle().setRotationDirection("right");
		}

		if (KeyEvent.VK_SPACE == e.getKeyCode()) {
			game.getBall().setStarted(true);
		}

		if (KeyEvent.VK_T == e.getKeyCode()) {
			// game.getPaddleExpansionAbility();
			game.getPaddleExpansionAbility().setPressed_T(true);
			if (Game.getInstance().getInventory().contains(0)) {
				game.getPaddleExpansionAbility().paddleExpansion();
				Game.getInstance().getInventory().removeAll(Arrays.asList(0));
			}
		}

		if (KeyEvent.VK_H == e.getKeyCode()) {
			// game.getPaddleExpansionAbility();
			game.getMagicalHex().setPressed_H(true);
			if (Game.getInstance().getInventory().contains(1)) {
				game.getMagicalHex().magicalHex();
				Game.getInstance().getInventory().removeAll(Arrays.asList(1));
				game.getMagicalHex().setCannon1PosY(Game.getInstance().getPaddle().getPositionY() - 10);
				game.getMagicalHex().setCannon2PosY(Game.getInstance().getPaddle().getPositionY() - 10);
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		game.MovePaddle("");
		game.getPaddle().setDirection("");
		game.getPaddle().setVelocity(0);
		game.getPaddle().setRotationDirection("");
		game.getPaddle().setRotationVelocity(0);
	}

	public void keyTyped(KeyEvent e) {
	}

	public void setTimer(Timer tm) {
		this.tm = tm;
	}

	public Timer getTimer() {
		return tm;
	}

	public void PauseGame() {
		tm.stop();

	}

	public void ResumeGame() {
		tm.start();
	}

}
