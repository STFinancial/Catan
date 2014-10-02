package stf.gamePieces;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.graphics.g2d.Batch;


public class Table {
	private final Board board = new Board();
	private final DevelopmentDeck developmentDeck = new DevelopmentDeck();
	private final ResourceCollection resourceDeck;
	private List<Player> players = new ArrayList<Player>();
	private int currentPlayerIndex; //Need at the moment for paying resources in correct order
	
	private ResourceCollection brickDeck;
	private ResourceCollection logsDeck;
	private ResourceCollection oreDeck;
	private ResourceCollection sheepDeck;
	private ResourceCollection wheatDeck;
	
	/* Eventually we might want to allow players to pick their color */
	public Table(int humans, int ai) {
		Map<ResourceType, Integer> resources = new HashMap<ResourceType, Integer>();
		for (ResourceType type : ResourceType.values()) {
			resources.put(type, 19);
		}
		resourceDeck = new ResourceCollection(resources);
		
		Queue<PlayerColor> availableColors =  new LinkedList<PlayerColor>();
		availableColors.addAll(Arrays.asList(PlayerColor.values()));
		
		for (int i = 0; i < humans + ai; i++) {
			boolean humanPlayer = true;
			if (i < ai) {
				humanPlayer = false;
			}
			players.add(new Player(availableColors.poll(), humanPlayer));
		}
	}
	
	public Player getCurrentPlayer() {
		return players.get(currentPlayerIndex);
	}
	
	public Player getWinner() {
		if (hasWinner()) {
			for (Player p: players) {
				if (p.getVictoryPoints() >= 10) {
					return p;
				}
			}
			return null;
		} else {
			return null;
		}
	}
	
	public boolean hasWinner() {
		for (Player p: players) {
			if (p.getVictoryPoints() >= 10) {
				return true;
			}
		}
		return false;
	}
	
