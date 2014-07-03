package stf.gamePieces;


import java.util.Stack;

public class DevelopmentDeck {
	private Stack<DevelopmentCard> deck;
	
	public boolean isEmpty() {
		return deck.isEmpty();
	}
	
	public DevelopmentCard dealCard() {
		if (deck.isEmpty()) {
			return null;
		} else {
			return deck.pop();
		}
	}
	
	public DevelopmentDeck() {
		deck = new Stack<DevelopmentCard>();
		
		DevelopmentCard[] temp = new DevelopmentCard[25];
		
		DevelopmentCard newDevelopmentCard;
		
		for (int i = 0; i < 14; i++) {
			newDevelopmentCard = new DevelopmentCard();
			newDevelopmentCard.type = DevelopmentType.KNIGHT;
			temp[i] = newDevelopmentCard;
		}
		
		newDevelopmentCard = new DevelopmentCard();
		newDevelopmentCard.type = DevelopmentType.CHAPEL;
		temp[14] = newDevelopmentCard;
		newDevelopmentCard = new DevelopmentCard();
		newDevelopmentCard.type = DevelopmentType.LIBRARY;
		temp[15] = newDevelopmentCard;
		newDevelopmentCard = new DevelopmentCard();
		newDevelopmentCard.type = DevelopmentType.MARKET;
		temp[16] = newDevelopmentCard;
		newDevelopmentCard = new DevelopmentCard();
		newDevelopmentCard.type = DevelopmentType.UNIVERSITY;
		temp[17] = newDevelopmentCard;
		newDevelopmentCard = new DevelopmentCard();
		newDevelopmentCard.type = DevelopmentType.PALACE;
		temp[18] = newDevelopmentCard;
		newDevelopmentCard = new DevelopmentCard();
		newDevelopmentCard.type = DevelopmentType.ROADBUILDING;
		temp[19] = newDevelopmentCard;
		newDevelopmentCard = new DevelopmentCard();
		newDevelopmentCard.type = DevelopmentType.ROADBUILDING;
		temp[20] = newDevelopmentCard;
		newDevelopmentCard = new DevelopmentCard();
		newDevelopmentCard.type = DevelopmentType.MONOPOLY;
		temp[21] = newDevelopmentCard;
		newDevelopmentCard = new DevelopmentCard();
		newDevelopmentCard.type = DevelopmentType.MONOPOLY;
		temp[22] = newDevelopmentCard;
		newDevelopmentCard = new DevelopmentCard();
		newDevelopmentCard.type = DevelopmentType.YEAROFPLENTY;
		temp[23] = newDevelopmentCard;
		newDevelopmentCard = new DevelopmentCard();
		newDevelopmentCard.type = DevelopmentType.YEAROFPLENTY;
		temp[24] = newDevelopmentCard;
		
		temp = BoardUtility.arrayShuffle(temp);
		for (int i = 0; i < 25; i++){
			deck.push(temp[i]);
		}
	}
}
