package stf.catan;

import gamePieces.Board;
import gamePieces.Tile;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class CatanGDX extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img, desertImg, hillsImg, mountainsImg, plainsImg, forestImg, pastureImg;
	Board board;
	Camera camera;
	BitmapFont font;
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(800,480);
		board = new Board();
		font =new BitmapFont();
		Gdx.input.setInputProcessor(new GestureDetector(new MyGestureListener()));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(Gdx.input.getInputProcessor().scrolled(1)){
			System.out.println("got here");
		}
		camera.update();
		batch.begin();
		for(Tile t : board.getTiles()){
			batch.draw(t.getType().getImage(), camera.project(t.getGameCoords()).x, camera.project(t.getGameCoords()).y);
			font.draw(batch, "" + t.getNumber(), camera.project(t.getGameCoords()).x + 120, camera.project(t.getGameCoords()).y + 128);
			//font.draw(batch, "x: " + t.getX() + " y: " + t.getY(), t.getGameCoords().x + 120, t.getGameCoords().y + 118);
		}
		
		batch.end();
	}
	public class MyGestureListener implements GestureListener{
		
		@Override
		public boolean touchDown(float x, float y, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean tap(float x, float y, int count, int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean longPress(float x, float y) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean fling(float velocityX, float velocityY, int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean pan(float x, float y, float deltaX, float deltaY) {
			camera.translate(-1*deltaX, deltaY, 0);
			return false;
		}

		@Override
		public boolean panStop(float x, float y, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean zoom(float initialDistance, float distance) {

			System.out.println(initialDistance);
			System.out.println(distance);
			return false;
		}

		@Override
		public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
				Vector2 pointer1, Vector2 pointer2) {
			return false;
		}

	
	}
}
