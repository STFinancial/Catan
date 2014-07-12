package stf.gamePieces;


public class DevelopmentCard {
	public DevelopmentType type;
	public boolean canPlay;
	
	public boolean equals(DevelopmentCard card) {
		return card.getType() == type;
	}
	
	public boolean isVictoryPoint() {
		return type.isVictoryPoint;
	}
	
	public DevelopmentType getType() {
		return type;
	}
}
