package stf.gamePieces;

import java.util.ArrayList;

import stf.main.CoordinateUtil;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

public class Path {
	Road road;
	ArrayList<Path> adjacentPaths;
	int ID;
	ArrayList<Intersection> spanningIntersections;
	private Sprite sprite;
	
	
	Path(int ID) {
		this.ID = ID;
		adjacentPaths = new ArrayList<Path>(4);
		spanningIntersections = new ArrayList<Intersection>(2);
	}
	
	public void build(Road road) {
		this.road = road;
		road.setPosition(this);
	}
	
	public int getID() {
		return ID;
	}

	public ArrayList<Intersection> getSpanningIntersections() {
		return spanningIntersections;
	}
	
	public void printInfo(){
		System.out.println();
		System.out.println("Path Number: " + ID);
		System.out.println("Connects Intersections: " + spanningIntersections.get(0).getID()+ " " + spanningIntersections.get(1).getID());
		System.out.println("Has Adjacent Paths: ");
		for (int i = 0; i < adjacentPaths.size(); i++){
			System.out.println(adjacentPaths.get(i).getID());
		}
		System.out.println();
	}
	
	 //To only be called on creation 
	public void updateSprite(){
		sprite = new Sprite(new Texture("path.gif"));
		sprite.setBounds(CoordinateUtil.pathToGame(ID).x, CoordinateUtil.pathToGame(ID).y,128,128);
		sprite.rotate(CoordinateUtil.pathRotation(ID));
	}
	
	public Sprite getSprite(){
		return sprite;
	}
	

	
}
