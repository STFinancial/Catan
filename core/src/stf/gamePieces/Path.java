package stf.gamePieces;

import java.util.ArrayList;

public class Path {
	Road road;
	ArrayList<Path> adjacentPaths;
	int ID;
	ArrayList<Intersection> spanningIntersections;
	
	
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

	
}