	/* Consider visibility in this (should it be public?) */
	/* Should this be in the board class? (lots of imports going on here) */
	public void payResources(){
		int roll = dice.getTotal();
		Tile[] tiles = board.getTiles();
		
		Tile tempTile;
		int tempTileNumber;
		int thiefPosition = board.getThiefPosition();
		int numPlayers = players.size();
		Player paidPlayer;
		Intersection buildingIntersection;
		ResourceCollection tempDeck;
		for (int i = 0; i < tiles.length; ++i) {
			tempTile = tiles[i];
			tempTileNumber = tempTile.getNumber();
			tempDeck = getDeck(tempTile.getType());
			//if the roll is equal to the tile pip and the thief isn't there
			if (roll == tempTileNumber && i != thiefPosition) {
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
		case BUILD: performBuild(move, false);
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
	
	
	private ResourceCollection getDeck(ResourceType type) {
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

	private ResourceCollection getDeck(TileType type) {
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
	
	private void performBuild(Move move, boolean freeRoad) {
		Player performingPlayer = move.getPerformingPlayer();
		if (move.getSubType() == MoveSubType.CARD) {
			DevelopmentCard card = developmentDeck.dealCard();
			card.canPlay = false;
			performingPlayer.addDevelopmentCard(card);
			performingPlayer.removeResourceCards(ResourceType.WHEAT, 1);
			performingPlayer.removeResourceCards(ResourceType.SHEEP, 1);
			performingPlayer.removeResourceCards(ResourceType.ORE, 1);
		} else if (move.getSubType() == MoveSubType.ROAD) {
			Path p = board.getPath(move.getBuildPosition());
			p.build(move.getPerformingPlayer().getNextPlayableRoad());
			if (!freeRoad) {
				performingPlayer.removeResourceCards(ResourceType.BRICK, 1);
				performingPlayer.removeResourceCards(ResourceType.LOGS, 1);
			}
			performingPlayer.updateLongestRoad();
			
		} else {
			Intersection i = board.getIntersection(move.getBuildPosition());
			if (move.getSubType() == MoveSubType.SETTLEMENT) {
				i.build(move.getPerformingPlayer().getNextPlayableSettlement());
				performingPlayer.removeResourceCards(ResourceType.WHEAT, 1);
				performingPlayer.removeResourceCards(ResourceType.BRICK, 1);
				performingPlayer.removeResourceCards(ResourceType.LOGS, 1);
				performingPlayer.removeResourceCards(ResourceType.SHEEP, 1);
			} else {
				move.getPerformingPlayer().returnSettlement((Settlement) i.getBuilding());
				i.build(move.getPerformingPlayer().getNextPlayableCity());
				performingPlayer.removeResourceCards(ResourceType.WHEAT, 2);
				performingPlayer.removeResourceCards(ResourceType.ORE, 3);
				
			}
		}
	}
	
	/* Alternatively, this method might be completely unecessary */
	private void performEndTurn(Move move) {
		/* Should this method also start the next person's turn and get their move? */
		/* This essentially could be the gameplay loop */
		currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
		Player performingPlayer = move.getPerformingPlayer();
		performingPlayer.hasRolled = false;
		performingPlayer.hasPlayedDevelopmentCard = false;
		
		//need to allow players to now use cards they just purchased
		ArrayList<DevelopmentCard> hand = performingPlayer.getDevelopmentHand();
		for (int i = 0; i < hand.size(); ++i) {
			hand.get(i).canPlay = true;
		}
		
	}
	
	private void performPlay(Move move) {
		MoveSubType subType = move.getSubType();
		Player usingPlayer = move.getPerformingPlayer();
		usingPlayer.hasPlayedDevelopmentCard = true;
		if (subType == MoveSubType.KNIGHT) {
			Player targetPlayer = move.getTargetPlayer();
			usingPlayer.addResourceCard(move.getStolenCard());
			targetPlayer.removeResourceCards(move.getStolenCard().getType(), 1);			
		} else if (subType == MoveSubType.MONOPOLY) {
			Player tempPerson;
			ArrayList<ResourceCard> cards = new ArrayList<ResourceCard>();
			for (int i = 0; i < players.size(); ++i) {
				tempPerson = players.get(i);
				if (!tempPerson.equals(usingPlayer)) {
					cards.addAll(tempPerson.removeResourceCards(move.getMonopolyType()));	
				}
			}
			usingPlayer.addResourceCards(cards);
		} else if (subType == MoveSubType.ROADBUILDER) {
			if (move.getFirstPathID() > -1) {
				Move firstMove = new Move(MoveType.BUILD, MoveSubType.ROAD, usingPlayer, move.getFirstPathID());
				performBuild(firstMove, true);
			}
			if (move.getSecondPathID() > -1) {
				Move secondMove = new Move(MoveType.BUILD, MoveSubType.ROAD, usingPlayer, move.getSecondPathID());
				performBuild(secondMove, true);
			}
		} else if (subType == MoveSubType.YEAROFPLENTY) {
			usingPlayer.addResourceCard(getDeck(move.getFirstType()).dealCard());
			usingPlayer.addResourceCard(getDeck(move.getSecondType()).dealCard());
			
		} else {
			System.out.println("Shouldn't get here. Perform Play");
		}
		return;
	}
	
	private void performRollAndGiveResources(Move move) {
		dice.roll();
		move.getPerformingPlayer().hasRolled = true;
		payResources();
	}
	
	private void performTrade(Move move) {
		Player performingPlayer =  move.getPerformingPlayer();
		if (move.getSubType() == MoveSubType.PORT || move.getSubType() == MoveSubType.FOURTOONE) {
			ResourceType givingType = move.getGivingType();
			ResourceType receivingType = move.getReceivingType();
			ResourceCollection deckToReturnTo = getDeck(givingType);
			ResourceCollection deckToDealFrom;
			int cardsToReturn;
			if (move.getSubType() == MoveSubType.PORT) {
				if (move.getPortType() == PortType.RANDOM) {
					deckToDealFrom = getDeck(receivingType);
					cardsToReturn = 3;
				} else {
					cardsToReturn = 2;
					switch (move.getPortType()) {
					case WHEAT:
						deckToDealFrom = wheatDeck;
						break;
					case LOGS:
						deckToDealFrom = logsDeck;
						break;
						
					case SHEEP:
						deckToDealFrom = sheepDeck;
						break;
						
					case ORE:
						deckToDealFrom = oreDeck;
						break;
						
					case BRICK:
						deckToDealFrom = brickDeck;
						break;
					
					default: System.out.println("Shouldn't get here. PerformTrade switch.");
						deckToDealFrom = null;
						break;
					}
				}
			} else {
				cardsToReturn = 4;
				deckToDealFrom = getDeck(receivingType);
			}
			
			deckToReturnTo.returnCards(performingPlayer.removeResourceCards(givingType, cardsToReturn));
			performingPlayer.addResourceCard(deckToDealFrom.dealCard());
			
		} else {
			//trade between players
			Player receivingPlayer = move.getTradePartner();
			receivingPlayer.addResourceCards(performingPlayer.removeResourceCards(move.getGivingCards()));
			performingPlayer.addResourceCards(receivingPlayer.removeResourceCards(move.getReceivingCards()));
		}
	}

	public Board getBoard() {
		return board;
	}
	
	public void render(Batch batch){
		for(Tile t : board.getTiles()){
			t.getSprite().draw(batch);
			if(t.getType() != TileType.DESERT){
				t.getPipSprite().draw(batch);
			}
		}
		for(Intersection i : board.getIntersections()){
			i.getSprite().draw(batch);
		}

		for(Port p : board.getPorts()){
			p.getSprite().draw(batch);
			p.getTypeSprite().draw(batch);
		}

		
		for(Path p : board.getPaths()){
			p.getSprite().draw(batch);
		}
	}
	
	private int rollDice() {
		return ThreadLocalRandom.current().nextInt(1, 13);
	}	
}
