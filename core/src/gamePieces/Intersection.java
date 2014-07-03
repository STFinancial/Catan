package gamePieces;

import java.util.ArrayList;

public class Intersection {
	ArrayList<Intersection> adjacentIntersections;
	ArrayList<Tile> adjacentTiles;
	ArrayList<Path> exitingPaths;
	int ID;
	PortType port;
	
	Intersection(int ID) {
		this.ID = ID;
		adjacentIntersections = new ArrayList<Intersection>(3);
		adjacentTiles = new ArrayList<Tile>(3);
		exitingPaths = new ArrayList<Path>(3);
	}
	
	public ArrayList<Tile> getAdjacentTiles() {
		return adjacentTiles;
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
}
