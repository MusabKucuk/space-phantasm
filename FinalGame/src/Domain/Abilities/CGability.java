package Domain.Abilities;

import Domain.GamePlay.Game;

public class CGability {

	private boolean touchesPaddle = false;

	public void addHpToPaddle() {

		Game.getInstance().getPaddle().setPaddleHP(Game.getInstance().getPaddle().getPaddleHP() + 1);

	}

	public boolean isTouchesPaddle() {
		return touchesPaddle;
	}

	public void setTouchesPaddle(boolean touchesPaddle) {
		this.touchesPaddle = touchesPaddle;
	}

}
