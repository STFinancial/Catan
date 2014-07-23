package stf.gamePieces;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.math.Vector3;

public class BoardUtility {
	
	
	public static <T> T[] arrayShuffle(T[] array) {
		Random r = new Random();
		int len = array.length;
		int tempIndex;
		T tempItem;
		
		for (int i = 0; i < len; i++) {
			tempIndex = r.nextInt(len);
			
			tempItem = array[tempIndex];
			array[tempIndex] = array[i];
			array[i] = tempItem;
		}
		
		return array;
	}
	
	public static Intersection getEndPointIntersection(Road road) {
		Path path = road.getPosition();
		int pathID = path.getID();
		ArrayList<Intersection> spanningIntersections = path.getSpanningIntersections();
		PlayerColor color = road.getOwnerColor();
		
		Intersection tempInt;
		Building tempBuilding;
		ArrayList<Path> exitingPaths;
		Path tempPath;
		boolean hasConnectors;
		Road tempRoad;
		for (int i = 0; i < 2; ++i) {
			tempInt = spanningIntersections.get(i);
			tempBuilding = tempInt.getBuilding();
			hasConnectors = false;
			if (tempBuilding != null && tempBuilding.ownerColor != color) {
				return tempInt;
			} else {
				exitingPaths = tempInt.getExitingPaths();
				for (int j = 0; j < exitingPaths.size(); ++j) {
					tempPath = exitingPaths.get(j);
					if (tempPath.getID() != pathID) {
						tempRoad = tempPath.getRoad();
						if (tempRoad != null) {
							hasConnectors = hasConnectors || tempRoad.getOwnerColor() == color;
						}
					}
				}
			}
			if (!hasConnectors) {
				return tempInt;
			}
		}
		
		return null;
	}
	
	public static Intersection getInterveningIntersection(Path path1, Path path2) {
		ArrayList<Intersection> spanningInts1 = path1.getSpanningIntersections();
		ArrayList<Intersection> spanningInts2 = path2.getSpanningIntersections();
		Intersection int11 = spanningInts1.get(0), int21 = spanningInts2.get(0);
		if (int11.getID() == int21.getID()) {
			return int11;
		}
		Intersection int22 = spanningInts2.get(1);
		if (int11.getID() == int22.getID()) {
			return int11;
		}
		return spanningInts1.get(1);
	}
	
	/* A method that puts together all the necessary board pieces */
	public static void initializeBoard(Board board) {
		initializeTiles(board);
		do {
			initializeTiles(board);
		} while (!checkTiles(board.getTiles()));
		createIntersectionsAndPaths(board);
		placePorts(board);
		setIntersectionsAndPathsToTiles(board);
		printInfo(board);
	}
	
	public static boolean isEndpoint(Road road) {
		Path path = road.getPosition();
		int pathID = path.getID();
		ArrayList<Intersection> spanningIntersections = path.getSpanningIntersections();
		PlayerColor color = road.getOwnerColor();
		
		//conditions for endpoint
			//either:
				//one of the spanning intersections has an enemy building on it
				//all exiting paths of one or more of the spanning intersections do not contain roads of the same player
		
		Intersection tempInt;
		Building tempBuilding;
		ArrayList<Path> exitingPaths;
		Path tempPath;
		boolean hasConnectors;
		Road tempRoad;
		for (int i = 0; i < 2; ++i) {
			tempInt = spanningIntersections.get(i);
			tempBuilding = tempInt.getBuilding();
			hasConnectors = false;
			if (tempBuilding != null && tempBuilding.ownerColor != color) {
				return true;
			} else {
				exitingPaths = tempInt.getExitingPaths();
				for (int j = 0; j < exitingPaths.size(); ++j) {
					tempPath = exitingPaths.get(j);
					if (tempPath.getID() != pathID) {
						tempRoad = tempPath.getRoad();
						if (tempRoad != null) {
							hasConnectors = hasConnectors || tempRoad.getOwnerColor() == color;
						}
					}
				}
			}
			if (!hasConnectors) {
				return true;
			}
		}
		
		return false;
	}

