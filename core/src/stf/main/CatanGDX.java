package stf.main;

import stf.gamePieces.Board;
import stf.gamePieces.Intersection;
import stf.gamePieces.Path;
import stf.gamePieces.Table;
import stf.gamePieces.Tile;
import stf.gui.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class CatanGDX extends com.badlogic.gdx.Game {
	SpriteBatch batch;
	Board board;
	Table table;
	GUI gui;
	
	@Override
	public void create () {
		Gdx.graphics.setContinuousRendering(false);
		batch = new SpriteBatch();
		table = new Table(4, 0);
		board = table.getBoard();
		
		Gdx.input.isPeripheralAvailable(Peripheral.)
		Gdx.input.setInputProcessor(new GestureDetector(listener));
		Gdx.input.getInputProcessor();
		Gdx.input.setInputProcessor(new InputHandler());
		
		//Probably should move this to table constructor
		for(Tile t : board.getTiles()){
			t.updateSprite();
		}
		for(Intersection i : board.getIntersections()){
			i.updateSprite();
		}
		for(Path p : board.getPaths()){
			p.updateSprite();
		}
		CoordinateUtil.setupGametoTile(board.getTiles());
		CoordinateUtil.setupGametoInt(board.getIntersections());
		CoordinateUtil.setupGametoPort(board.getPorts());
		CoordinateUtil.setupGametoPath(board.getPaths());
		
		
		gui = new GUI(table);
		Gdx.graphics.requestRendering();
	}

	@Override
	public void render () {
		super.render();
		Gdx.gl.glClearColor(.2f, .3f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gui.update();   

		batch.begin();
		batch.setProjectionMatrix(gui.getCamera().combined); 
		table.render(batch);
		batch.setProjectionMatrix(gui.getGuiCamera().combined);
		gui.render(batch);
		batch.end();
		
	}

	@Override  
	public void resize(int width, int height) {  
		super.resize(width, height);  
		gui.resize();
	}  

	public Board getBoard() {
		return board;
	}
}
