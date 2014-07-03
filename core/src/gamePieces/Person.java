package gamePieces;

import java.util.ArrayList;

public abstract class Person {
	private PlayerColor color;
	private ArrayList<DevelopmentCard> developmentHand;
	private ArrayList<ResourceCard> resourceHand;
	
	public ArrayList<DevelopmentCard> getDevelopmentHand() {
		return developmentHand;
	}
	
	public ArrayList<ResourceCard> getResourceHand() {
		return resourceHand;
	}
	
	public abstract Move getMove();
}
