package stf.table;

import java.util.ArrayList;

import stf.gamePieces.Board;
import stf.gamePieces.City;
import stf.gamePieces.DevelopmentDeck;
import stf.gamePieces.Dice;
import stf.gamePieces.Intersection;
import stf.gamePieces.Person;
import stf.gamePieces.ResourceDeck;
import stf.gamePieces.ResourceType;
import stf.gamePieces.Settlement;
import stf.gamePieces.Tile;
import stf.gamePieces.TileType;

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
	
}