	public static void printInfo(Board board) {
		for (int i = 0; i < 54; i++){
			board.intersections[i].printInfo();
		}
		for (int i = 0; i < 72; i++){
			board.paths[i].printInfo();
		}
	}
	
	
	/* Private methods should be created at the bottom */
	
	

	private static boolean checkTiles(Tile[] tiles){
		for (int index = 0; index < tiles.length; index++){
			if (!isValid(tiles[index], tiles, index)){
				return false;
			}
		}
		return true;
	}
	
	private static void createIntersectionsAndPaths(Board board){
		board.intersections = new Intersection[54];
		board.paths = new Path[72];
		//create each of the 54 intersections
		for (int i = 0; i < 54; i++){
			board.intersections[i] = new Intersection(i);
		}
		
		//assign them tiles
		board.intersections[0].adjacentTiles.add(board.tiles[0]);
		
		board.intersections[1].adjacentTiles.add(board.tiles[0]);
		
		board.intersections[2].adjacentTiles.add(board.tiles[0]);
		board.intersections[2].adjacentTiles.add(board.tiles[1]);
		
		board.intersections[3].adjacentTiles.add(board.tiles[1]);
		
		board.intersections[4].adjacentTiles.add(board.tiles[1]);
		board.intersections[4].adjacentTiles.add(board.tiles[2]);
		
		board.intersections[5].adjacentTiles.add(board.tiles[2]);
		
		board.intersections[6].adjacentTiles.add(board.tiles[2]);
		
		board.intersections[7].adjacentTiles.add(board.tiles[3]);
		
		board.intersections[8].adjacentTiles.add(board.tiles[0]);
		board.intersections[8].adjacentTiles.add(board.tiles[3]);
		
		board.intersections[9].adjacentTiles.add(board.tiles[0]);
		board.intersections[9].adjacentTiles.add(board.tiles[3]);
		board.intersections[9].adjacentTiles.add(board.tiles[4]);
		
		board.intersections[10].adjacentTiles.add(board.tiles[0]);
		board.intersections[10].adjacentTiles.add(board.tiles[1]);
		board.intersections[10].adjacentTiles.add(board.tiles[4]);
		
		board.intersections[11].adjacentTiles.add(board.tiles[1]);
		board.intersections[11].adjacentTiles.add(board.tiles[4]);
		board.intersections[11].adjacentTiles.add(board.tiles[5]);
		
		board.intersections[12].adjacentTiles.add(board.tiles[1]);
		board.intersections[12].adjacentTiles.add(board.tiles[2]);
		board.intersections[12].adjacentTiles.add(board.tiles[5]);
		
		board.intersections[13].adjacentTiles.add(board.tiles[2]);
		board.intersections[13].adjacentTiles.add(board.tiles[5]);
		board.intersections[13].adjacentTiles.add(board.tiles[6]);
		
		board.intersections[14].adjacentTiles.add(board.tiles[2]);
		board.intersections[14].adjacentTiles.add(board.tiles[6]);
		
		board.intersections[15].adjacentTiles.add(board.tiles[6]);
		
		board.intersections[16].adjacentTiles.add(board.tiles[7]);
		
		board.intersections[17].adjacentTiles.add(board.tiles[3]);
		board.intersections[17].adjacentTiles.add(board.tiles[7]);
		
		board.intersections[18].adjacentTiles.add(board.tiles[3]);
		board.intersections[18].adjacentTiles.add(board.tiles[7]);
		board.intersections[18].adjacentTiles.add(board.tiles[8]);
		
		board.intersections[19].adjacentTiles.add(board.tiles[3]);
		board.intersections[19].adjacentTiles.add(board.tiles[4]);
		board.intersections[19].adjacentTiles.add(board.tiles[8]);
		
		board.intersections[20].adjacentTiles.add(board.tiles[4]);
		board.intersections[20].adjacentTiles.add(board.tiles[8]);
		board.intersections[20].adjacentTiles.add(board.tiles[9]);
		
		board.intersections[21].adjacentTiles.add(board.tiles[4]);
		board.intersections[21].adjacentTiles.add(board.tiles[5]);
		board.intersections[21].adjacentTiles.add(board.tiles[9]);
		
		board.intersections[22].adjacentTiles.add(board.tiles[5]);
		board.intersections[22].adjacentTiles.add(board.tiles[9]);
		board.intersections[22].adjacentTiles.add(board.tiles[10]);
		
		board.intersections[23].adjacentTiles.add(board.tiles[5]);
		board.intersections[23].adjacentTiles.add(board.tiles[6]);
		board.intersections[23].adjacentTiles.add(board.tiles[10]);
		
		board.intersections[24].adjacentTiles.add(board.tiles[6]);
		board.intersections[24].adjacentTiles.add(board.tiles[10]);
		board.intersections[24].adjacentTiles.add(board.tiles[11]);
		
		board.intersections[25].adjacentTiles.add(board.tiles[6]);
		board.intersections[25].adjacentTiles.add(board.tiles[11]);
		
		board.intersections[26].adjacentTiles.add(board.tiles[11]);
		
		board.intersections[27].adjacentTiles.add(board.tiles[7]);
		
		board.intersections[28].adjacentTiles.add(board.tiles[7]);
		board.intersections[28].adjacentTiles.add(board.tiles[12]);
		
		board.intersections[29].adjacentTiles.add(board.tiles[7]);
		board.intersections[29].adjacentTiles.add(board.tiles[8]);
		board.intersections[29].adjacentTiles.add(board.tiles[12]);
		
		board.intersections[30].adjacentTiles.add(board.tiles[8]);
		board.intersections[30].adjacentTiles.add(board.tiles[12]);
		board.intersections[30].adjacentTiles.add(board.tiles[13]);
		
		board.intersections[31].adjacentTiles.add(board.tiles[8]);
		board.intersections[31].adjacentTiles.add(board.tiles[9]);
		board.intersections[31].adjacentTiles.add(board.tiles[13]);
		
		board.intersections[32].adjacentTiles.add(board.tiles[9]);
		board.intersections[32].adjacentTiles.add(board.tiles[13]);
		board.intersections[32].adjacentTiles.add(board.tiles[14]);
		
		board.intersections[33].adjacentTiles.add(board.tiles[9]);
		board.intersections[33].adjacentTiles.add(board.tiles[10]);
		board.intersections[33].adjacentTiles.add(board.tiles[14]);
		
		board.intersections[34].adjacentTiles.add(board.tiles[10]);
		board.intersections[34].adjacentTiles.add(board.tiles[14]);
		board.intersections[34].adjacentTiles.add(board.tiles[15]);
		
		board.intersections[35].adjacentTiles.add(board.tiles[10]);
		board.intersections[35].adjacentTiles.add(board.tiles[11]);
		board.intersections[35].adjacentTiles.add(board.tiles[15]);
		
		board.intersections[36].adjacentTiles.add(board.tiles[11]);
		board.intersections[36].adjacentTiles.add(board.tiles[15]);
		
		board.intersections[37].adjacentTiles.add(board.tiles[11]);
		
		board.intersections[38].adjacentTiles.add(board.tiles[12]);
		
		board.intersections[39].adjacentTiles.add(board.tiles[12]);
		board.intersections[39].adjacentTiles.add(board.tiles[16]);
		
		board.intersections[40].adjacentTiles.add(board.tiles[12]);
		board.intersections[40].adjacentTiles.add(board.tiles[13]);
		board.intersections[40].adjacentTiles.add(board.tiles[16]);
		
		board.intersections[41].adjacentTiles.add(board.tiles[13]);
		board.intersections[41].adjacentTiles.add(board.tiles[16]);
		board.intersections[41].adjacentTiles.add(board.tiles[17]);
		
		board.intersections[42].adjacentTiles.add(board.tiles[13]);
		board.intersections[42].adjacentTiles.add(board.tiles[14]);
		board.intersections[42].adjacentTiles.add(board.tiles[17]);
		
		board.intersections[43].adjacentTiles.add(board.tiles[14]);
		board.intersections[43].adjacentTiles.add(board.tiles[17]);
		board.intersections[43].adjacentTiles.add(board.tiles[18]);
		
		board.intersections[44].adjacentTiles.add(board.tiles[14]);
		board.intersections[44].adjacentTiles.add(board.tiles[15]);
		board.intersections[44].adjacentTiles.add(board.tiles[18]);
		
		board.intersections[45].adjacentTiles.add(board.tiles[15]);
		board.intersections[45].adjacentTiles.add(board.tiles[18]);
		
		board.intersections[46].adjacentTiles.add(board.tiles[15]);
		
		board.intersections[47].adjacentTiles.add(board.tiles[16]);
		
		board.intersections[48].adjacentTiles.add(board.tiles[16]);
		
		board.intersections[49].adjacentTiles.add(board.tiles[16]);
		board.intersections[49].adjacentTiles.add(board.tiles[17]);
		
		board.intersections[50].adjacentTiles.add(board.tiles[17]);
		
		board.intersections[51].adjacentTiles.add(board.tiles[17]);
		board.intersections[51].adjacentTiles.add(board.tiles[18]);
		
		board.intersections[52].adjacentTiles.add(board.tiles[18]);
		
		board.intersections[53].adjacentTiles.add(board.tiles[18]);
		
		
		
		//create the paths
		Path newPath;
		ArrayList<Intersection> spans;
		for (int i = 0; i < 72; i++){
			newPath = new Path(i);
			spans = new ArrayList<Intersection>(2);
			if (i < 6) {
				spans.add( board.intersections[i]);
				spans.add( board.intersections[i + 1]);
			} else if (i < 14) {
				spans.add( board.intersections[i + 1]);
				spans.add( board.intersections[i + 2]);
			} else if (i < 24) {
				spans.add( board.intersections[i + 2]);
				spans.add( board.intersections[i + 3]);
			} else if (i < 34) {
				spans.add( board.intersections[i + 3]);
				spans.add( board.intersections[i + 4]);
			} else if (i < 42) {
				spans.add( board.intersections[i + 4]);
				spans.add( board.intersections[i + 5]);
			} else if (i < 48) {
				spans.add( board.intersections[i + 5]);
				spans.add( board.intersections[i + 6]);
			} else if (i < 52) {
				spans.add( board.intersections[((i - 48) * 2)]);
				spans.add( board.intersections[((i - 48) * 2) + 8]);
			} else if (i < 57) {
				spans.add( board.intersections[((i - 49) * 2) + 1]);
				spans.add( board.intersections[((i - 49) * 2) + 11]);
			} else if (i < 63) {
				spans.add( board.intersections[((i - 49) * 2)]);
				spans.add( board.intersections[((i - 49) * 2) + 11]);
			} else if (i < 68) {
				spans.add( board.intersections[((i - 49) * 2)]);
				spans.add( board.intersections[((i - 49) * 2) + 10]);
			} else if (i < 72) {
				spans.add( board.intersections[((i - 49) * 2) + 1]);
				spans.add( board.intersections[((i - 49) * 2) + 9]);
			}
			newPath.spanningIntersections = spans;
			board.paths[i] = newPath;
			
			//then we can place these as the leaving paths for each intersection
			spans.get(0).exitingPaths.add(newPath);
			spans.get(1).exitingPaths.add(newPath);
		}
		
		//we can initialize paths and their spanning intersections
		
		//then we can get adjacent paths of each of the paths by simply finding the leaving paths
		//of each of the spanning intersections, and using the ones that are not equal to the one we are currently talking about
		Intersection currentInt;
		ArrayList<Path> exitingPaths;
		Path currentPath;
		for (int i = 0; i < 54; i++) {
			currentInt = board.intersections[i];
			//get the current intersection we're working with
			
			exitingPaths = currentInt.getExitingPaths();
			//find all the exiting paths
			
			//this loop assigns all adjacent intersections to each intersection
			for (int j = 0; j < exitingPaths.size(); j++){
				currentPath = exitingPaths.get(j);
				if (currentPath.getSpanningIntersections().get(0).getID() != i){
					currentInt.adjacentIntersections.add(currentPath.getSpanningIntersections().get(0));
				} else {
					currentInt.adjacentIntersections.add(currentPath.getSpanningIntersections().get(1));
				}
			}
		}
		
		Path tempPath;
		for (int i = 0; i < 72; i++){
			
			currentPath = board.paths[i];
			spans = currentPath.getSpanningIntersections();
			
			for (int k = 0; k < 2; k++){
				
				exitingPaths = spans.get(k).getExitingPaths();
				
				for (int j = 0; j < exitingPaths.size(); j++) {
					tempPath = exitingPaths.get(j);
					if (tempPath.getID() != i){
						currentPath.adjacentPaths.add(tempPath);
					}
				
				}
			}
		}
	}
	
