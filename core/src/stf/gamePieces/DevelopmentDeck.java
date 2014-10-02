package stf.gamePieces;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DevelopmentDeck {
	private Stack<DevelopmentCard> deck = new Stack<DevelopmentCard>();
	
	public DevelopmentCard dealCard() {
		if (deck.isEmpty()) {
			return null;
		} else {
			return deck.pop();
		}
	}
	
	public boolean isEmpty() {
		return deck.isEmpty();
	}
	
	public List<DevelopmentCard> addCard(DevelopmentType type, int amount) {
		List<DevelopmentCard> temp = new ArrayList<DevelopmentCard>();
		for (int i = 0; i < amount; i++) {
			temp.add(new DevelopmentCard(type));
		}
		return temp;
	}
	
	public DevelopmentDeck() {
		ArrayList<DevelopmentCard> temp = new ArrayList<DevelopmentCard>();
		
		temp.addAll(addCard(DevelopmentType.KNIGHT, 14));
		temp.addAll(addCard(DevelopmentType.CHAPEL, 1));
		temp.addAll(addCard(DevelopmentType.LIBRARY, 1));
		temp.addAll(addCard(DevelopmentType.MARKET, 1));
		temp.addAll(addCard(DevelopmentType.UNIVERSITY, 1));
		temp.addAll(addCard(DevelopmentType.PALACE, 1));
		temp.addAll(addCard(DevelopmentType.ROADBUILDING, 2));
		temp.addAll(addCard(DevelopmentType.MONOPOLY, 2));
		temp.addAll(addCard(DevelopmentType.YEAROFPLENTY, 2));
		
		Collections.shuffle(temp);
		for (DevelopmentCard card : temp) {
			deck.push(card);
		}
	}
}
