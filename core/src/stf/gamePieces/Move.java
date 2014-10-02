package stf.gamePieces;

import java.util.ArrayList;

public class Move {
	private MoveType type;
	private MoveSubType subType;
	private Player performingPlayer;
	double score = 0;
	
	/* Keeps Track of Resource Types Needed */
	private ResourceType firstType;
	private ResourceType secondType;
	
	
	
	//this "buildposition" really really really breaks encapsulation rules
	private int buildPosition;
	private PortType portType;
	private Player tradePlayer;
	private ArrayList<ResourceCard> givingCards;
	private ArrayList<ResourceCard> receivingCards;
	
	
	/* Fields for Play Moves */
	private Player targetPlayer;
	private int thiefPosition;
	private ResourceCard stolenCard;
	private ResourceType monopolyType;
	private int firstPath;
	private int secondPath;
	
	
	public Move(MoveType type, MoveSubType subType, Player performingPlayer) {
		this.type = type;
		this.subType = subType;
		this.performingPlayer = performingPlayer;
	}
	
	//build constructor
	public Move(MoveType type, MoveSubType subType, Player performingPlayer, int buildPosition) {
		this.type = type;
		this.subType = subType;
		this.buildPosition = buildPosition;
		this.performingPlayer = performingPlayer;
	}
	
	
	//port trade constructor
	public Move(MoveType type, MoveSubType subType, Player performingPlayer, PortType portType, ResourceType receivingType) {
		this.type = type;
		this.subType = subType;
		this.portType = portType;
		this.performingPlayer = performingPlayer;
		secondType = receivingType;
	}
	
	//random port type constructor
	public Move(MoveType type, MoveSubType subType, Player performingPlayer, PortType portType, ResourceType givingType, ResourceType receivingType) {
		this.type = type;
		this.subType = subType;
		this.portType = portType;
		this.performingPlayer = performingPlayer;
		firstType = givingType;
		secondType = receivingType;
	}
	
	
	
	//player trade constructor
	public Move(MoveType type, MoveSubType subType, Player performingPlayer, Player tradePlayer, ArrayList<ResourceCard> givingCards, ArrayList<ResourceCard> receivingCards) {
		this.type = type;
		this.subType = subType;
		this.tradePlayer = tradePlayer;
		this.performingPlayer = performingPlayer;
		this.givingCards = givingCards;
		this.receivingCards = receivingCards;
	}
	
	
	/* Play Move Constructors */
	
	/* Knight */
	public Move(MoveType type, MoveSubType subType, Player usingPlayer, Player targetPlayer, int thiefPosition, ResourceCard stolenCard) {
		this.type = type;
		this.subType = subType;
		performingPlayer = usingPlayer;
		this.targetPlayer = targetPlayer;
		this.thiefPosition = thiefPosition;
		this.stolenCard = stolenCard;
	}
	
	/* Monopoly */
	public Move(MoveType type, MoveSubType subType, Player usingPlayer, ResourceType monopolyType) {
		this.type = type;
		this.subType = subType;
		performingPlayer = usingPlayer;
		this.monopolyType = monopolyType;
	}
	
	/* Road Builder */
	public Move(MoveType type, MoveSubType subType, Player usingPlayer, int firstPath, int secondPath) {
		this.type = type;
		this.subType = subType;
		performingPlayer = usingPlayer;
		this.firstPath = firstPath;
		this.secondPath = secondPath;
	}
	
	/* Year of Plenty */
	public Move(MoveType type, MoveSubType subType, Player usingPlayer, ResourceType firstType, ResourceType secondType) {
		this.type = type;
		this.subType = subType;
		performingPlayer = usingPlayer;
		this.firstType = firstType;
		this.secondType = secondType;
	}
	
	
	public int getBuildPosition() {
		return buildPosition;
	}
	
	public int getFirstPathID() {
		return firstPath;
	}
	
	public ResourceType getFirstType() {
		return firstType;
	}
	
	public ArrayList<ResourceCard> getGivingCards() {
		return givingCards;
	}
	
	public ResourceType getGivingType() {
		return firstType;
	}
	
	public ResourceType getMonopolyType() {
		return monopolyType;
	}
	
	public int getNewThiefPosition() {
		return thiefPosition;
	}
	
	public Player getPerformingPlayer() {
		return performingPlayer;
	}
	
	public PortType getPortType() {
		return portType;
	}
	
	public ArrayList<ResourceCard> getReceivingCards() {
		return receivingCards;
	}
	
	public ResourceType getReceivingType() {
		return secondType;
	}
	
	public int getSecondPathID() {
		return secondPath;
	}
	
	public ResourceType getSecondType() {
		return secondType;
	}
	
	public ResourceCard getStolenCard() {
		return stolenCard;
	}
	
	public MoveSubType getSubType() {
		return subType;
	}
	
	public Player getTargetPlayer() {
		return targetPlayer;
	}
	

	
	public Player getTradePartner() {
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
			extra = subType.toString();
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
