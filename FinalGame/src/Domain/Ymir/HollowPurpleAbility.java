package Domain.Ymir;

import Domain.GamePlay.Collision;
import Domain.GamePlay.Game;
import Domain.Obstacles.PurpleObstacle;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class HollowPurpleAbility {

	Collision collision = Collision.getInstance();
	ArrayList<PurpleObstacle> purpleObstacleArray = new ArrayList<PurpleObstacle>();
	Random rand = new Random();
	int a = 15;

	public HollowPurpleAbility() {
	}

	public ArrayList<PurpleObstacle> createPurpleObstacle() {

		int maxRangeX = 1100;
		int minRangeX = 50;
		int maxRangeY = 400;
		int minRangeY = 50;
		int randomXRange;
		int randomYRange;
		int counter = 0;

		randomXRange = (rand.nextInt(maxRangeX - minRangeX) + minRangeX);
		randomYRange = (rand.nextInt(maxRangeY - minRangeY) + minRangeY);
		purpleObstacleArray = new ArrayList<PurpleObstacle>(8);

		PurpleObstacle temp = new PurpleObstacle(randomXRange, randomYRange);
		purpleObstacleArray.add(0, temp);

		for (int k = 0; k < Game.getInstance().getSimpleObsArray().size(); k++) {
			while (collision.getRectangle(Game.getInstance().getSimpleObsArray().get(k))
					.intersects(collision.getRectangle(getPurpleObstacleArray().get(0)))) {
				randomXRange = (rand.nextInt(maxRangeX - minRangeX) + minRangeX);
				randomYRange = (rand.nextInt(maxRangeY - minRangeY) + minRangeY);
				purpleObstacleArray.remove(0);
				temp.setPositionX(randomXRange);
				temp.setPositionY(randomYRange);
				purpleObstacleArray.add(0, temp);
			}
			
		}

		for (int i = 1; i < 8; i++) {
			randomXRange = (rand.nextInt(maxRangeX - minRangeX) + minRangeX);
			randomYRange = (rand.nextInt(maxRangeY - minRangeY) + minRangeY);

			PurpleObstacle SomeObstacle = new PurpleObstacle(randomXRange, randomYRange);
			purpleObstacleArray.add(i, SomeObstacle);

			counter++;
			for (int j = 0; j < counter; ++j) {

				for (int k = 0; k < Game.getInstance().getSimpleObsArray().size(); k++) {

					while (collision.getRectangle(Game.getInstance().getSimpleObsArray().get(k))
							.intersects(collision.getRectangle(getPurpleObstacleArray().get(counter)))
							|| collision.getRectangle(getPurpleObstacleArray().get(j))
									.intersects(collision.getRectangle(getPurpleObstacleArray().get(counter)))) {
						j = 0;
						randomXRange = (rand.nextInt(maxRangeX - minRangeX) + minRangeX);
						randomYRange = (rand.nextInt(maxRangeY - minRangeY) + minRangeY);
						purpleObstacleArray.remove(i);
						SomeObstacle.setPositionX(randomXRange);
						SomeObstacle.setPositionY(randomYRange);
						purpleObstacleArray.add(counter, SomeObstacle);
					}
				}
			}
		}

		for (int i = 0; i < 8; i++) {

			int xpos = purpleObstacleArray.get(i).getPositionX();
			int ypos = purpleObstacleArray.get(i).getPositionY();
			PurpleObstacle aaa = new PurpleObstacle(xpos, ypos);
			purpleObstacleArray.set(i, aaa);
		}

		return purpleObstacleArray;

	}

	public ArrayList<PurpleObstacle> getPurpleObstacleArray() {
		return purpleObstacleArray;
	}

	public void setPurpleObstacleArray(ArrayList<PurpleObstacle> purpleObstacleArray) {
		this.purpleObstacleArray = purpleObstacleArray;
	}

	public void hollowAbility() {
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

					Game.getInstance().purpleObstacleAppender();

				if (a <= 0) {
					a = 15;
					myTimer.cancel();

					// System.out.println("**************************************************************************");
				}

			}

		};

		myTimer.schedule(task, 0, 1000);
	}

}