	private static boolean isValid(Tile tile, Tile[] tiles, int i) {
		//the tile is comparing to itself, so this will always fail
		int similarAdjacent = 0;
		TileType type = tile.getType();
		boolean isRed = tile.getNumber() == 6 || tile.getNumber() == 8;
		int x = tile.x;
		int y = tile.y;
		
		int xDif, yDif;
		Tile current;
		
		for (int index = 0; index < tiles.length; index++){
			if (index != i){
				current = tiles[index];
				xDif = Math.abs(x - current.x);
				yDif = Math.abs(y - current.y);
				
				if (yDif < 2 && xDif < 2){
					if (isRed && (current.getNumber() == 8 || current.getNumber() == 6)){
						return false;
					}
					
					if (type == current.getType()){
						similarAdjacent++;
					}
				}
			}
		}
		
		if (similarAdjacent > 1 && (type == TileType.FIELDS || type == TileType.PASTURE || type == TileType.FOREST)){
			return false;
		} else if ((type == TileType.HILLS || type == TileType.MOUNTAINS) && similarAdjacent > 0){
			return false;
		}
		
		return true;
	}
	
	private static void initializeTiles(Board board) {
		Integer[] numbers = {2,3,3,4,4,5,5,6,6,8,8,9,9,10,10,11,11,12};
		numbers = BoardUtility.arrayShuffle(numbers);
		Tile[] tiles = new Tile[19];
		
		
		Tile newTile;
		for (int i = 0; i < 19; i++){
			
			//assign each tile a tile type and a number
			if (i < 1)
			{
				newTile = new Tile(TileType.DESERT, 0);
			} 
			else if (i < 4) 
			{
				newTile = new Tile(TileType.HILLS, numbers[i-1]);
			} 
			else if (i < 7) 
			{
				newTile = new Tile(TileType.MOUNTAINS, numbers[i-1]);
			} 
			else if (i < 11) 
			{
				newTile = new Tile(TileType.FIELDS, numbers[i-1]);
			} 
			else if (i < 15) 
			{
				newTile = new Tile(TileType.PASTURE, numbers[i-1]);
			} 
			else if (i < 19) 
			{
				newTile = new Tile(TileType.FOREST, numbers[i-1]);
			} 
			else 
			{
				System.out.println("It shouldn't get here: Initialize Tiles");
				newTile = null;
			}
			tiles[i] = newTile;
		}
				
		//shuffle the tiles so we can randomly assign them coordinates
		tiles = BoardUtility.arrayShuffle(tiles);
		
		//assign coordinates
		for (int i = 0; i < 19; i++){
			Tile tile = tiles[i];
			if (i < 3){
				tile.x = i;
				tile.y = 0;
			} else if (i < 7) {
				tile.x = i - 3;
				tile.y = 1;
			} else if (i < 12) {
				tile.x = i - 7;
				tile.y = 2;
			} else if (i < 16) {
				tile.x = i - 12;
				tile.y = 3;
			} else if (i < 19) {
				tile.x = i - 16;
				tile.y = 4;
			} else {
				System.out.println("It shouldn't get here: Board Constructor Loop 2");
			}
		}
		board.tiles = tiles;

	}
	
