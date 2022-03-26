package Domain.Ymir;

import Domain.GamePlay.Game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class InfiniteVoidAbility {

	Random random = new Random();
	private int randomObsIndex;
	int a = 15;
	ArrayList<Integer> arr = new ArrayList<Integer>();

	public void chooseRandomObstacle() {

		if (Game.getInstance().getSimpleObsArray().size() <= 8) {
			for (int i = 0; i < Game.getInstance().getSimpleObsArray().size(); i++) {
				Game.getInstance().getSimpleObstacleArray().get(i).setFreeze(true);
			}
		} else {
			// ThreadLocalRandom.current().ints(0,
			// Game.getInstance().getSimpleObsArray().size()).distinct().limit(8).forEach(Game.getInstance().getSimpleObstacleArray().get(randomObsIndex)).setFreeze(true);
			randomObsIndex = random.nextInt(Game.getInstance().getSimpleObsArray().size());
			arr.add(randomObsIndex);
			Game.getInstance().getSimpleObstacleArray().get(randomObsIndex).setFreeze(true);
			//System.out.println(Game.getInstance().getSimpleObstacleArray().get(randomObsIndex).getPositionX()
					//+ " index:" + randomObsIndex);
			for (int j = 0; j < 7; j++) {

				while (arr.contains(randomObsIndex)) {
					randomObsIndex = random.nextInt(Game.getInstance().getSimpleObsArray().size());
				}
				arr.add(randomObsIndex);
				Game.getInstance().getSimpleObstacleArray().get(randomObsIndex).setFreeze(true);
				//System.out.println(Game.getInstance().getSimpleObstacleArray().get(randomObsIndex).getPositionX()
						//+ " index:" + randomObsIndex);
				if (j == 6) {
					//System.out.println(arr);
					
					while (!arr.isEmpty()) {
						//System.out.println("removed: " + arr.get(0));
						arr.remove(0);
					}

				}
			}
		}
	}

	public void infiniteVoid() {

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
					chooseRandomObstacle();
				if (a <= 0) {
					a = 15;
					myTimer.cancel();
					for (int i = 0; i < Game.getInstance().getSimpleObsArray().size(); i++) {
						Game.getInstance().getSimpleObsArray().get(i).setFreeze(false);
					}
				}

			}

		};

		myTimer.schedule(task, 0, 1000);
	}

}
