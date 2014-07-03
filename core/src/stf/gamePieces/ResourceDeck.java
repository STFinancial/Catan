package stf.gamePieces;

import java.util.Stack;

public class ResourceDeck {
	private ResourceType type;
	private Stack<ResourceCard> deck;
	
	
	public ResourceDeck(ResourceType type) {
		this.type = type;
		
		deck = new Stack<ResourceCard>();
		
		//19 of each resource
		ResourceCard newResourceCard;
		for (int i = 0; i < 19; i++) {
			newResourceCard = new ResourceCard();
			newResourceCard.type = type;
			deck.push(newResourceCard);
		}
	}
	
	public ResourceCard dealCard() {
		if (deck.isEmpty()) {
			return null;
		} else {
			return deck.pop();
		}
	}
	
	public ResourceType getType() {
		return type;
	}
	
	public boolean isEmpty() {
		return deck.isEmpty();
	}
	
	public void returnCard(ResourceCard card) {
		deck.push(card);
	}
}
