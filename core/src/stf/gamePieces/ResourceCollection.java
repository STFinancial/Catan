package stf.gamePieces;

import java.util.HashMap;
import java.util.Map;

public class ResourceCollection {
	private final Map<ResourceType, Integer> cards = new HashMap<ResourceType, Integer>();
	
	public ResourceCollection() {
		super();
	}
	
	public ResourceCollection(Map<ResourceType, Integer> cards) {
		cards.putAll(cards);
	}
	
	public int getCardsLeft(ResourceType type) {
		return cards.get(type);
	}
	
	public void returnCards(ResourceType type, int amount) {
		cards.replace(type, getCardsLeft(type) + amount);
	}
	
	public int getCard(ResourceType type) {
		return cards.get(type);
	}
}
