package Domain.Abilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import Domain.GamePlay.Ball;


public class MagicalHex {

	private boolean pressed_H = false;
	static double a = 120;
	private boolean isActivated = false;
	private static MagicalHex instance;
	private double cannon1Width;
	private double cannon1Height;
	private double cannon2Width;
	private double cannon2Height;
	private double cannon1PosX;
	private double cannon1PosY;
	private double cannon2PosX;
	private double cannon2PosY;
	
	private List<Bullet> firedBullets = new ArrayList<Bullet>();
	
	public static MagicalHex getInstance() {
		if (instance == null)
			instance = new MagicalHex();
		return instance;
	}

	public MagicalHex() {
		cannon1Height = 10;
		cannon1Width = 10;
		cannon2Height = 10;
		cannon2Width = 10;
	}

	public void magicalHex() {
		if (isPressed_H() == true && isActivated == false) {

			Timer myTimer = new Timer();

			setActivated(true);
			TimerTask task = new TimerTask() {

				
				@Override
				public void run() {
					a--;
					
                    Bullet bullet = new Bullet();
                    firedBullets.add(bullet);
                    
                    if(!Ball.getInstance().getIsStarted()) a = 0;
                    
					if (a <= 0) {
						myTimer.cancel();
                     
						a = 120;
						setActivated(false);
						setPressed_H(false);
						
					}
				}

			};
			myTimer.schedule(task, 0, 250);

		}
	}

	
	
	public List<Bullet> getFiredBullets() {
		return firedBullets;
	}

	public boolean isPressed_H() {
		return pressed_H;
	}

	public void setPressed_H(boolean pressed_T) {
		this.pressed_H = pressed_T;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	public double getCannon1Width() {
		return cannon1Width;
	}

	public void setCannon1Width(double cannon1Width) {
		this.cannon1Width = cannon1Width;
	}

	public double getCannon1Height() {
		return cannon1Height;
	}

	public void setCannon1Height(double cannon1Height) {
		this.cannon1Height = cannon1Height;
	}

	public double getCannon2Width() {
		return cannon2Width;
	}

	public void setCannon2Width(double cannon2Width) {
		this.cannon2Width = cannon2Width;
	}

	public double getCannon2Height() {
		return cannon2Height;
	}

	public void setCannon2Height(double cannon2Height) {
		this.cannon2Height = cannon2Height;
	}

	public double getCannon1PosX() {
		return cannon1PosX;
	}

	public void setCannon1PosX(double cannon1PosX) {
		this.cannon1PosX = cannon1PosX;
	}

	public double getCannon1PosY() {
		return cannon1PosY;
	}

	public void setCannon1PosY(double cannon1PosY) {
		this.cannon1PosY = cannon1PosY;
	}

	public double getCannon2PosX() {
		return cannon2PosX;
	}

	public void setCannon2PosX(double cannon2PosX) {
		this.cannon2PosX = cannon2PosX;
	}

	public double getCannon2PosY() {
		return cannon2PosY;
	}

	public void setCannon2PosY(double cannon2PosY) {
		this.cannon2PosY = cannon2PosY;
	}

}
