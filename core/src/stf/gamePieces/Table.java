package stf.gamePieces;

import java.util.ArrayList;


public class Table {
	private Board board;
	
	private Dice dice;
	
	private DevelopmentDeck developmentDeck;
	
	private ArrayList<Person> players;
	private int currentPlayerIndex; //Need at the moment for paying resources in correct order
	
	private ResourceDeck brickDeck;
	private ResourceDeck logsDeck;
	private ResourceDeck oreDeck;
	private ResourceDeck sheepDeck;
	private ResourceDeck wheatDeck;
	
	/* Eventually we might want to allow players to pick their color */
	public Table(int numPlayers, int numAI) {
		board = new Board();
		dice = new Dice();
		developmentDeck = new DevelopmentDeck();
		currentPlayerIndex = 0;
		brickDeck = new ResourceDeck(ResourceType.BRICK);
		logsDeck = new ResourceDeck(ResourceType.LOGS);
		oreDeck = new ResourceDeck(ResourceType.ORE);
		sheepDeck = new ResourceDeck(ResourceType.SHEEP);
		wheatDeck = new ResourceDeck(ResourceType.WHEAT);
		
		players = new ArrayList<Person>(numPlayers + numAI);
		for (int i = 0; i < numPlayers + numAI; ++i) {
			if (i < numPlayers) {
				players.add(new Player(PlayerColor.getColor(i)));
			} else {
				players.add(new AI(PlayerColor.getColor(i)));
			}
		}
	}
	
	public Person getCurrentPlayer() {
		return players.get(currentPlayerIndex);
	}
	
	/* Consider visibility in this (should it be public?) */
	/* Should this be in the board class? (lots of imports going on here) */
	public void payResources(){
		int roll = dice.getTotal();
		Tile[] tiles = board.getTiles();
		
		Tile tempTile;
		int tempTileNumber;
		int numPlayers = players.size();
		Person paidPlayer;
		Intersection buildingIntersection;
		ResourceDeck tempDeck;
		for (int i = 0; i < tiles.length; ++i) {
			tempTile = tiles[i];
			tempTileNumber = tempTile.getNumber();
			tempDeck = getDeck(tempTile.getType());
			//if the roll is equal to the tile pip
			if (roll == tempTileNumber) {
				//pay players starting with the player who rolled
				for (int j = currentPlayerIndex; j < currentPlayerIndex + numPlayers; ++j) {
					paidPlayer = players.get(j % numPlayers);
					for (Settlement s: paidPlayer.getSettlements()) {
						buildingIntersection = s.getPosition();
						if (buildingIntersection != null) {
							for (Tile t: buildingIntersection.getAdjacentTiles()) {
								if (t.equals(tempTile)) {
									paidPlayer.addResourceCard(tempDeck.dealCard());
								}
							}
						}
					}
					for (City c: paidPlayer.getCities()) {
						buildingIntersection = c.getPosition();
						if (buildingIntersection != null) {
							for (Tile t: buildingIntersection.getAdjacentTiles()) {
								if (t.equals(tempTile)) {
									paidPlayer.addResourceCard(tempDeck.dealCard());
									paidPlayer.addResourceCard(tempDeck.dealCard());
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void performMove(Move move) {
		MoveType type = move.getType();
		/* Moves should be validated before passed to this method */
		switch (type) {
		case BUILD: performBuild(move);
		break;
		
		case PLAY: performPlay(move);
		break;
		
		case TRADE: performTrade(move);
		break;
		
		//these last two seem a bit excessive
		case ENDTURN: performEndTurn(move);
		break;
				
		case ROLL: performRollAndGiveResources(move);
		break;
		
		default: System.out.println("Should not get here: Perform Move.");
		break;
		}
	}
	
	
	private ResourceDeck getDeck(ResourceType type) {
		switch (type) {
		//wheat first for minute optimization.
		case WHEAT: return wheatDeck;
		
		case SHEEP: return sheepDeck;
			
		case LOGS: return logsDeck;
		
		case ORE: return oreDeck;
		
		case BRICK:	return brickDeck;
		
		default: System.out.println("Shouldn't get here: Table.getDeck");
			return null;
		}
	}

	private ResourceDeck getDeck(TileType type) {
		switch (type) {
		//wheat first for minute optimization.
		case FIELDS: return wheatDeck;
		
		case PASTURE: return sheepDeck;
			
		case FOREST: return logsDeck;
		
		case MOUNTAINS: return oreDeck;
		
		case HILLS:	return brickDeck;
		
		default: System.out.println("Shouldn't get here: Table.getDeck");
			return null;
		}
	}
	
	private void performBuild(Move move) {
		if (move.getSubType() == MoveSubType.ROAD) {
			Path p = board.getPath(move.getBuildPosition());
			p.build(move.getPerformingPlayer().getNextPlayableRoad());
		} else {
			Intersection i = board.getIntersection(move.getBuildPosition());
			if (move.getSubType() == MoveSubType.SETTLEMENT) {
				i.build(move.getPerformingPlayer().getNextPlayableSettlement());
			} else {
				move.getPerformingPlayer().returnSettlement((Settlement) i.getBuilding());
				i.build(move.getPerformingPlayer().getNextPlayableCity());
			}
			
		}
	}
	
	/* Alternatively, this method might be completely unecessary */
	private void performEndTurn(Move move) {
		/* Should this method also start the next person's turn and get their move? */
		/* This essentially could be the gameplay loop */
		currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
	}
	
	private void performPlay(Move move) {
		//TODO: THIS METHOD
		
		return;
	}
	
	private void performRollAndGiveResources(Move move) {
		dice.roll();
		payResources();
	}
	
	private void performTrade(Move move) {
		/* Should be doing tests here to ensure that the correct subtypes re being set */
		Person player =  move.getPerformingPlayer();
		if (move.getSubType() == MoveSubType.PORT) {
			PortType portType = move.getPortType();
			ResourceDeck deck;
			switch (portType) {
			case RANDOM:
				deck = getDeck(move.getReceivingType());
			//	player.removeResourceCards(type, quantity)
			}
		} else {
			
		}
	}
	
	
	
}
