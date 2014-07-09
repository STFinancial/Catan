package stf.gamePieces;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Person {
	protected ArrayList<City> cities;
	protected PlayerColor color;
	/* If optimization is needed, linked lists "might" help (see removeResourceCards) */
	protected ArrayList<DevelopmentCard> developmentHand;
	protected ArrayList<ResourceCard> resourceHand;
	protected ArrayList<Road> roads;
	protected ArrayList<Settlement> settlements;
	
	protected int numPlayedCities;
	protected int numPlayedRoads;
	protected int numPlayedSettlements;
		
	
	public Person(PlayerColor color) {
		this.color = color;
		numPlayedRoads = 0;
		numPlayedCities = 0;
		numPlayedSettlements = 0;
		
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

	City getNextPlayableCity() {
		if (numPlayedCities < 4) {
			return cities.get(numPlayedCities++);
		} else {
			return null;
		}
	}
	
	/* The ONLY TIME this should be called is when a road is being used */
	/* In that case, should this method even exist? */
	Road getNextPlayableRoad() {
		if (numPlayedRoads < 15) {
			return roads.get(numPlayedRoads++);
		} else {
			return null;
		}
	}
	
	Settlement getNextPlayableSettlement() {
		if (numPlayedSettlements < 5) {
			return settlements.get(numPlayedSettlements++);
		} else {
			return null;
		}
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
	
	void returnSettlement(Settlement settlement) {
		settlement.setPosition(null);
		--numPlayedSettlements;
	}
	
	/* This should be called ONLY on valid moves */
	/* Which means the validation on moves needs to be done gui side */
	void removeResourceCards(ResourceType type, int quantity) {
		int index = 0;
		ResourceCard tempResourceCard;
		while (quantity > 0) {
			tempResourceCard = resourceHand.get(index);
			if (type == tempResourceCard.type) {
				resourceHand.remove(index);
				--quantity;
			}
			++index;
		}
	}
	
	public abstract Move getMove();
}
