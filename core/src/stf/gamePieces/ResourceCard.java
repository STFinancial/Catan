package stf.gamePieces;

public class ResourceCard {
	public ResourceType type;
	
	public boolean equals(ResourceCard card) {
		return card.type == type;
	}
	
	public ResourceType getType() {
		return type;
	}
	
	
}
