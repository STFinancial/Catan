package stf.main;

import stf.gamePieces.Board;
import stf.gamePieces.Tile;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class CatanGDX extends com.badlogic.gdx.Game {
	SpriteBatch batch;
	Board board;
	BitmapFont font;
	private OrthographicCameraWithVirtualViewport camera;  
    private MultipleVirtualViewportBuilder multipleVirtualViewportBuilder;  
	
    @Override
	public void create () {
		
	    multipleVirtualViewportBuilder = new MultipleVirtualViewportBuilder(800, 480, 854, 600);  
        VirtualViewport virtualViewport = multipleVirtualViewportBuilder.getVirtualViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());  
          
        camera = new OrthographicCameraWithVirtualViewport(virtualViewport);  
        camera.position.set(0f, 0f, 0f);  
		batch = new SpriteBatch();
		board = new Board();
		font =new BitmapFont();
		Gdx.input.setInputProcessor(new GestureDetector(new MyGestureListener()));
		for(Tile t : board.getTiles()){
			t.updateSprite();
		}
	}

	@Override
	public void render () {
		super.render();
		Gdx.gl.glClearColor(.2f, .3f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(Gdx.input.getInputProcessor().scrolled(1)){
			System.out.println("got here");
		}
		camera.update();   
		batch.begin();
        batch.setProjectionMatrix(camera.combined); 
		for(Tile t : board.getTiles()){
			t.getSprite().draw(batch);
		}
		
		batch.end();
	}
	
	 @Override  
	    public void resize(int width, int height) {  
	        super.resize(width, height);  
	          
	        VirtualViewport virtualViewport = multipleVirtualViewportBuilder.getVirtualViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());  
	        camera.setVirtualViewport(virtualViewport);  
	          
	        camera.updateViewport();  
	        // centers the camera at 0, 0 (the center of the virtual viewport)  
	        camera.position.set(0f, 0f, 0f);  
	          
	       }  
	
	
	public class MyGestureListener implements GestureListener{
		
		@Override
		public boolean touchDown(float x, float y, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean tap(float x, float y, int count, int button) {
			System.out.println(x + " , " + y);
			if(x < 15 && y < 15)
				camera.zoom += .1f;
			if(x < 15 && y > Gdx.graphics.getHeight() - 15)
				camera.zoom -= .1f;
			
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
			System.out.println(camera.origin);
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
