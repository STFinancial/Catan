package stf.gamePieces;


public class Board {
	Intersection[] intersections;
	Path[] paths;
	Tile[] tiles;
	Port[] ports;
	private int thiefPosition;
	//if we really want to optimize, we can use a hashmap to map tiles to their roll

	public Board() {
		thiefPosition = 0;
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
	
	public int getThiefPosition() {
		return thiefPosition;
	}
	
	public Tile getTile(int index) {
		return tiles[index];
	}

	public Tile[] getTiles() {
		return tiles;
	}
	
	public void setThiefPosition(int position) {
		thiefPosition = position;
	}

	public Port[] getPorts() {
		return ports;
	}
	
	
	
	
}
