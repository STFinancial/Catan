package stf.gamePieces;

import java.util.ArrayList;


public class Board {
	Intersection[] intersections;
	Path[] paths;
	Tile[] tiles;
	//if we really want to optimize, we can use a hashmap to map tiles to their roll

	public Board() {
		BoardUtility.initializeBoard(this);
	}
	
	public Intersection[] getIntersections() {
		return intersections;
	}
	
	public Path[] getPaths() {
		return paths;
	}

	public Tile[] getTiles() {
		return tiles;
	}
	
	
	
	
}
