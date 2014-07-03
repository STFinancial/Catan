package stf.gamePieces;


public class Board {
	Intersection[] intersections;
	Path[] paths;
	Tile[] tiles;

	public Board() {
		BoardUtility.initializeBoard(this);
	}
	
	public Intersection[] getIntersections() {
		return intersections;
	}

	public Tile[] getTiles() {
		return tiles;
	}
}
