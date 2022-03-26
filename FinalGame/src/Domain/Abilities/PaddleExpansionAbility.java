package Domain.Abilities;

import java.util.Timer;
import java.util.TimerTask;

import Domain.GamePlay.Ball;
import Domain.GamePlay.Game;

public class PaddleExpansionAbility {
	private  boolean pressed_T = false;
	static int a = 30;
	private  boolean isActivated = false;
	
	
	public  void paddleExpansion() {
		if(isPressed_T() == true && isActivated == false) {
			
			
			Timer myTimer = new Timer();
			Game.getInstance().getPaddle().setWidth(Game.getInstance().getPaddle().getWidth() * 2);
			setActivated(true);
			TimerTask task = new TimerTask( ) {
			
			
				@Override
				public void run() {
					a--;
					
					if(!Ball.getInstance().getIsStarted()) a = 0;
					
					if(a<=0) {
						myTimer.cancel();
						Game.getInstance().getPaddle().setWidth(Game.getInstance().getPaddle().getWidth() / 2);
						a = 30;
						setActivated(false);
						setPressed_T(false);
					}
				}
				
			};
			 myTimer.schedule(task, 0, 1000);
			 
			 
		}
	}

	public  boolean isPressed_T() {
		return pressed_T;
	}

	public  void setPressed_T(boolean pressed_T) {
		this.pressed_T = pressed_T;
	}
	public  boolean isActivated() {
		return isActivated;
	}

	public  void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}
}
