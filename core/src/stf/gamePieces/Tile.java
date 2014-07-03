package stf.gamePieces;

import java.util.ArrayList;

import stf.main.CoordinateUtil;

import com.badlogic.gdx.math.Vector3;

public class Tile {
	private TileType type;
	private int pip;
	//sloppy, but it will have to do
	ArrayList<Intersection> intersections;
	ArrayList<Path> paths;
	int x;
	int y;
	Vector3 coords;

	public Tile(TileType type, int pip) {
		this.type = type;
		this.pip = pip;
		intersections = new ArrayList<Intersection>(6);
		paths = new ArrayList<Path>(6);
	}
	
	public int getNumber() {
		return pip;
	}
	
	public TileType getType() {
		return type;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Vector3 getGameCoords(){
		return CoordinateUtil.tileToGame(coords);
	}
	
	
}
