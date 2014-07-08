package stf.gamePieces;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Person {
	protected ArrayList<City> cities;
	protected PlayerColor color;
	protected ArrayList<DevelopmentCard> developmentHand;
	protected ArrayList<ResourceCard> resourceHand;
	protected ArrayList<Road> roads;
	protected ArrayList<Settlement> settlements;
		
	
	public Person(PlayerColor color) {
		this.color = color;
		
		cities = new ArrayList<City>(4);
		City tempCity;
		for (int i = 0; i < 4; ++i) {
			tempCity = new City(color);
			cities.add(tempCity);
		}
		
		developmentHand = new ArrayList<DevelopmentCard>(10);
		resourceHand = new ArrayList<ResourceCard>(20);
		
		roads = new ArrayList<Road>(15);
		Road tempRoad;
		for (int i = 0; i < 15; ++i) {
			tempRoad = new Road(color);
			roads.add(tempRoad);
		}
		
		settlements = new ArrayList<Settlement>(5);
		Settlement tempSet;
		for (int i = 0; i < 5; ++i) {
			tempSet = new Settlement(color);
			settlements.add(tempSet);
		}
	}
	
	public void addResourceCard(ResourceCard card) {
		resourceHand.add(card);
	}

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
