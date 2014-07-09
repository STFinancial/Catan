package stf.gamePieces;

import java.util.ArrayList;

public class Move {
	private MoveType type;
	private MoveSubType subType;
	private Person performingPlayer;
	
	//this "buildposition" really really really breaks encapsulation rules
	private int buildPosition;
	private DevelopmentCard playCard;
	private PortType portType;
	private ResourceType givingType;
	private ResourceType receivingType;
	private Person tradePlayer;
	private ArrayList<ResourceCard> givingCards;
	private ArrayList<ResourceCard> receivingCards;
	
	public Move(MoveType type, Person performingPlayer) {
		this.type = type;
		this.performingPlayer = performingPlayer;
	}
	
	//build constructor
	public Move(MoveType type, MoveSubType subType, int buildPosition, Person performingPlayer) {
		this.type = type;
		this.subType = subType;
		this.buildPosition = buildPosition;
		this.performingPlayer = performingPlayer;
	}
	
	//play constructor
	public Move(MoveType type, MoveSubType subType, DevelopmentCard playCard, Person performingPlayer) {
		this.type = type;
		this.subType = subType;
		this.playCard = playCard;
		this.performingPlayer = performingPlayer;
	}
	
	//port trade constructor
	public Move(MoveType type, MoveSubType subType, PortType portType, Person performingPlayer, ResourceType receivingType) {
		this.type = type;
		this.subType = subType;
		this.portType = portType;
		this.performingPlayer = performingPlayer;
		this.receivingType = receivingType;
	}
	
	//random port type constructor
	public Move(MoveType type, MoveSubType subType, PortType portType, Person performingPlayer, ResourceType givingType, ResourceType receivingType) {
		this.type = type;
		this.subType = subType;
		this.portType = portType;
		this.performingPlayer = performingPlayer;
		this.givingType = givingType;
		this.receivingType = receivingType;
	}
	
	//4:1 trade constructor
	public Move(MoveType type, MoveSubType subType, Person performingPlayer, ResourceType givingType, ResourceType receivingType) {
		this.type = type;
		this.subType = subType;
		this.performingPlayer = performingPlayer;
		this.givingType = givingType;
		this.receivingType = receivingType;
	}
	
	//player trade constructor
	public Move(MoveType type, MoveSubType subType, Person tradePlayer, Person performingPlayer, ArrayList<ResourceCard> givingCards, ArrayList<ResourceCard> receivingCards) {
		this.type = type;
		this.subType = subType;
		this.tradePlayer = tradePlayer;
		this.performingPlayer = performingPlayer;
		this.givingCards = givingCards;
		this.receivingCards = receivingCards;
	}
	
	public int getBuildPosition() {
		return buildPosition;
	}
	
	public DevelopmentCard getCard() {
		return playCard;
	}
	
	
	
	public ArrayList<ResourceCard> getGivingCards() {
		return givingCards;
	}
	
	public ResourceType getGivingType() {
		return givingType;
	}
	
	public Person getPerformingPlayer() {
		return performingPlayer;
	}
	
	public PortType getPortType() {
		return portType;
	}
	
	public ArrayList<ResourceCard> getReceivingCards() {
		return receivingCards;
	}
	
	public ResourceType getReceivingType() {
		return receivingType;
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
