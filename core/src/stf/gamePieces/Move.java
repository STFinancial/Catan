package stf.gamePieces;

public class Move {
	private MoveType type;
	private MoveSubType subType;
	
	private int buildPosition;
	private DevelopmentCard playCard;
	private PortType portType;
	private Person tradePlayer;
	
	public Move(MoveType type) {
		this.type = type;
	}
	
	
	//build constructor
	public Move(MoveType type, MoveSubType subType, int buildPosition) {
		this.type = type;
		this.subType = subType;
		this.buildPosition = buildPosition;
	}
	
	//play constructor
	public Move(MoveType type, MoveSubType subType, DevelopmentCard playCard) {
		this.type = type;
		this.subType = subType;
		this.playCard = playCard;
	}
	
	//port trade constructor
	public Move(MoveType type, MoveSubType subType, PortType portType) {
		this.type = type;
		this.subType = subType;
		this.portType = portType;
	}
	
	//player trade constructor
	public Move(MoveType type, MoveSubType subType, Person tradePlayer) {
		this.type = type;
		this.subType = subType;
		this.tradePlayer = tradePlayer;
	}
	
	public int getBuildPosition() {
		return buildPosition;
	}
	
	public DevelopmentCard getCard() {
		return playCard;
	}
	
	public PortType getPortType() {
		return portType;
	}
	
	public MoveSubType getSubType() {
		return subType;
	}
	
	public Person getTradePlayer() {
		return tradePlayer;
	}
	
	public MoveType getType() {
		return type;
	}
	
	
	public void printInfo() {
		String extra;
		
		switch (type) {
		case BUILD: 
			extra = String.valueOf(buildPosition);
		break;
		
		case PLAY: 
			extra = playCard.type.toString();
		break;
		
		case TRADE: 
			if (subType == MoveSubType.PORT) {
			extra = portType.toString();
			} else {
			extra = tradePlayer.getColor().toString();
			}
		break;
		
		default:
			extra = "";
		break;
		
		}
		
		System.out.println(type.toString() + " " + subType.toString() + extra);
	}
}
