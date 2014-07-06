package stf.gamePieces;

public class Move {
	MoveType type;
	MoveSubType subType;
	
	int buildPosition;
	DevelopmentCard playCard;
	PortType portType;
	Person tradePlayer;
	
	public Move(MoveType type) {
		this.type = type;
	}
	
	
	//build constructor
	public Move(MoveType type, MoveSubType subType, int buildPosition) {
		this.type = type;
		this.subType = subType;
		this.buildPosition = buildPosition;
	}
	
	public Move(MoveType type, MoveSubType subType, DevelopmentCard playCard) {
		this.type = type;
		this.subType = subType;
		this.playCard = playCard;
	}
}
