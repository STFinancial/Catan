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
	
	public Intersection getIntersection(int index) {
		return intersections[index];
	}
	
	public Intersection[] getIntersections() {
		return intersections;
	}
	
	public Path getPath(int index) {
		//should we do index checking?
		return paths[index];
	}
	
	public Path[] getPaths() {
		return paths;
	}
	
	public Tile getTile(int index) {
		return tiles[index];
	}

	public Tile[] getTiles() {
		return tiles;
	}
	
	
	
	
}
