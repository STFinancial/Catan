package stf.main;

import stf.gamePieces.Board;
import stf.gamePieces.Intersection;
import stf.gamePieces.Port;
import stf.gamePieces.Tile;
import stf.gamePieces.TileType;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

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
		for(Intersection i : board.getIntersections()){
			i.updateSprite();
		}
		CoordinateUtil.setupGametoTile(board.getTiles());
		CoordinateUtil.setupGametoInt(board.getIntersections());
		CoordinateUtil.setupGametoPort(board.getPorts());
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
			if(t.getType() != TileType.DESERT){
				t.getPipSprite().draw(batch);
			}
		}
		for(Intersection i : board.getIntersections()){
			i.getSprite().draw(batch);
		}

		for(Port p : board.getPorts()){
			p.getSprite().draw(batch);
			p.getTypeSprite().draw(batch);
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
			Vector3 gameCoords = camera.unproject(new Vector3(x,y,0));
			Object clickedObject = CoordinateUtil.getClickObject(gameCoords, board);
			if(clickedObject != null){
				if(clickedObject instanceof Tile){
					System.out.println("tapped Tile: " + clickedObject);
				}
				if(clickedObject instanceof Intersection){
					Intersection i = (Intersection) clickedObject;
					i.printInfo();
				}
				if(clickedObject instanceof Port){
					System.out.println("tapped Port: " + clickedObject);
				}
			}
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
			if(camera.position.x > 1250)
				camera.position.x = 1250;
			if(camera.position.x < -50)
				camera.position.x = -50;
			if(camera.position.y > 1000)
				camera.position.y = 1000;
			if(camera.position.y < 0)
				camera.position.y = 0;
			
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


	public Board getBoard() {
		return board;
	}
}
