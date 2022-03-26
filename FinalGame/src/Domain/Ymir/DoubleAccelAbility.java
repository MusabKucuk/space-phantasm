package Domain.Ymir;

import Domain.GamePlay.Ball;
import Domain.GamePlay.Game;

import java.util.Timer;
import java.util.TimerTask;

public class DoubleAccelAbility {

	Ball ball = Game.getInstance().getBall();
	int a = 15;

	public void halfBallVel() {
		if (ball.getVelocityX() == 0) {
			ball.setVelocityY(ball.getVelocityY() / (2));
		} else {
			ball.setVelocityX(ball.getVelocityX() / 2);
			ball.setVelocityY(ball.getVelocityY() / 2);
		}
	}

	public void doubleBallVel() {
		if (ball.getVelocityX() == 0) {
			ball.setVelocityY(ball.getVelocityY() * (2));
		} else {
		ball.setVelocityX(ball.getVelocityX() * 2);
		ball.setVelocityY(ball.getVelocityY() * 2);
		}
	}

	public void accelAbility() {
		Timer myTimer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				a--;
				
				if(!Game.getInstance().getIsRunning()) {
					a++;
				}
				
				//System.out.println(a);
				
				if (a == 14)
					halfBallVel();
				if (!ball.getIsStarted()) {
					a = 0;
				}
				if (a <= 0) {
					a = 15;
					myTimer.cancel();
					doubleBallVel();
					//System.out.println("**************************************************************************");
				}

			}

		};

		myTimer.schedule(task, 0, 1000);
	}
}
