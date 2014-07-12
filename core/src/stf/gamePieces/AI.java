package stf.gamePieces;

import java.util.ArrayList;

public class AI extends Person {

	public AI(PlayerColor color) {
		super(color);
	}
	
	@Override
	public Move getMove() {
		// TODO Auto-generated method stub
		ArrayList<Move> moves = generateMoves();
		scoreMoves(moves);
		
		Move currentBestMove;
		double currentBestScore = -10000;
		Move currentMove;
		for (int i = 0; i < moves.size(); ++i) {
			
		}
		
		return null;
	}
	
	/* Methods to get potential buying moves */
	/* These might be moved to Person if we feel */
	
	private ArrayList<Move> generateMoves() {
		ArrayList<Move> moves = new ArrayList<Move>();
		
		//generate play moves
		
		
		if (!hasRolled) {
			//roll move or play moves
			moves.addAll(generatePlayMoves());
			moves.add(new Move(MoveType.ROLL, null, this));
			
		} else {
			if (!hasPlayedDevelopmentCard) {
				//Generate Play Moves
				moves.addAll(generatePlayMoves());
				
			}
			//Generate Build Moves
			
			
			
			//Generate Trade Moves
			
			//Generate Roll Moves
			//if the player has not already rolled
			
			
			//Generate End Turn Moves
			// this is always an option
			moves.add(new Move(MoveType.ENDTURN, null, this));
		}
		
		
		return moves;
	}
	
	private ArrayList<Move> generatePlayMoves() {
		ArrayList<Move> moves = new ArrayList<Move>();
		DevelopmentCard card;
		MoveSubType subType;
		for (int i = 0; i < developmentHand.size(); ++i) {
			card = developmentHand.get(i);
			if (!card.isVictoryPoint()) {
				switch(card.getType()) {
				case KNIGHT:
					moves.add(e)
					break;
					
				case ROADBUILDING:
					subType = MoveSubType.ROADBUILDER;
					break;
					
				case MONOPOLY:
					subType = MoveSubType.MONOPOLY;
					break;
					
				case YEAROFPLENTY:
					subType = MoveSubType.YEAROFPLENTY;
					break;
					
				default:
					System.out.println("Should not get here. Generate Play Moves");
					subType = null;
					break;
				}
				moves.add(new Move(MoveType.PLAY, subType, this))
			}
		}
	}
	
	private void scoreMoves(ArrayList<Move> moves) {
		
	}

}
