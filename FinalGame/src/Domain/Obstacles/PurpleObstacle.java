package Domain.Obstacles;

public class PurpleObstacle extends SuperObstacle {

	int type = -1;
	
	
	public PurpleObstacle(int a, int b) {
		super(a, b);
		super.setWidth(24);
		super.setHeight(20);
	}

	
	public int getType() {
		return this.type;
	}


}
