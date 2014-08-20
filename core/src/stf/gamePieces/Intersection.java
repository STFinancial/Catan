package stf.gamePieces;

import java.util.ArrayList;

import stf.main.CoordinateUtil;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

public class Intersection {
	ArrayList<Intersection> adjacentIntersections;
	ArrayList<Tile> adjacentTiles;
	ArrayList<Path> exitingPaths;
	int ID;
	int x, y;
	PortType port;
	Vector3 coords;
	Sprite sprite;
	Building building = null;
	
	Intersection(int ID) {
		this.ID = ID;
		adjacentIntersections = new ArrayList<Intersection>(3);
		adjacentTiles = new ArrayList<Tile>(3);
		exitingPaths = new ArrayList<Path>(3);
	}
	
	public void build(Building building) {
		this.building = building;
		building.setPosition(this);
	}
	
	public ArrayList<Tile> getAdjacentTiles() {
		return adjacentTiles;
	}
	
	public Building getBuilding() {
		return building;
	}
	
	public ArrayList<Path> getExitingPaths() {
		return exitingPaths;
	}
	
	public int getID() {
		return ID;
	}
	
	public PortType getPortType() {
		return port;
	}
	
	public boolean hasPort() {
		return port != null;
	}
	
	public void printInfo() {
		System.out.println();
		System.out.println("Intersection Number: " + ID);
		if (hasPort()){
			System.out.println("Has Port of Type: " + port.toString());
		} else {
			System.out.println("Does Not Have a Port");
		}
		System.out.println("Has Adjacent Intersections:");
		
		for (int i = 0; i < adjacentIntersections.size(); i++) {
			System.out.println(adjacentIntersections.get(i).getID());
		}
		
		System.out.println("Has Exiting Paths:");
		
		for (int i = 0; i < exitingPaths.size(); i++) {
			System.out.println(exitingPaths.get(i).getID());
		}
	}
	
	
		public void updateSprite(){
			coords = new Vector3(x,y,0);
			if(building == null){
				sprite = new Sprite(new Texture("emptyIntersection.gif"));
			}else{
				sprite = new Sprite(building.getImg());
			}
			sprite.setBounds(CoordinateUtil.intToGame(ID).x, CoordinateUtil.intToGame(ID).y,64,64);
		}
		
		public Sprite getSprite(){
			return sprite;
		}
}
