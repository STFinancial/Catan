package stf.main;

import com.badlogic.gdx.InputAdapter;

public class InputHandler extends InputAdapter {

	public boolean touchDown (int x, int y, int pointer, int button) {
		// your touch down code here
		return true; // return true to indicate the event was handled
	}

	@Override
	public boolean scrolled (int amount) {
		return false;
	}

	public boolean tap(float x, float y, int count, int button) {
		gui.tap(x,y);
		return false;
	}

	public boolean pan(float x, float y, float deltaX, float deltaY) {
		gui.pan(deltaX,deltaY);
		return false;
	}

	public boolean zoom(float initialDistance, float distance) {

		System.out.println(initialDistance);
		System.out.println(distance);
		return false;
	}


}
