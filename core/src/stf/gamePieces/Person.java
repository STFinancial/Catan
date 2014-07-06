package stf.gamePieces;

import java.util.ArrayList;

public abstract class Person {
	private ArrayList<City> cities;
	private PlayerColor color;
	private ArrayList<DevelopmentCard> developmentHand;
	private ArrayList<ResourceCard> resourceHand;
	private ArrayList<Road> roads;
	private ArrayList<Settlement> settlements;
		
	
	public ArrayList<City> getCities() {
		return cities;
	}
	
	public PlayerColor getColor() {
		return color;
	}
	
	public ArrayList<DevelopmentCard> getDevelopmentHand() {
		return developmentHand;
	}
	
	public ArrayList<ResourceCard> getResourceHand() {
		return resourceHand;
	}
	
	public ArrayList<Road> getRoads() {
		return roads;
	}
	
	public ArrayList<Settlement> getSettlements() {
		return settlements;
	}
	
	public abstract Move getMove();
}
