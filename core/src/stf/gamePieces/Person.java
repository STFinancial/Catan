package stf.gamePieces;

import java.util.ArrayList;

public abstract class Person {
	protected ArrayList<City> cities;
	protected PlayerColor color;
	protected ArrayList<DevelopmentCard> developmentHand;
	protected ArrayList<ResourceCard> resourceHand;
	protected ArrayList<Road> roads;
	protected ArrayList<Settlement> settlements;
		
	
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