	private static void placePorts(Board board){
		PortType[] ports = new PortType[9];
		ports[3] = ports[2] = ports[1] = ports[0] = PortType.RANDOM;
		ports[4] = PortType.BRICK;
		ports[5] = PortType.ORE;
		ports[6] = PortType.SHEEP;
		ports[7] = PortType.LOGS;
		ports[8] = PortType.WHEAT;
		
		ports = BoardUtility.arrayShuffle(ports);
		
		//ports are on 0:1 , 3:4, 7:17, 14:15, 26:37, 28:38, 45:46, 47:48, 50:51
		//arranged clockwise 0:1, 3:4, 14:15, 26:37, 45:46, 50:51, 47:48, 28:38, 7:17
		
		board.intersections[0].port = ports[0];
		
		board.intersections[1].port = ports[0];
		
		board.intersections[3].port = ports[1];
		
		board.intersections[4].port = ports[1];
		
		board.intersections[14].port = ports[2];
		
		board.intersections[15].port = ports[2];
		
		board.intersections[26].port = ports[3];
		
		board.intersections[37].port = ports[3];
		
		board.intersections[45].port = ports[4];
		
		board.intersections[46].port = ports[4];
		
		board.intersections[50].port = ports[5];
		
		board.intersections[51].port = ports[5];
		
		board.intersections[47].port = ports[6];
		
		board.intersections[48].port = ports[6];
		
		board.intersections[28].port = ports[7];
		
		board.intersections[38].port = ports[7];
		
		board.intersections[7].port = ports[8];
		
		board.intersections[17].port = ports[8];
		
		//that should be the extent of port placing that needs to be done
		//HA, Maybe for your MERE BACKEND!, the world of graphics requires much more from you.
		board.ports = new Port[9];
		for(int i = 0; i<9; i++){
			board.ports[i] = new Port(ports[i], i);
		}
	}
	
	
	
	private static void setIntersectionsAndPathsToTiles(Board board){
		Intersection currentInt;
		Path currentPath;
		ArrayList<Tile> adjacentTiles;
		Tile currentTile;
		for (int i = 0; i < 54; i++){
			currentInt = board.intersections[i];
			adjacentTiles = currentInt.getAdjacentTiles();
			for (int j = 0; j < adjacentTiles.size(); j++){
				currentTile = adjacentTiles.get(j);
				currentTile.intersections.add(currentInt);
			}
		}
		
		ArrayList<Intersection> spans;
		for (int i = 0; i < 72; i++){
			currentPath = board.paths[i];
			//we know that the tiles that will be adjacent to the path
			//will be the ones that are similar amongst both spanning intersections
			spans = currentPath.getSpanningIntersections();
			
			
			for (int j = 0; j < spans.get(0).getAdjacentTiles().size(); j++){
				currentTile = spans.get(0).getAdjacentTiles().get(j);
				for (int k = 0; k < spans.get(1).getAdjacentTiles().size(); k++){
					if (currentTile.equals(spans.get(1).getAdjacentTiles().get(k))){
						currentTile.paths.add(currentPath);
					}
				}
			}
		}
	}
}
