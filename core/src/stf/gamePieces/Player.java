package stf.gamePieces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Player {
	private final boolean human;
	private final List<City> cities = new ArrayList<City>(4);
	private final PlayerColor color;
	/* If optimization is needed, linked lists "might" help (see removeResourceCards) */
	private final List<DevelopmentCard> developmentHand = new ArrayList<DevelopmentCard>(10);
	private final ResourceCollection resourceHand = new ResourceCollection();
	
	//in the future, these and 'cities' could change to playedCities and unplayedCities
	//this change is useful for road, but possibly 'only' useful for road
	private final List<Road> roads = new ArrayList<Road>(15);
	private final List<Settlement> settlements = new ArrayList<Settlement>(5);
	
	protected int numPlayedCities = 0;
	protected int numPlayedKnights = 0;
	protected int numPlayedRoads = 0;
	protected int numPlayedSettlements = 0;
	
	protected int numBrick = 0;
	protected int numLogs = 0;
	protected int numOre = 0;
	protected int numSheep = 0;
	protected int numWheat = 0;
	
	public Player(PlayerColor color, boolean human) {
		this.color = color;
		this.human = human;
		
		Collections.fill(cities, new City(color));
		Collections.fill(roads, new Road(color));
		Collections.fill(settlements, new Settlement(color));
	}
	
	public void addDevelopmentCard(DevelopmentCard card) {
		developmentHand.add(card);
	}
	
	public void addResourceCard(ResourceCard card) {
		incrementResourceCounters(card.getType(), 1);
		resourceHand.add(card);
	}
	
	public void addResourceCards(List<ResourceCard> cards) {
		for (int i = 0; i < cards.size(); ++i) {
			incrementResourceCounters(cards.get(i).getType(), 1);
			resourceHand.add(cards.remove(i));
		}
	}
	
	public boolean equals(Player person) {
		return person.getColor() == color;
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
	
	public ArrayList<Road> getLongestRoad() {
		return longestRoad;
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
		for (int i = 0; i < 5; ++i) {
			if (settlements.get(i).getPosition() == null) {
				return settlements.get(i);
			}
		}
		numPlayedSettlements++;
		return null;
	}
	
	public int getNumPossibleCities() {
		if (numPlayedCities == 4) {
			return 0;
		} else {
			return Math.min(numWheat / 2, numOre / 3);
		}
	}
	
	public int getNumPossibleDevelopmentCards() {
		return Math.min(numSheep, Math.min(numWheat, numOre));
	}
	
	public int getNumPossibleRoads() {
		return Math.min(numBrick, numLogs);
	}
	
	public int getNumPossibleSettlements() {
		return Math.min(Math.min(numSheep, numWheat), Math.min(numBrick, numLogs));
	}
	
	
	
	public int getNumResources(ResourceType type) {
		int total = 0;
		for (int i = 0; i < resourceHand.size(); ++i) {
			if (resourceHand.get(i).getType() == type) {
				++total;
			}
		}
		return total;
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
	
	public int getVictoryPoints() {
		int total = 0;
		total += numPlayedSettlements + numPlayedCities * 2;
		if (hasLongestRoad) {
			total += 2;
		}
		if (hasLargestArmy) {
			total += 2;
		}
		for(DevelopmentCard temp: developmentHand) {
			if (temp.isVictoryPoint()) {
				
				++total;
			}
		}
		
		return total;
	}
	
	public void incrementPlayedKnights() {
		++numPlayedKnights;
	}
	
	public DevelopmentCard removeDevelopmentCard(DevelopmentCard card) {
		for (int i = 0; i < developmentHand.size(); ++i) {
			if (card.equals(developmentHand.get(i))) {
				return developmentHand.remove(i);
			}
		}
		
		return null;
	}
	
	public ResourceCard removeRandomResourceCard() {
		Random rand = new Random();
		ResourceCard card = resourceHand.remove(rand.nextInt(resourceHand.size()));
		incrementResourceCounters(card.getType(), -1);
		return card;
	}
	
	public void updateLongestRoad() {
		
		boolean foundStartPoint = false;
		Road currentRoad;
		ArrayList<Road> currentLongest = null;
		int currentLongestLength = 0;
		ArrayList<Road> current;
		for (int i = 0; i < numPlayedRoads; ++i) {
			currentRoad = roads.get(i);
			if (BoardUtility.isEndpoint(currentRoad)) {
				foundStartPoint = true;
				current = getLongestRoad(new boolean[72], currentRoad, BoardUtility.getEndPointIntersection(currentRoad));
				if (current.size() > currentLongestLength) {
					currentLongestLength = current.size();
					currentLongest = current;
				}
			}
		}
		
		if (!foundStartPoint) {
			for (int i = 0; i < numPlayedRoads; ++i) {
				currentRoad = roads.get(i);
				current = getLongestRoad(new boolean[72], currentRoad, currentRoad.getPosition().getSpanningIntersections().get(0));
				if (current.size() > currentLongestLength) {
					currentLongestLength = current.size();
					currentLongest = current;
				}
			}
		}
		
		longestRoad = currentLongest;
	}
	
	private ArrayList<Road> getLongestRoad(boolean[] checked, Road currentRoad, Intersection reference) {
		
		checked[currentRoad.getPosition().getID()] = true;
		
		if (BoardUtility.isEndpoint(currentRoad)) {
			ArrayList<Road> ROWD = new ArrayList<Road>();
			ROWD.add(currentRoad);
			return ROWD;
		}
		
		Path roadPath = currentRoad.getPosition();
		ArrayList<Path> exitingPaths = reference.getExitingPaths();
		Path currentPath;
		
		int currentLongestLength = 0;
		ArrayList<Road> currentLongest = null;
		ArrayList<Road> temp;
		Road tempRoad;
		for (int i = 0; i < exitingPaths.size(); ++i) {
			currentPath = exitingPaths.get(i);
			tempRoad = currentPath.getRoad();
			if (currentPath.getID() != roadPath.getID()) {
				if (tempRoad != null && tempRoad.getOwnerColor() == color) {
					temp = getLongestRoad(checked, tempRoad, BoardUtility.getInterveningIntersection(currentPath, roadPath));
					if (temp.size() > currentLongestLength) {
						currentLongestLength = temp.size();
						currentLongest = temp;
					}
				}
			}
			
		}
		
		currentLongest.add(currentRoad);
		return currentLongest;
	}
	
	/* This should be called ONLY on valid moves */
	/* Which means the validation on moves needs to be done gui side */
	ArrayList<ResourceCard> removeResourceCards(ResourceType type, int quantity) {
		int index = 0;
		ArrayList<ResourceCard> cards = new ArrayList<ResourceCard>(quantity);
		ResourceCard tempResourceCard;
		while (quantity > 0) {
			tempResourceCard = resourceHand.get(index);
			if (type == tempResourceCard.type) {
				cards.add(resourceHand.remove(index));
				--quantity;
			}
			++index;
		}
		incrementResourceCounters(type, -1 * quantity);
		return cards;
	}
	
	/* Assume only valid input */
	ArrayList<ResourceCard> removeResourceCards(List<ResourceCard> cards) {
		ResourceCard tempCard;
		ArrayList<ResourceCard> cardsToReturn = new ArrayList<ResourceCard>(cards.size());
		for (int i = 0; i < cards.size(); ++i) {
			tempCard = cards.get(i);
			for (int j = 0; j < resourceHand.size(); ++j) {
				if (tempCard.equals(resourceHand.get(j))) {
					incrementResourceCounters(resourceHand.get(j).getType(), -1);
					cardsToReturn.add(resourceHand.remove(j));
				}
			}
		}
		
		return cardsToReturn;
	}
	
	/* Method only for use by monopoly */
	ArrayList<ResourceCard> removeResourceCards(ResourceType type) {
		ArrayList<ResourceCard> cards = new ArrayList<ResourceCard>();
		for (int i = 0; i < resourceHand.size(); ++i) {
			if (resourceHand.get(i).getType() == type) {
				cards.add(resourceHand.remove(i));
				incrementResourceCounters(type, -1);
			}
		}
		return cards;
	}
	
	void returnSettlement(Settlement settlement) {
		settlement.setPosition(null);
		--numPlayedSettlements;
	}
	
	protected void incrementResourceCounters(ResourceType type, int amount) {
		switch (type) {
		case BRICK:
			numBrick += amount;
			break;
			
		case LOGS:
			numLogs += amount;
			break;
			
		case ORE:
			numOre += amount;
			break;
			
		case SHEEP:
			numSheep += amount;
			break;
			
		case WHEAT:
			numWheat += amount;
			break;
			
			default: System.out.println("Shouldn't get here. IncrementResourceCounters switch (Person).");
		}
	}
	
	public abstract Move getMove();
}
