package Domain.Abilities;

import java.util.Timer;
import java.util.TimerTask;

import Domain.GamePlay.Ball;

public class UnstoppableAbility {

	
	static int a = 30;
	private boolean isActivated = false;
	
	public void unstoppableSphere() {
			
			Timer myTimer = new Timer();
			setActivated(true);
			TimerTask task = new TimerTask( ) {
			
			
				@Override
				public void run() {
					a--;
					//System.out.println(a);
					setActivated(true);
					
					if(!Ball.getInstance().getIsStarted()) a = 0;
					
					if(a<=0) {
						myTimer.cancel();
						a = 30;		
						setActivated(false);
					}
				}
				
			};
			 myTimer.schedule(task, 0, 1000);
			 
			 
	}
	
	public  boolean isActivated() {
		return isActivated;
	}

	public  void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}
}
