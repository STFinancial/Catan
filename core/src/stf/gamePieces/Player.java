package stf.gamePieces;

import java.util.ArrayList;

public class Player extends Person {

	
	public Player(PlayerColor color) {
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
	
	
	
	@Override
	public Move getMove() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
