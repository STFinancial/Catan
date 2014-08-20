package stf.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;

import stf.gamePieces.Intersection;
import stf.gamePieces.Path;
import stf.gamePieces.Port;
import stf.gamePieces.Table;
import stf.gamePieces.Tile;
import stf.main.CoordinateUtil;
import stf.main.MultipleVirtualViewportBuilder;
import stf.main.OrthographicCameraWithVirtualViewport;
import stf.main.VirtualViewport;

//This is the GUI for multiple players on one Device
//after initial testing is done will need to be rewritten to support only one player
public class GUI {
	Table table;
	int height;
	int width;
	private OrthographicCameraWithVirtualViewport camera;  
	private MultipleVirtualViewportBuilder multipleVirtualViewportBuilder;  
	private OrthographicCamera guiCamera;
	BitmapFont font;
	
	public GUI (Table table){

		
		this.table = table;
		multipleVirtualViewportBuilder = new MultipleVirtualViewportBuilder(800, 480, 854, 600);  
		VirtualViewport virtualViewport = multipleVirtualViewportBuilder.getVirtualViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());  
		guiCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera =new OrthographicCameraWithVirtualViewport(virtualViewport);
		getCamera().position.set(0f, 0f, 0f);  
		resize();
		font =new BitmapFont();
	}
	
	public void render(Batch batch){
		font.draw(batch, "Bottom Left" , 50 , 50);
		font.draw(batch, "Bottom Right" , width - 100 , 50);
		font.draw(batch, "Top Left" , 50 , height - 50);
		font.draw(batch, "Top Right" , width - 100 ,height - 50);
	}
	
	public void resize(){
		VirtualViewport virtualViewport = multipleVirtualViewportBuilder.getVirtualViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());  
		camera.setVirtualViewport(virtualViewport);  

		camera.updateViewport();  
		camera.position.set(0f, 0f, 0f);  

		height = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();
		guiCamera = new OrthographicCamera(width, height);
		guiCamera.position.set(width/2f, height/2f,0);
	}

	public void tap(float x, float y) {
		System.out.println("\nTap Event !!!!!!!!!!!!!!");
		System.out.println("Screen Coords " + x + " , " + y);
		if(x < 15 && y < 15)
			getCamera().zoom += .1f;
		if(x < 15 && y > Gdx.graphics.getHeight() - 15)
			getCamera().zoom -= .1f;
		Vector3 gameCoords = getCamera().unproject(new Vector3(x,y,0));
		System.out.println("Fame Coords " + gameCoords.x + " , " + gameCoords.y);

		Object clickedObject = CoordinateUtil.getClickObject(gameCoords, table.getBoard());
		if(clickedObject != null){
			if(clickedObject instanceof Tile){
				System.out.println("Tapped Tile: " + clickedObject);
			}else if(clickedObject instanceof Intersection){
				System.out.print("Tapped Intersection: ");
				Intersection i = (Intersection) clickedObject;
				i.printInfo();
			}else if(clickedObject instanceof Port){
				System.out.println("Tapped Port: " + clickedObject);
			}else if(clickedObject instanceof Path){
				Path p = (Path) clickedObject;
				System.out.print("Tapped Path: ");
				 p.printInfo();
			}
		}
	}

	public void pan(float deltaX, float deltaY) {
		getCamera().translate(-1*deltaX, deltaY, 0);
		if(getCamera().position.x > 1250)
			getCamera().position.x = 1250;
		if(getCamera().position.x < -50)
			getCamera().position.x = -50;
		if(getCamera().position.y > 1000)
			getCamera().position.y = 1000;
		if(getCamera().position.y < 0)
			getCamera().position.y = 0;
	}

	public void update() {
		getCamera().update();
		getGuiCamera().update();
	}

	public OrthographicCamera getGuiCamera() {
		return guiCamera;
	}

	public OrthographicCameraWithVirtualViewport getCamera() {
		return camera;
	}

}
